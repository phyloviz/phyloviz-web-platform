import {useState} from "react"
import {useNavigate, useParams} from "react-router-dom"
import {SelectChangeEvent} from "@mui/material"
import {useProjectContext} from "../../useProject";
import {useCompute} from "../useCompute";

export enum ComputeTreeViewLayout {
    FORCE_DIRECTED = "Force Directed",
    RADIAL = "Radial",
    RECTANGULAR = "Rectangular",
}

/**
 * Hook for that handles the compute of a tree view.
 */
export function useComputeTreeView(layout: ComputeTreeViewLayout) {
    const navigate = useNavigate()
    const {datasetId} = useParams<{ datasetId: string }>()
    const {project} = useProjectContext()

    const [selectedTree, setSelectedTree] = useState<string | null>(null)
    const trees = project?.datasets
        .find((dataset) => dataset.datasetId === datasetId)
        ?.trees ?? []

    const [triedSubmitting, setTriedSubmitting] = useState<boolean>(false)

    const {createWorkflow} = useCompute()
    const [error, setError] = useState<string | null>(null)

    /**
     * Returns the layout string for the selected layout.
     */
    function getLayout(): string {
        switch (layout) {
            case ComputeTreeViewLayout.FORCE_DIRECTED:
                return "force-directed"
            case ComputeTreeViewLayout.RADIAL:
                return "radial"
            case ComputeTreeViewLayout.RECTANGULAR:
                return "rectangular"
            default:
                throw new Error("Invalid layout.")
        }
    }

    return {
        trees,
        selectedTree,
        handleTreeChange: (event: SelectChangeEvent) => setSelectedTree(event.target.value),

        handleCancel: () => navigate(-1),
        handleCompute: () => {
            setError(null)

            setTriedSubmitting(true)
            if (selectedTree === null) {
                setError("Please select a tree.")
                return
            }
            setTriedSubmitting(false)

            createWorkflow(
                {
                    type: "compute-tree-view",
                    properties: {
                        datasetId: datasetId,
                        treeId: selectedTree,
                        layout: getLayout()
                    }
                }
            )
        },
        triedSubmitting,
        error,
        clearError: () => setError(null)
    }
}