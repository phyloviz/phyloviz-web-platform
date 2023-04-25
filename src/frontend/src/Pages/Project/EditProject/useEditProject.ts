import {useNavigate} from "react-router-dom"
import * as React from "react"
import {useState} from "react"
import {useProjectContext} from "../useProject";
import {
    UpdateProjectInputModel
} from "../../../Services/Administration/models/projects/updateProject/UpdateProjectInputModel";
import AdministrationService from "../../../Services/Administration/AdministrationService";
import {WebUiUris} from "../../WebUiUris";

/**
 * Hook for the EditProject page.
 */
export function useEditProject() {
    const navigate = useNavigate()
    const {project, onFileStructureUpdate} = useProjectContext()

    const [newProjectName, setProjectName] = useState<string>(project!.name)
    const [newProjectDescription, setProjectDescription] = useState<string>(project!.description)

    const [error, setError] = useState<string | null>(null)

    return {
        newProjectName,
        newProjectDescription,
        handleProjectNameChange: (event: React.ChangeEvent<HTMLInputElement>) => setProjectName(event.target.value),
        handleProjectDescriptionChange: (event: React.ChangeEvent<HTMLInputElement>) => setProjectDescription(event.target.value),
        handleSubmit: () => {
            if (newProjectName == null || newProjectName === "" || newProjectDescription == null || newProjectDescription === "") {
                setError("Please fill out all fields")
                return
            }

            AdministrationService.updateProject(project?.projectId!, {
                    name: newProjectName,
                    description: newProjectDescription
                } as UpdateProjectInputModel
            )
                .then(() => {
                    navigate(WebUiUris.project(project?.projectId!))
                    onFileStructureUpdate()
                })
                .catch(err => setError(err.message))
        },
        handleCancel: () => navigate(-1),
        error,
        clearError: () => setError(null)
    }
}