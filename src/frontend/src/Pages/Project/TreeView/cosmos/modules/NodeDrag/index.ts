import regl from 'regl'
import { vec2 } from 'gl-matrix'
import { GraphData } from '../GraphData'
import { Points } from '../Points'
import { createQuadBuffer } from '../Shared/buffer'
import { Store } from '../Store'
import vert from '../Shared/quad.vert'
import frag from './nodedrag.frag'
import { GraphConfigInterface } from '../../config'
import { CosmosInputNode, CosmosInputLink } from '../../types'

export class NodeDrag<N extends CosmosInputNode, L extends CosmosInputLink> {
	private runCommand: regl.DrawCommand | undefined

	public readonly reglInstance: regl.Regl
	public readonly config: GraphConfigInterface<N, L>
	public readonly store: Store<N>
	public readonly data: GraphData<N, L>
	public readonly points: Points<N, L> | undefined

	public constructor(
		reglInstance: regl.Regl,
		config: GraphConfigInterface<N, L>,
		store: Store<N>,
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
		const { reglInstance, config, store, points } = this
		const pointsTextureSize = store.pointsTextureSize

		this.runCommand = reglInstance({
			frag: `
			#ifdef GL_ES
			precision highp float;
			#endif

			uniform sampler2D position;
			uniform vec2 draggedIndex;
			uniform vec2 mousePos;
			varying vec2 index;

			void main() {
		  		vec4 pointPosition = texture2D(position, index);
				
				if(gl_FragCoord.x - 0.5 == draggedIndex.x && gl_FragCoord.y -0.5 == draggedIndex.y) {
					pointPosition = vec4(mousePos, 0.0, 1.0);
				}

				gl_FragColor = pointPosition;
			}
			`,
			vert,
			framebuffer: () => points?.currentPositionFbo as regl.Framebuffer2D,
			primitive: 'triangle strip',
			count: 4,
			attributes: { quad: createQuadBuffer(reglInstance) },
			uniforms: {
				position: () => {
					return points?.previousPositionFbo
				},
				draggedIndex: () => {
					const sortedIndex = this.data.getSortedIndexById(store.draggedNode?.node.id)!

					return [sortedIndex % pointsTextureSize, Math.floor(sortedIndex / pointsTextureSize)]
				},
				mousePos: () => store!.mousePosition

			},
		})
	}

	public run(): void {
		this.runCommand?.()
	}
}
