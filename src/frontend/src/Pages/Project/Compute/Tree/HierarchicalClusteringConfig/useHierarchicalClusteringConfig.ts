import {useState} from "react"
import {useNavigate, useParams} from "react-router-dom"
import {SelectChangeEvent} from "@mui/material";
import {useProjectContext} from "../../../useProject";
import {GoeBURSTConfigurationStep} from "../GoeBURSTConfig/useGoeBURSTConfig";

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
    const {projectId, datasetId} = useParams<{ projectId: string, datasetId: string }>()
    const {project, onProjectUpdate} = useProjectContext()

    const [selectedDistance, setSelectedDistance] = useState<string | null>(null)
    const distances = project?.datasets
        .find((dataset) => dataset.datasetId === datasetId)
        ?.distanceMatrices ?? []

    const [selectedMethod, setSelectedMethod] = useState<string | null>(null)

    const [workflowId, setWorkflowId] = useState<string | null>(null)

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
            if (step === HierarchicalClusteringConfigStep.DISTANCE) {
                setStep(HierarchicalClusteringConfigStep.METHOD)
                setCurrStep(1)
            }
            // TODO: else, finish
        }
    }
}