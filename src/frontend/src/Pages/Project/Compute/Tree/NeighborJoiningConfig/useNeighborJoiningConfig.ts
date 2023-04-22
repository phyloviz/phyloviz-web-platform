import {useState} from "react"
import {useNavigate, useParams} from "react-router-dom"
import {useProjectContext} from "../../../useProject"
import {SelectChangeEvent} from "@mui/material"
import {useCompute} from "../../useCompute"

export enum NeighborJoiningConfigurationStep {
    DISTANCE = "Distance",
    METHOD = "Method",
}

export enum NeighborJoiningCriteria {
    SAILOU_AND_NEI = "Sailou-Nei",
    STUDIER_AND_KEPPLER = "Studier-Keppler",
}

/**
 * Hook for the NeighborJoiningConfig page.
 */
export function useNeighborJoiningConfig() {
    const [step, setStep] = useState(NeighborJoiningConfigurationStep.DISTANCE)
    const [currStep, setCurrStep] = useState(0)

    const navigate = useNavigate()
    const {projectId, datasetId} = useParams<{ projectId: string, datasetId: string }>()
    const {project} = useProjectContext()

    const [selectedDistance, setSelectedDistance] = useState<string | null>(null)
    const distances = project?.datasets
        .find((dataset) => dataset.datasetId === datasetId)
        ?.distanceMatrices ?? []

    const [selectedCriteria, setSelectedCriteria] = useState<NeighborJoiningCriteria | null>(null)

    const {createWorkflow} = useCompute()
    const [error, setError] = useState<string | null>(null)

    return {
        step,
        currStep,

        distances,
        selectedDistance,
        handleDistanceChange: (event: SelectChangeEvent) => setSelectedDistance(event.target.value),

        selectedCriteria,
        handleCriteriaChange: (event: SelectChangeEvent) => setSelectedCriteria(event.target.value as NeighborJoiningCriteria),

        handleCancel: () => navigate(-1),
        handleBack: () => {
            if (step === NeighborJoiningConfigurationStep.METHOD) {
                setStep(NeighborJoiningConfigurationStep.DISTANCE)
                setCurrStep(0)
            }
        },
        handleNext: () => {
            if (step === NeighborJoiningConfigurationStep.DISTANCE) {
                setStep(NeighborJoiningConfigurationStep.METHOD)
                setCurrStep(1)
                return
            }

            if (selectedDistance === null) {
                setError("Please select a distance matrix.")
                return
            }

            if (selectedCriteria === null) {
                setError("Please select a criteria.")
                return
            }

            createWorkflow(
                {
                    type: "compute-tree",
                    properties: {
                        datasetId: datasetId,
                        distanceMatrixId: selectedDistance,
                        algorithm: selectedCriteria === NeighborJoiningCriteria.SAILOU_AND_NEI ? "saitounei" : "studierkepler"
                    }
                }
            )
        },
        error,
        clearError: () => setError(null)
    }
}