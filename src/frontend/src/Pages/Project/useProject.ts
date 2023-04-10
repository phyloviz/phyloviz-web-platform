import {useOutletContext, useParams} from "react-router-dom"
import {useEffect, useState} from "react"
import {GetProjectOutputModel} from "../../Services/administration/models/getProject/GetProjectOutputModel"
import {AdministrationService} from "../../Services/administration/AdministrationService"

/**
 * Context for the Project page.
 *
 * @property project the project to display
 * @property onProjectUpdate callback to update the project
 */
interface ProjectContext {
    project: GetProjectOutputModel | null,
    onProjectUpdate: () => void
}


/**
 * Hook for the Project page.
 */
export function useProject() {
    const {projectId} = useParams<{ projectId: string }>()
    const [project, setProject] = useState<GetProjectOutputModel | null>(null)
    const [update, setUpdate] = useState<boolean>(false)

    useEffect(() => {
        if (projectId === undefined)
            throw new Error("Project id is undefined")

        AdministrationService.getProject(projectId)
            .then((res) => {
                setProject(res)
            })
            .catch((err) => {
                console.log(err)
            })
    }, [projectId, update])

    return {
        project,
        onProjectUpdate: () => setUpdate(!update)
    }
}

/**
 * Hook to use the project context.
 */
export function useProjectContext() {
    return useOutletContext<ProjectContext>()
}