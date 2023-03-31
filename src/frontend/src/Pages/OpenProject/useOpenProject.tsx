import {useEffect, useState} from "react";
import {ProjectModel} from "../../Services/administration/models/ProjectModel";
import {AdministrationService} from "../../Services/administration/AdministrationService";

/**
 * Hook for the OpenProject page.
 */
export function useOpenProject() {
    const [projects, setProjects] = useState<ProjectModel[]>([]);

    useEffect(() => {
        AdministrationService.getProjects()
            .then((res) => {
                setProjects(res.projects);
            })
            .catch((err) => {
                console.log(err); // TODO: Handle error
            });
    }, []);

    return {
        projects,
        handleOpenProject: (projectId: string) => {
            // TODO: Navigate to project page
        }
    }
}