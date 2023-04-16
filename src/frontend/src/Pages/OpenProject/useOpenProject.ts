import {useEffect, useState} from "react"
import {ProjectModel} from "../../Services/administration/models/ProjectModel"
import {AdministrationService} from "../../Services/administration/AdministrationService"
import {useNavigate} from "react-router-dom"
import {WebUiUris} from "../WebUiUris"

/**
 * Hook for the OpenProject page.
 */
export function useOpenProject() {
    const [projects, setProjects] = useState<ProjectModel[] | null>(null)
    const navigate = useNavigate()
    const [error, setError] = useState<string | null>(null)

    useEffect(() => {
        AdministrationService.getProjects()
            .then((res) => {
                setProjects(res.projects)
            })
            .catch((err) => {
                setError(err.message)
            })
    }, [])

    return {
        projects,
        handleOpenProject: (projectId: string) => navigate(WebUiUris.project(projectId)),
        error
    }
}