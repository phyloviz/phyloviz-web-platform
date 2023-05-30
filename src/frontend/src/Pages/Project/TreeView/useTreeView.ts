import {useOutletContext, useParams} from "react-router-dom"
import {TreeView} from "../../../Services/Administration/models/projects/getProject/GetProjectOutputModel"
import {useProjectContext} from "../useProject"
import {useCascadingInfoTreeView} from "./useCascadingInfoTreeView";


/**
 * Hook for the TreeView page.
 */
export function useTreeView() {
    const pathParams = useParams<{ projectId: string, datasetId: string, treeViewId: string }>()
    const datasetId = pathParams.datasetId!
    const treeViewId = pathParams.treeViewId!

    const {project} = useProjectContext()
    const dataset = project?.datasets.find(dataset => dataset.datasetId === datasetId)

    const {cascadingInfoTreeView} = useCascadingInfoTreeView(dataset, treeViewId)

    return {
        treeView: cascadingInfoTreeView,
    }
}

/**
 * Context for the TreeView page.
 *
 * @property treeView - the tree view to display
 * @property typingDataId - the typing data id to filter by
 * @property isolateDataId - the isolate data id to filter by
 */
interface TreeViewContext {
    treeView: TreeView
    typingDataId: string
    isolateDataId?: string
}

/**
 * Hook to use the TreeView context.
 */
export function useTreeViewContext() {
    return useOutletContext<TreeViewContext>()
}
