import regl from 'regl'
import {createQuadBuffer} from '@cosmograph/cosmos/dist/modules/Shared/buffer'
import {CosmosInputLink, CosmosInputNode} from '@cosmograph/cosmos/dist/types'
import {GraphConfigInterface} from '@cosmograph/cosmos/dist/config'
import {GraphData} from '@cosmograph/cosmos/dist/modules/GraphData'
import {Points} from '@cosmograph/cosmos/dist/modules/Points'
import {CustomStore} from '../modules/Store'

export class NodeDrag<N extends CosmosInputNode, L extends CosmosInputLink> {
    public readonly reglInstance: regl.Regl
    public readonly config: GraphConfigInterface<N, L>
    public readonly store: CustomStore<N>
    public readonly data: GraphData<N, L>
    public readonly points: Points<N, L> | undefined
    private runCommand: regl.DrawCommand | undefined

    public constructor(
        reglInstance: regl.Regl,
        config: GraphConfigInterface<N, L>,
        store: CustomStore<N>,
        data: GraphData<N, L>,
        points?: Points<N, L>
    ) {
        this.reglInstance = reglInstance
        this.config = config
        this.store = store
        this.data = data
        if (points) this.points = points
    }

    public initPrograms(): void {
        const {reglInstance, config, store, points} = this

        const index = this.data.getSortedIndexById(store.draggedNode?.node.id)!
        this.runCommand = reglInstance({
            frag: `
			#ifdef GL_ES
		precision highp float
		#endif

		uniform sampler2D position
		uniform vec2 draggedIndex
		uniform vec2 mousePos

		varying vec2 index

		void main() {  
		vec4 pointPosition = texture2D(position, index)
		
		if(index.x == draggedIndex.x && index.y == draggedIndex.y) {
			pointPosition = vec4(mousePos, 0, 1)
		}

		gl_FragColor = pointPosition
		} 
	  `,
            vert: `
	 #ifdef GL_ES
precision highp float
#endif

attribute vec2 quad
varying vec2 index

void main() {
    index = (quad + 1.0) / 2.0
    gl_Position = vec4(quad, 0, 1)
}
 
	  `,
            framebuffer: () => points?.currentPositionFbo as regl.Framebuffer2D,
            primitive: 'triangle strip',
            count: 4,
            attributes: {quad: createQuadBuffer(reglInstance)},
            uniforms: {
                position: () => points?.previousPositionFbo,
                draggedIndex: () => [index * 4, index * 4 + 1],
                mousePos: () => store!.mousePosition
            },
        })
    }

    public run(): void {
        this.runCommand?.()
    }
}
