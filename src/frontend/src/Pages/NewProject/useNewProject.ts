import {useNavigate} from "react-router-dom"
import * as React from "react"
import {useState} from "react"
import {WebUiUris} from "../WebUiUris"
import AdministrationService from "../../Services/Administration/AdministrationService"
import {
    CreateProjectInputModel
} from "../../Services/Administration/models/projects/createProject/CreateProjectInputModel"

/**
 * Hook for the NewProject page.
 */
export function useNewProject() {
    const navigate = useNavigate()

    const [projectName, setProjectName] = useState<string | null>(null)
    const [projectDescription, setProjectDescription] = useState<string | null>(null)

    const [error, setError] = useState<string | null>(null)

    return {
        handleProjectNameChange: (event: React.ChangeEvent<HTMLInputElement>) => setProjectName(event.target.value),
        handleProjectDescriptionChange: (event: React.ChangeEvent<HTMLInputElement>) => setProjectDescription(event.target.value),
        handleSubmit: () => {
            if (projectName == null || projectName === "" || projectDescription == null || projectDescription === "") {
                setError("Please fill out all fields")
                return
            }

            AdministrationService.createProject({
                    name: projectName,
                    description: projectDescription
                } as CreateProjectInputModel
            )
                .then(res => navigate(WebUiUris.project(res.projectId)))
                .catch(err => setError(err.message))
        },
        handleCancel: () => navigate(-1),
        error,
        clearError: () => setError(null)
    }
}