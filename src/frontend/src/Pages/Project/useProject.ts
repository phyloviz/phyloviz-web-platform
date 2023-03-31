import {useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import {GetProjectOutputModel} from "../../Services/administration/models/getProject/GetProjectOutputModel";
import {AdministrationService} from "../../Services/administration/AdministrationService";

/**
 * Hook for the Project page.
 */
export function useProject() {
    const {projectId} = useParams<{ projectId: string }>();
    const [project, setProject] = useState<GetProjectOutputModel | null>(null);

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
    }, []);

    return {
        project
    }
}