import {useState} from "react"
import {useNavigate, useParams} from "react-router-dom"
import {SelectChangeEvent} from "@mui/material"
import {useProjectContext} from "../../../useProject"
import {useCompute} from "../../useCompute"

export enum HierarchicalClusteringConfigStep {
    DISTANCE = "Distance",
    METHOD = "Method",
}

export enum HierarchicalClusteringMethod {
    COMPLETE_LINKAGE = "Complete-Linkeage",
    SINGLE_LINKAGE = "Single-Linkage",
    UPGMA = "UPGMA",
    WPGMA = "WPGMA",
}

/**
 * Hook for the HierarchicalClusteringConfig page.
 */
export function useHierarchicalClusteringConfig() {
    const [step, setStep] = useState(HierarchicalClusteringConfigStep.DISTANCE)
    const [currStep, setCurrStep] = useState(0)

    const navigate = useNavigate()
    const {datasetId} = useParams<{ datasetId: string }>()
    const {project} = useProjectContext()

    const [selectedDistance, setSelectedDistance] = useState<string | null>(null)
    const distances = project?.datasets
        .find((dataset) => dataset.datasetId === datasetId)
        ?.distanceMatrices ?? []

    const [selectedMethod, setSelectedMethod] = useState<string | null>(null)

    const [triedSubmitting, setTriedSubmitting] = useState<boolean>(false)

    const {createWorkflow} = useCompute()
    const [error, setError] = useState<string | null>(null)

    /**
     * Returns the algorithm string for the selected method.
     */
    function getAlgorithm(): string {
        switch (selectedMethod) {
            case HierarchicalClusteringMethod.COMPLETE_LINKAGE:
                return "cl"
            case HierarchicalClusteringMethod.SINGLE_LINKAGE:
                return "sl"
            case HierarchicalClusteringMethod.UPGMA:
                return "upgma"
            case HierarchicalClusteringMethod.WPGMA:
                return "wpgma"
            default:
                return "cl"
        }
    }

    return {
        step,
        currStep,

        distances,
        selectedDistance,
        handleDistanceChange: (event: SelectChangeEvent) => setSelectedDistance(event.target.value),

        selectedMethod,
        handleMethodChange: (event: SelectChangeEvent) => setSelectedMethod(event.target.value),

        handleCancel: () => navigate(-1),
        handleBack: () => {
            if (step === HierarchicalClusteringConfigStep.METHOD) {
                setStep(HierarchicalClusteringConfigStep.DISTANCE)
                setCurrStep(0)
            }
        },
        handleNext: () => {
            setError(null)

            if (step === HierarchicalClusteringConfigStep.DISTANCE) {
                setTriedSubmitting(true)
                if (selectedDistance === null) {
                    setError("Please select a distance matrix.")
                    return
                }
                setTriedSubmitting(false)

                setStep(HierarchicalClusteringConfigStep.METHOD)
                setCurrStep(1)
                return
            } else if (step === HierarchicalClusteringConfigStep.METHOD) {
                setTriedSubmitting(true)
                if (selectedMethod === null) {
                    setError("Please select a method.")
                    return
                }
                setTriedSubmitting(false)
            }

            createWorkflow(
                {
                    type: "compute-tree-and-index",
                    properties: {
                        datasetId: datasetId,
                        distanceMatrixId: selectedDistance,
                        algorithm: getAlgorithm()
                    }
                }
            )
        },
        triedSubmitting,
        error,
        clearError: () => setError(null)
    }
}