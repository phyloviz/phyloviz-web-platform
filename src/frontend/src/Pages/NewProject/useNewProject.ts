import {useNavigate} from "react-router-dom";
import * as React from "react";
import {useState} from "react";
import {WebUiUris} from "../../Utils/navigation/WebUiUris";
import {AdministrationService} from "../../Services/administration/AdministrationService";
import {CreateProjectInputModel} from "../../Services/administration/models/createProject/CreateProjectInputModel";

/**
 * Hook for the NewProject page.
 */
export function useNewProject() {
    const navigate = useNavigate();

    const [projectName, setProjectName] = useState<string | null>(null);
    const [projectDescription, setProjectDescription] = useState<string | null>(null);

    const [error, setError] = useState<string | null>(null);

    return {
        handleProjectNameChange: (event: React.ChangeEvent<HTMLInputElement>) => {
            setProjectName(event.target.value);
        },
        handleProjectDescriptionChange: (event: React.ChangeEvent<HTMLInputElement>) => {
            setProjectDescription(event.target.value);
        },
        handleSubmit: () => {
            if (projectName == null || projectName === "" || projectDescription == null || projectDescription === "") {
                setError("Please fill out all fields");
                return;
            }

            navigate(WebUiUris.project("test")); // TODO: Remove this when the backend is ready

            AdministrationService.createProject({
                    name: projectName,
                    description: projectDescription
                } as CreateProjectInputModel
            )
                .then(res => {
                    navigate(WebUiUris.project(res.projectId));
                })
                .catch(err => {
                    setError(err.message);
                });
        },
        handleCancel: () => navigate(WebUiUris.HOME),
        error
    }
}