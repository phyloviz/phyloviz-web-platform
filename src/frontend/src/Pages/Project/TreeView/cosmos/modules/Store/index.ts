import { Store } from '@cosmograph/cosmos/dist/modules/Store'

type Dragged<Node> = { node: Node; index: number; position: [number, number] }

export class CustomStore<N> extends Store<N> {
	public draggedNode: Dragged<N> | undefined = undefined
	public draggedX: number | undefined = undefined;
	public draggedY: number | undefined = undefined;
}
