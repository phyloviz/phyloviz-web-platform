import regl from 'regl'
import {CoreModule} from '../../modules/core-module'
import forceFrag from '../../modules/ForceMouse/force-mouse.frag'
import {createQuadBuffer} from '../../modules/Shared/buffer'
import updateVert from '../../modules/Shared/quad.vert'
import {CosmosInputLink, CosmosInputNode} from '../../types'

export class ForceMouse<N extends CosmosInputNode, L extends CosmosInputLink> extends CoreModule<N, L> {
    private runCommand: regl.DrawCommand | undefined

    public initPrograms(): void {
        const {reglInstance, config, store, points} = this
        this.runCommand = reglInstance({
            frag: forceFrag,
            vert: updateVert,
            framebuffer: () => points?.velocityFbo as regl.Framebuffer2D,
            primitive: 'triangle strip',
            count: 4,
            attributes: {quad: createQuadBuffer(reglInstance)},
            uniforms: {
                position: () => points?.previousPositionFbo,
                mousePos: () => store.mousePosition,
                repulsion: () => config.simulation?.repulsionFromMouse,
            },
        })
    }

    public run(): void {
        this.runCommand?.()
    }
}
