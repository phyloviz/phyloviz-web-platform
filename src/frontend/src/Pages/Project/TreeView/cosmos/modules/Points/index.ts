import regl from 'regl'
import {CoreModule} from '../../modules/core-module'
import {createColorBuffer, createGreyoutStatusBuffer} from '../../modules/Points/color-buffer'
import drawPointsFrag from '../../modules/Points/draw-points.frag'
import drawPointsVert from '../../modules/Points/draw-points.vert'
import findPointsOnAreaSelectionFrag from '../../modules/Points/find-points-on-area-selection.frag'
import drawHighlightedFrag from '../../modules/Points/draw-highlighted.frag'
import drawHighlightedVert from '../../modules/Points/draw-highlighted.vert'
import findHoveredPointFrag from '../../modules/Points/find-hovered-point.frag'
import findHoveredPointVert from '../../modules/Points/find-hovered-point.vert'
import {createSizeBuffer, getNodeSize} from '../../modules/Points/size-buffer'
import updatePositionFrag from '../../modules/Points/update-position.frag'
import {createIndexesBuffer, createQuadBuffer, destroyFramebuffer} from '../../modules/Shared/buffer'
import {createTrackedIndicesBuffer, createTrackedPositionsBuffer} from '../../modules/Points/tracked-buffer'
import trackPositionsFrag from '../../modules/Points/track-positions.frag'
import updateVert from '../../modules/Shared/quad.vert'
import clearFrag from '../../modules/Shared/clear.frag'
import {defaultConfigValues} from '../../variables'
import {readPixels} from '../../helper'
import {CosmosInputLink, CosmosInputNode} from '../../types'
import {mat4} from 'gl-matrix'

export class Points<N extends CosmosInputNode, L extends CosmosInputLink> extends CoreModule<N, L> {
    public currentPositionFbo: regl.Framebuffer2D | undefined
    public previousPositionFbo: regl.Framebuffer2D | undefined
    public velocityFbo: regl.Framebuffer2D | undefined
    public selectedFbo: regl.Framebuffer2D | undefined
    public colorFbo: regl.Framebuffer2D | undefined
    public hoveredFbo: regl.Framebuffer2D | undefined
    public greyoutStatusFbo: regl.Framebuffer2D | undefined
    public sizeFbo: regl.Framebuffer2D | undefined
    public trackedIndicesFbo: regl.Framebuffer2D | undefined
    public trackedPositionsFbo: regl.Framebuffer2D | undefined
    // drawTextCommands: Map<string, regl.DrawCommand> = new Map()
    private drawCommand: regl.DrawCommand | undefined
    private drawHighlightedCommand: regl.DrawCommand | undefined
    private updatePositionCommand: regl.DrawCommand | undefined
    private findPointsOnAreaSelectionCommand: regl.DrawCommand | undefined
    private findHoveredPointCommand: regl.DrawCommand | undefined
    private clearHoveredFboCommand: regl.DrawCommand | undefined
    private trackPointsCommand: regl.DrawCommand | undefined
    public drawLabelsCommand: regl.DrawCommand | undefined
    private trackedIds: string[] | undefined
    private trackedPositionsById: Map<string, [number, number]> = new Map()
     public labelPositionsTex: regl.Texture2D | undefined
    public labelPositionsData: Float32Array | undefined
    public labelPositionsDataMatrices: Float32Array[] = []
    glyphWidth: number | undefined;

    public create(): void {
        const {reglInstance, config, store, data} = this
        const {spaceSize} = config
        const {pointsTextureSize} = store
        const numParticles = data.nodes.length
        const initialState = new Float32Array(pointsTextureSize * pointsTextureSize * 4)
        for (let i = 0; i < numParticles; ++i) {
            const sortedIndex = this.data.getSortedIndexByInputIndex(i)
            const node = data.nodes[i]
            if (node && sortedIndex !== undefined) {
                const space = spaceSize ?? defaultConfigValues.spaceSize
                initialState[sortedIndex * 4 + 0] = node.x ?? space * store.getRandomFloat(0.495, 0.505)
                initialState[sortedIndex * 4 + 1] = node.y ?? space * store.getRandomFloat(0.495, 0.505)
            }
        }

        // Create position buffer
        this.currentPositionFbo = reglInstance.framebuffer({
            color: reglInstance.texture({
                data: initialState,
                shape: [pointsTextureSize, pointsTextureSize, 4],
                type: 'float',
            }),
            depth: false,
            stencil: false,
        })


        this.previousPositionFbo = reglInstance.framebuffer({
            color: reglInstance.texture({
                data: initialState,
                shape: [pointsTextureSize, pointsTextureSize, 4],
                type: 'float',
            }),
            depth: false,
            stencil: false,
        })

        // Create velocity buffer
        this.velocityFbo = reglInstance.framebuffer({
            color: reglInstance.texture({
                data: new Float32Array(pointsTextureSize * pointsTextureSize * 4).fill(0),
                shape: [pointsTextureSize, pointsTextureSize, 4],
                type: 'float',
            }),
            depth: false,
            stencil: false,
        })

        // Create selected points buffer
        this.selectedFbo = reglInstance.framebuffer({
            color: reglInstance.texture({
                data: initialState,
                shape: [pointsTextureSize, pointsTextureSize, 4],
                type: 'float',
            }),
            depth: false,
            stencil: false,
        })

        this.hoveredFbo = reglInstance.framebuffer({
            shape: [2, 2],
            colorType: 'float',
            depth: false,
            stencil: false,
        })

        this.updateSize()
        this.updateColor()
        this.updateGreyoutStatus()
    }

    public async initPrograms() {
        const {reglInstance, config, store, data} = this
        this.updatePositionCommand = reglInstance({
            frag: updatePositionFrag,
            vert: updateVert,
            framebuffer: () => this.currentPositionFbo as regl.Framebuffer2D,
            primitive: 'triangle strip',
            count: 4,
            attributes: {quad: createQuadBuffer(reglInstance)},
            uniforms: {
                position: () => this.previousPositionFbo,
                velocity: () => this.velocityFbo,
                friction: () => config.simulation?.friction,
                spaceSize: () => config.spaceSize,
            },
        })
        this.drawCommand = reglInstance({
            frag: drawPointsFrag,
            vert: drawPointsVert,
            primitive: 'points',
            count: () => data.nodes.length,
            attributes: {indexes: createIndexesBuffer(reglInstance, store.pointsTextureSize)},
            uniforms: {
                positions: () => this.currentPositionFbo,
                particleColor: () => this.colorFbo,
                particleGreyoutStatus: () => this.greyoutStatusFbo,
                particleSize: () => this.sizeFbo,
                ratio: () => config.pixelRatio,
                sizeScale: () => config.nodeSizeScale,
                pointsTextureSize: () => store.pointsTextureSize,
                transform: () => store.transform,
                spaceSize: () => config.spaceSize,
                screenSize: () => store.screenSize,
                greyoutOpacity: () => config.nodeGreyoutOpacity,
                scaleNodesOnZoom: () => config.scaleNodesOnZoom,
            },
            blend: {
                enable: true,
                func: {
                    dstRGB: 'one minus src alpha',
                    srcRGB: 'src alpha',
                    dstAlpha: 'one minus src alpha',
                    srcAlpha: 'one',
                },
                equation: {
                    rgb: 'add',
                    alpha: 'add',
                },
            },
            depth: {
                enable: false,
                mask: false,
            },
        })
        this.findPointsOnAreaSelectionCommand = reglInstance({
            frag: findPointsOnAreaSelectionFrag,
            vert: updateVert,
            framebuffer: () => this.selectedFbo as regl.Framebuffer2D,
            primitive: 'triangle strip',
            count: 4,
            attributes: {quad: createQuadBuffer(reglInstance)},
            uniforms: {
                position: () => this.currentPositionFbo,
                particleSize: () => this.sizeFbo,
                spaceSize: () => config.spaceSize,
                screenSize: () => store.screenSize,
                sizeScale: () => config.nodeSizeScale,
                transform: () => store.transform,
                ratio: () => config.pixelRatio,
                'selection[0]': () => store.selectedArea[0],
                'selection[1]': () => store.selectedArea[1],
                scaleNodesOnZoom: () => config.scaleNodesOnZoom,
                maxPointSize: () => store.maxPointSize,
            },
        })
        this.clearHoveredFboCommand = reglInstance({
            frag: clearFrag,
            vert: updateVert,
            framebuffer: this.hoveredFbo as regl.Framebuffer2D,
            primitive: 'triangle strip',
            count: 4,
            attributes: {quad: createQuadBuffer(reglInstance)},
        })
        this.findHoveredPointCommand = reglInstance({
            frag: findHoveredPointFrag,
            vert: findHoveredPointVert,
            primitive: 'points',
            count: () => data.nodes.length,
            framebuffer: () => this.hoveredFbo as regl.Framebuffer2D,
            attributes: {indexes: createIndexesBuffer(reglInstance, store.pointsTextureSize)},
            uniforms: {
                position: () => this.currentPositionFbo,
                particleSize: () => this.sizeFbo,
                ratio: () => config.pixelRatio,
                sizeScale: () => config.nodeSizeScale,
                pointsTextureSize: () => store.pointsTextureSize,
                transform: () => store.transform,
                spaceSize: () => config.spaceSize,
                screenSize: () => store.screenSize,
                scaleNodesOnZoom: () => config.scaleNodesOnZoom,
                mousePosition: () => store.screenMousePosition,
                maxPointSize: () => store.maxPointSize,
            },
            depth: {
                enable: false,
                mask: false,
            },
        })
        this.drawHighlightedCommand = reglInstance({
            frag: drawHighlightedFrag,
            vert: drawHighlightedVert,
            attributes: {quad: createQuadBuffer(reglInstance)},
            primitive: 'triangle strip',
            count: 4,
            uniforms: {
                color: reglInstance.prop<{ color: number[] }, 'color'>('color'),
                width: reglInstance.prop<{ width: number }, 'width'>('width'),
                pointIndex: reglInstance.prop<{ pointIndex: number }, 'pointIndex'>('pointIndex'),
                positions: () => this.currentPositionFbo,
                particleColor: () => this.colorFbo,
                particleSize: () => this.sizeFbo,
                sizeScale: () => config.nodeSizeScale,
                pointsTextureSize: () => store.pointsTextureSize,
                transform: () => store.transform,
                spaceSize: () => config.spaceSize,
                screenSize: () => store.screenSize,
                scaleNodesOnZoom: () => config.scaleNodesOnZoom,
                maxPointSize: () => store.maxPointSize,
                particleGreyoutStatus: () => this.greyoutStatusFbo,
                greyoutOpacity: () => config.nodeGreyoutOpacity,
            },
            blend: {
                enable: true,
                func: {
                    dstRGB: 'one minus src alpha',
                    srcRGB: 'src alpha',
                    dstAlpha: 'one minus src alpha',
                    srcAlpha: 'one',
                },
                equation: {
                    rgb: 'add',
                    alpha: 'add',
                },
            },
            depth: {
                enable: false,
                mask: false,
            },
        })
        this.trackPointsCommand = reglInstance({
            frag: trackPositionsFrag,
            vert: updateVert,
            framebuffer: () => this.trackedPositionsFbo as regl.Framebuffer2D,
            primitive: 'triangle strip',
            count: 4,
            attributes: {quad: createQuadBuffer(reglInstance)},
            uniforms: {
                position: () => this.currentPositionFbo,
                trackedIndices: () => this.trackedIndicesFbo,
                pointsTextureSize: () => store.pointsTextureSize,
            },
        })
        const glyphImg = await loadImage(
            'https://webglfundamentals.org/webgl/resources/8x8-font.png'
        )
        const messages: string[] = []

        for (const node of this.data.nodes) {
            messages.push(node.id)
        }


        const glyphTex = reglInstance.texture({
            data: glyphImg,
            mag: 'nearest',
            min: 'nearest',
        });

        const glyphsAcross = 8;
        const glyphsDown = 5;
        this.glyphWidth = glyphImg.width / glyphsAcross;
        const glyphHeight = glyphImg.height / glyphsDown;
        const glyphUWidth = this.glyphWidth / glyphImg.width;
        const glyphVHeight = glyphHeight / glyphImg.height;

        const positions: number[] = [];
        const texcoords: number[] = [];
        const messageIds: number[] = [];
        this.labelPositionsData = new Float32Array(messages.length * 16);
        this.labelPositionsDataMatrices = [];
        const quadPositions = [-1, -1, 1, -1, -1, 1, -1, 1, 1, -1, 1, 1];
        const quadTexcoords = [0, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 0];
        messages.forEach((message, id) => {
            this.labelPositionsDataMatrices.push(this.labelPositionsData!.subarray(id * 16, (id + 1) * 16));
            for (let i = 0; i < message.length; ++i) {
                const c = convertToGlyphIndex(message[i]);
                const u = (c % glyphsAcross) * glyphUWidth;
                const v = ((c / glyphsAcross) | 0) * glyphVHeight;
                for (let j = 0; j < 6; ++j) {
                    const offset = j * 2;
                    positions.push(
                        quadPositions[offset] * 0.5 + i - message.length / 2,
                        quadPositions[offset + 1] * 0.5
                    );
                    texcoords.push(
                        u + quadTexcoords[offset] * glyphUWidth,
                        v + quadTexcoords[offset + 1] * glyphVHeight
                    );
                    messageIds.push(id);
                }
            }
        });

        this.labelPositionsTex = reglInstance.texture({
            data: this.labelPositionsData,
            type: 'float',
            width: 4,
            height: messages.length,
            mag: 'nearest',
            min: 'nearest',
            wrap: 'clamp',
        });

        this.drawLabelsCommand = reglInstance({
            vert: `
      attribute vec4 position;
      attribute vec2 texcoord;
      attribute float messageId;
      uniform sampler2D matrixTex;
      uniform vec2 matrixTexSize;
      uniform mat4 viewProjection;
      
      varying vec2 v_texcoord;
    
      void main() {
        vec2 uv = (vec2(0, messageId) + 0.5) / matrixTexSize;
        mat4 model = mat4(
          texture2D(matrixTex, uv),
          texture2D(matrixTex, uv + vec2(1.0 / matrixTexSize.x, 0)),
          texture2D(matrixTex, uv + vec2(2.0 / matrixTexSize.x, 0)),
          texture2D(matrixTex, uv + vec2(3.0 / matrixTexSize.x, 0)));
        gl_Position = viewProjection * model * position;
        v_texcoord = texcoord;
      }
    `,
            frag: `
      precision highp float;
    
      varying vec2 v_texcoord;
      uniform sampler2D glyphTex;
    
      void main() {
        vec4 glyphColor = texture2D(glyphTex, v_texcoord);
    
        if (glyphColor.a < 0.1) discard;
    
        gl_FragColor = glyphColor;
      }
    `,

            attributes: {
                position: {
                    buffer: positions,
                    size: 2,
                },
                texcoord: {
                    buffer: texcoords,
                    size: 2,
                },
                messageId: {
                    buffer: messageIds,
                    size: 1,
                },
            },
            uniforms: {
                viewProjection: ({ viewportWidth, viewportHeight }) =>
                    mat4.ortho(mat4.create(), 0, viewportWidth, 0, viewportHeight, -1, 1),
                matrixTex: this.labelPositionsTex,
                matrixTexSize: [4, messages.length],
                glyphTex,
            },
            count: positions.length / 2,
        });


        // for (const element of data.nodes) {
        //     const node = element
        //     const textMesh = this.data.getTextMeshById(node.id)!
        //
        //     const drawText = this.reglInstance({
        //         frag: `
        //     precision mediump float;
        //     uniform float t;
        //     void main () {
        //       gl_FragColor = vec4(0.0, 0.0, 0.0, 1.0);
        //     }`,
        //
        //         vert: `
        //     attribute vec2 position;
        //     uniform float x;
        //     uniform float y;
        //     uniform mat3 transform;
        //     uniform float spaceSize;
        //     uniform vec2 screenSize;
        //
        //     void main () {
        //       vec2 point = vec2(x,y);
        //       vec2 p = 2.0 * point / spaceSize - 1.0;
        //       p *= spaceSize / screenSize;
        //       vec3 final = transform * vec3(p, 1);
        //
        //       vec2 scale = vec2(transform[0][0])/200.0;
        //       scale.y = -scale.y;
        //       gl_Position = vec4(final.rg+scale*position, 0, 1);
        //     }`,
        //         attributes: {
        //             position: textMesh.positions,
        //         },
        //
        //         uniforms: {
        //             x: reglInstance.prop<{ x: number }, 'x'>('x'),
        //             y: reglInstance.prop<{ y: number }, 'y'>('y'),
        //             spaceSize: () => config.spaceSize,
        //             screenSize: () => store.screenSize,
        //             transform: () => store.transform,
        //         },
        //         elements: textMesh.edges,
        //         blend: {
        //             enable: true,
        //             func: {
        //                 dstRGB: 'one minus src alpha',
        //                 srcRGB: 'src alpha',
        //                 dstAlpha: 'one minus src alpha',
        //                 srcAlpha: 'one',
        //             },
        //             equation: {
        //                 rgb: 'add',
        //                 alpha: 'add',
        //             },
        //         },
        //         depth: {enable: false},
        //
        //     })
        //
        //     this.drawTextCommands.set(node.id, drawText)
        // }


    }


    public updateColor(): void {
        const {reglInstance, config, store, data} = this
        this.colorFbo = createColorBuffer(data, reglInstance, store.pointsTextureSize, config.nodeColor)
    }

    public updateGreyoutStatus(): void {
        const {reglInstance, store} = this
        this.greyoutStatusFbo = createGreyoutStatusBuffer(store.selectedIndices, reglInstance, store.pointsTextureSize)
    }

    public updateSize(): void {
        const {reglInstance, config, store, data} = this
        this.sizeFbo = createSizeBuffer(data, reglInstance, store.pointsTextureSize, config.nodeSize)
    }

    public trackPoints(): void {
        if (!this.trackedIndicesFbo || !this.trackedPositionsFbo) return
        this.trackPointsCommand?.()
    }

    public draw(): void {
        this.drawCommand?.()

        this.drawLabelsCommand?.()
        if (this.config.renderHighlightedNodeRing) {
            if (this.store.hoveredNode) {
                this.drawHighlightedCommand?.({
                    width: 0.85,
                    color: this.store.hoveredNodeRingColor,
                    pointIndex: this.store.hoveredNode.index,
                })
            }
            if (this.store.focusedNode) {
                this.drawHighlightedCommand?.({
                    width: 0.75,
                    color: this.store.focusedNodeRingColor,
                    pointIndex: this.store.focusedNode.index,
                })
            }
        }
    }




    public updatePosition(): void {
        this.updatePositionCommand?.()
        this.swapFbo()
    }

    public findPointsOnAreaSelection(): void {
        this.findPointsOnAreaSelectionCommand?.()
    }

    public findHoveredPoint(): void {
        this.clearHoveredFboCommand?.()
        this.findHoveredPointCommand?.()
    }

    public getNodeRadius(node: N): number {
        const {nodeSize} = this.config
        return getNodeSize(node, nodeSize) / 2
    }

    public trackNodesByIds(ids: string[]): void {
        this.trackedIds = ids.length ? ids : undefined
        this.trackedPositionsById.clear()
        const indices = ids.map(id => this.data.getSortedIndexById(id)).filter((d): d is number => d !== undefined)
        destroyFramebuffer(this.trackedIndicesFbo)
        this.trackedIndicesFbo = undefined
        destroyFramebuffer(this.trackedPositionsFbo)
        this.trackedPositionsFbo = undefined
        if (indices.length) {
            this.trackedIndicesFbo = createTrackedIndicesBuffer(indices, this.store.pointsTextureSize, this.reglInstance)
            this.trackedPositionsFbo = createTrackedPositionsBuffer(indices, this.reglInstance)
        }
        this.trackPoints()
    }

    public getTrackedPositions(): Map<string, [number, number]> {
        if (!this.trackedIds) return this.trackedPositionsById
        const pixels = readPixels(this.reglInstance, this.trackedPositionsFbo as regl.Framebuffer2D)
        this.trackedIds.forEach((id, i) => {
            const x = pixels[i * 4]
            const y = pixels[i * 4 + 1]
            if (x !== undefined && y !== undefined) this.trackedPositionsById.set(id, [x, y])
        })
        return this.trackedPositionsById
    }

    public destroy(): void {
        destroyFramebuffer(this.currentPositionFbo)
        destroyFramebuffer(this.previousPositionFbo)
        destroyFramebuffer(this.velocityFbo)
        destroyFramebuffer(this.selectedFbo)
        destroyFramebuffer(this.colorFbo)
        destroyFramebuffer(this.sizeFbo)
        destroyFramebuffer(this.greyoutStatusFbo)
        destroyFramebuffer(this.hoveredFbo)
        destroyFramebuffer(this.trackedIndicesFbo)
        destroyFramebuffer(this.trackedPositionsFbo)
    }

    public swapFbo(): void {
        const temp = this.previousPositionFbo
        this.previousPositionFbo = this.currentPositionFbo
        this.currentPositionFbo = temp
    }

}

function loadImage(url: string): Promise<HTMLImageElement> {
    return new Promise((resolve, reject) => {
        const img = new Image();
        img.crossOrigin = 'anonymous';
        img.onerror = reject;
        img.onload = () => resolve(img);
        img.src = url;
    });
}

function convertToGlyphIndex(c: string) {
    c = c.toUpperCase();
    if (c >= 'A' && c <= 'Z') {
        return c.charCodeAt(0) - 0x41;
    } else if (c >= '0' && c <= '9') {
        return c.charCodeAt(0) - 0x30 + 26;
    } else {
        return 255;
    }
}