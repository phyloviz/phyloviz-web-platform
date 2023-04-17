import {useState} from "react"
import {useNavigate, useParams} from "react-router-dom"
import {SelectChangeEvent} from "@mui/material"
import ComputeService from "../../../../../Services/compute/ComputeService"
import {useProjectContext} from "../../../useProject"

export enum GoeBURSTConfigurationStep {
    DISTANCE = "Distance",
    LEVEL = "Level",
}

/**
 * Hook for the GoeBURST configuration page.
 */
export function useGoeBURSTConfig() {
    const [step, setStep] = useState(GoeBURSTConfigurationStep.DISTANCE)
    const [currStep, setCurrStep] = useState(0)

    const navigate = useNavigate()
    const {projectId, datasetId} = useParams<{ projectId: string, datasetId: string }>()
    const {project, onProjectUpdate} = useProjectContext()

    const [selectedDistance, setSelectedDistance] = useState<string | null>(null)
    const distances = project?.datasets
        .find((dataset) => dataset.datasetId === datasetId)
        ?.distanceMatrices ?? []

    const [workflowId, setWorkflowId] = useState<string | null>(null)

    return {
        step,
        currStep,
        distances,
        selectedDistance,
        handleDistanceChange: (event: SelectChangeEvent) => setSelectedDistance(event.target.value),
        handleCancel: () => navigate(-1),
        handleBack: () => {
            if (step === GoeBURSTConfigurationStep.LEVEL) {
                setStep(GoeBURSTConfigurationStep.DISTANCE)
                setCurrStep(0)
            }
        },
        handleNext: () => {
            if (step === GoeBURSTConfigurationStep.DISTANCE) {
                setStep(GoeBURSTConfigurationStep.LEVEL)
                setCurrStep(1)
            }

            ComputeService.createWorkflow(
                projectId!,
                {
                    type: "compute-tree",
                    properties: {
                        datasetId: datasetId,
                        distanceMatrixId: selectedDistance,
                        algorithm: "goeburst"
                    }
                }
            )
                .then((res) => setWorkflowId(res.workflowId))// TODO: Get status until finished
                .catch((err) => console.error(err)) // TODO: Handle Error
        }
    }
}