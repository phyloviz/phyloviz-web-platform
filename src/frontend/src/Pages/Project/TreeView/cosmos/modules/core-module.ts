import regl from 'regl'
import {GraphConfigInterface} from '../config'
import {GraphData} from '../modules/GraphData'
import {Points} from '../modules/Points'
import {Store} from '../modules/Store'
import {CosmosInputLink, CosmosInputNode} from '../types'

export class CoreModule<N extends CosmosInputNode, L extends CosmosInputLink> {
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
}
