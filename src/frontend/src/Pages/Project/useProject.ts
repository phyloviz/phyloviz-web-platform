import {useOutletContext, useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import {GetProjectOutputModel} from "../../Services/administration/models/getProject/GetProjectOutputModel";
import {AdministrationService} from "../../Services/administration/AdministrationService";

// TODO: check if its necessary to pass the entire project object to the context
// or use other hook to get the project object
type ContextType = {
    project: GetProjectOutputModel | null,
    onUpdated: () => void
};


/**
 * Hook for the Project page.
 */
export function useProject() {
    const {projectId} = useParams<{ projectId: string }>();
    const [project, setProject] = useState<GetProjectOutputModel | null>(null);
    const [update, setUpdate] = useState<boolean>(false);

    useEffect(() => {
        if (projectId === undefined)
            throw new Error("Project id is undefined");

        AdministrationService.getProject(projectId)
            .then((res) => {
                setProject(res);
            })
            .catch((err) => {
                console.log(err);
            });
    }, [projectId, update]);

    return {
        project,
        onUpdated: () => setUpdate(!update)
    }
}

/**
 * Hook to use the project context.
 */
export function useProjectContext() {
    return useOutletContext<ContextType>();
}