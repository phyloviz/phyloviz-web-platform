import {useNavigate, useParams} from "react-router-dom"
import * as React from "react"
import {useState} from "react"
import {useProjectContext} from "../useProject";
import AdministrationService from "../../../Services/Administration/AdministrationService";
import {WebUiUris} from "../../WebUiUris";
import {
    UpdateDatasetInputModel
} from "../../../Services/Administration/models/datasets/updateDataset/UpdateDatasetInputModel";

/**
 * Hook for the EditDataset page.
 */
export function useEditDataset() {
    const navigate = useNavigate()
    const {project, onFileStructureUpdate} = useProjectContext()
    const {projectId, datasetId} = useParams<{ projectId: string, datasetId: string }>()
    const dataset = project?.datasets.find(d => d.datasetId === datasetId)

    const [newDatasetName, setNewDatasetName] = useState<string>(dataset!.name)
    const [newDatasetDescription, setNewDatasetDescription] = useState<string>(dataset!.description)

    const [error, setError] = useState<string | null>(null)

    return {
        newDatasetName,
        newDatasetDescription,
        handleDatasetNameChange: (event: React.ChangeEvent<HTMLInputElement>) => setNewDatasetName(event.target.value),
        handleDatasetDescriptionChange: (event: React.ChangeEvent<HTMLInputElement>) => setNewDatasetDescription(event.target.value),
        handleSubmit: () => {
            if (newDatasetName == null || newDatasetName === "" || newDatasetDescription == null || newDatasetDescription === "") {
                setError("Please fill out all fields")
                return
            }

            AdministrationService.updateDataset(project?.projectId!, datasetId!, {
                    name: newDatasetName,
                    description: newDatasetDescription
                } as UpdateDatasetInputModel
            )
                .then(() => {
                    navigate(WebUiUris.dataset(projectId!, datasetId!))
                    onFileStructureUpdate()
                })
                .catch(err => setError(err.message))
        },
        handleCancel: () => navigate(-1),
        error,
        clearError: () => setError(null)
    }
}