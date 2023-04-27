import {useState} from "react"
import {useNavigate, useParams} from "react-router-dom"
import {SelectChangeEvent} from "@mui/material"
import {useProjectContext} from "../../useProject";
import {useCompute} from "../useCompute";

export enum ComputeTreeViewStep {
    TREE = "Tree",
    LAYOUT = "Layout",
}

export enum ComputeTreeViewLayout {
    FORCE_DIRECTED = "Force Directed",
    RADIAL = "Radial",
    DENDROGRAM = "Dendrogram",
}

/**
 * Hook for the ComputeTreeView page.
 */
export function useComputeTreeView() {
    const [step, setStep] = useState(ComputeTreeViewStep.TREE)
    const [currStep, setCurrStep] = useState(0)

    const navigate = useNavigate()
    const {datasetId} = useParams<{ datasetId: string }>()
    const {project} = useProjectContext()

    const [selectedTree, setSelectedTree] = useState<string | null>(null)
    const trees = project?.datasets
        .find((dataset) => dataset.datasetId === datasetId)
        ?.trees ?? []

    const [selectedLayout, setSelectedLayout] = useState<string | null>(null)

    const {createWorkflow} = useCompute()
    const [error, setError] = useState<string | null>(null)

    /**
     * Returns the layout string for the selected layout.
     */
    function getLayout(): string {
        switch (selectedLayout) {
            case ComputeTreeViewLayout.FORCE_DIRECTED:
                return "force-directed"
            case ComputeTreeViewLayout.RADIAL:
                return "radial"
            case ComputeTreeViewLayout.DENDROGRAM:
                return "dendrogram"
            default:
                throw new Error("Invalid layout.")
        }
    }

    return {
        step,
        currStep,

        trees,
        selectedTree,
        handleTreeChange: (event: SelectChangeEvent) => setSelectedTree(event.target.value),

        selectedLayout,
        handleLayoutChange: (event: SelectChangeEvent) => setSelectedLayout(event.target.value),

        handleCancel: () => navigate(-1),
        handleBack: () => {
            if (step === ComputeTreeViewStep.LAYOUT) {
                setStep(ComputeTreeViewStep.TREE)
                setCurrStep(0)
            }
        },
        handleNext: () => {
            if (step === ComputeTreeViewStep.TREE) {
                setStep(ComputeTreeViewStep.LAYOUT)
                setCurrStep(1)
                return
            }

            if (selectedTree === null) {
                setError("Please select a tree.")
                return
            }

            if (selectedLayout === null) {
                setError("Please select a layout.")
                return
            }

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
        error,
        clearError: () => setError(null)
    }
}