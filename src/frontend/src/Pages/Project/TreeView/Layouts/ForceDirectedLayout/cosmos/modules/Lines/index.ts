import regl from 'regl'
import {getRgbaColor, getValue} from '../../helper'
import {CoreModule} from '../core-module'
import drawStraightFrag from './draw-straight.frag'
import drawStraightVert from './draw-straight.vert'
import {defaultLinkColor, defaultLinkWidth} from '../../variables'
import {CosmosInputLink, CosmosInputNode} from '../../types'
import {destroyBuffer} from '../Shared/buffer'
import {mat4} from "gl-matrix";
import TreeViewFontAtlasImage from "../../../../../../../../Assets/tree_view_font_atlas.png"


function stringHash(message: string): number {
    let hash = 0,
        i, chr;
    if (message.length === 0) return hash;
    for (i = 0; i < message.length; i++) {
        chr = message.charCodeAt(i);
        hash = ((hash << 5) - hash) + chr;
        hash |= 0; // Convert to 32bit integer
    }
    return hash;
}

export class NumberPair {
    _hashCode: number;

    constructor(public a: string, public b: string) {
        this._hashCode = stringHash(a + b);
    }

    hashCode(): number {
        return this._hashCode;
    }
}

export class Lines<N extends CosmosInputNode, L extends CosmosInputLink> extends CoreModule<N, L> {
    public drawLabelsCommand: regl.DrawCommand | undefined
    public labelPositionsTex: regl.Texture2D | undefined
    public labelPositionsData: Float32Array | undefined
    public labelPositionsDataMatrices = new Map<number, Float32Array>()
    glyphWidth: number | undefined;
    renderLabels: boolean = false;
    private drawStraightCommand: regl.DrawCommand | undefined
    private colorBuffer: regl.Buffer | undefined
    private widthBuffer: regl.Buffer | undefined

    public create(): void {
        this.updateColor()
        this.updateWidth()
    }

    public async initPrograms() {
        const {reglInstance, config, store, data, points} = this
        const {pointsTextureSize} = store

        const geometryLinkBuffer = {
            buffer: reglInstance.buffer([
                [0, -0.5],
                [1, -0.5],
                [1, 0.5],
                [0, -0.5],
                [1, 0.5],
                [0, 0.5],
            ]),
            divisor: 0,
        }

        const instancePoints: number[][] = []
        data.completeLinks.forEach(l => {
            const toIndex = data.getSortedIndexById(l.target) as number
            const fromIndex = data.getSortedIndexById(l.source) as number
            const fromX = fromIndex % pointsTextureSize
            const fromY = Math.floor(fromIndex / pointsTextureSize)

            const toX = toIndex % pointsTextureSize
            const toY = Math.floor(toIndex / pointsTextureSize)
            instancePoints.push([fromX, fromY])
            instancePoints.push([toX, toY])
        })
        const pointsBuffer = reglInstance.buffer(instancePoints)

        this.drawStraightCommand = reglInstance({
            vert: drawStraightVert,
            frag: drawStraightFrag,

            attributes: {
                position: geometryLinkBuffer,
                pointA: {
                    buffer: () => pointsBuffer,
                    divisor: 1,
                    offset: Float32Array.BYTES_PER_ELEMENT * 0,
                    stride: Float32Array.BYTES_PER_ELEMENT * 4,
                },
                pointB: {
                    buffer: () => pointsBuffer,
                    divisor: 1,
                    offset: Float32Array.BYTES_PER_ELEMENT * 2,
                    stride: Float32Array.BYTES_PER_ELEMENT * 4,
                },
                color: {
                    buffer: () => this.colorBuffer,
                    divisor: 1,
                    offset: Float32Array.BYTES_PER_ELEMENT * 0,
                    stride: Float32Array.BYTES_PER_ELEMENT * 4,
                },
                width: {
                    buffer: () => this.widthBuffer,
                    divisor: 1,
                    offset: Float32Array.BYTES_PER_ELEMENT * 0,
                    stride: Float32Array.BYTES_PER_ELEMENT * 1,
                },
            },
            uniforms: {
                positions: () => points?.currentPositionFbo,
                particleSize: () => points?.sizeFbo,
                particleGreyoutStatus: () => points?.greyoutStatusFbo,
                transform: () => store.transform,
                pointsTextureSize: () => store.pointsTextureSize,
                nodeSizeScale: () => config.nodeSizeScale,
                widthScale: () => config.linkWidthScale,
                useArrow: () => config.linkArrows,
                arrowSizeScale: () => config.linkArrowsSizeScale,
                spaceSize: () => config.spaceSize,
                screenSize: () => store.screenSize,
                ratio: () => config.pixelRatio,
                linkVisibilityDistanceRange: () => config.linkVisibilityDistanceRange,
                linkVisibilityMinTransparency: () => config.linkVisibilityMinTransparency,
                greyoutOpacity: () => config.linkGreyoutOpacity,
                scaleNodesOnZoom: () => config.scaleNodesOnZoom,
            },
            cull: {
                enable: true,
                face: 'back',
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
            count: 6, // segmentInstanceGeometry length
            instances: () => data.linksNumber,
        })


        const glyphImg = await loadImage(
            TreeViewFontAtlasImage
        )
        const links = Array.from(data.completeLinks)

        const linkLabels: string[] = []

        for (const link of links) {
            linkLabels.push(link.weight.toString())
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

        const numColumns = 4
        const numRows = linkLabels.length

        const positions: number[] = [];
        const texcoords: number[] = [];
        const messageIds: number[] = [];
        this.labelPositionsData = new Float32Array(numColumns * numRows * 4);

        const quadPositions = [-1, -1, 1, -1, -1, 1, -1, 1, 1, -1, 1, 1];
        const quadTexcoords = [0, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 0];
        links.forEach((link, id) => {
            const subarray = this.labelPositionsData!.subarray(id * 16, (id + 1) * 16)
            const numberPairA = new NumberPair(link.source, link.target)
            const numberPairB = new NumberPair(link.target, link.source)

            this.labelPositionsDataMatrices.set(numberPairA.hashCode(), subarray)
            this.labelPositionsDataMatrices.set(numberPairB.hashCode(), subarray)

            const message = link.weight.toString()
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

        console.log(linkLabels.length)
        console.log(this.labelPositionsData.length)
        console.log(numColumns)
        console.log(numRows)

        this.labelPositionsTex = reglInstance.texture({
            data: this.labelPositionsData,
            type: 'float',
            width: numColumns,
            height: numRows,
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
                viewProjection: ({viewportWidth, viewportHeight}) =>
                    mat4.ortho(mat4.create(), 0, viewportWidth, 0, viewportHeight, -1, 1),
                matrixTex: this.labelPositionsTex,
                matrixTexSize: [numColumns, numRows],
                glyphTex,
            },
            count: positions.length / 2,
        });
    }

    public draw(): void {
        if (!this.colorBuffer || !this.widthBuffer) return
        this.drawStraightCommand?.()
        if (this.renderLabels)
            this.drawLabelsCommand?.()
    }

    public updateColor(): void {
        const {reglInstance, config, data} = this
        const instancePoints: number[][] = []
        data.completeLinks.forEach(l => {
            const c = getValue<L, string | [number, number, number, number]>(l, config.linkColor) ?? defaultLinkColor
            const rgba = getRgbaColor(c)
            instancePoints.push(rgba)
        })
        this.colorBuffer = reglInstance.buffer(instancePoints)
    }

    public updateWidth(): void {
        const {reglInstance, config, data} = this
        const instancePoints: number[][] = []
        data.completeLinks.forEach(l => {
            const linkWidth = getValue<L, number>(l, config.linkWidth)
            instancePoints.push([linkWidth ?? defaultLinkWidth])
        })
        this.widthBuffer = reglInstance.buffer(instancePoints)
    }

    public destroy(): void {
        destroyBuffer(this.colorBuffer)
        destroyBuffer(this.widthBuffer)
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