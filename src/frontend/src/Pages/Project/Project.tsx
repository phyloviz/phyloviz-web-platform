import * as React from "react"
import {useEffect} from "react"
import {useOutlet, useParams} from "react-router-dom";
import {AdministrationService} from "../../Services/administration/AdministrationService";
import {ProjectModel} from "../../Services/administration/models/ProjectModel";
import {FileManager} from "../../Components/Project/FileManager/FileManager";
import Box from "@mui/material/Box";
import EmptyProject from "./EmptyProject";

/**
 * Project page.
 */
export default function Project() {
    const {projectId} = useParams<{ projectId: string }>();
    const outlet = useOutlet();
    const [project, setProject] = React.useState<ProjectModel | null>(null);

    useEffect(() => {
        //TODO: fetchProject();
    }, []);

    async function fetchProject() {
        if (projectId === undefined)
            throw new Error("Project id is undefined");

        AdministrationService.getProject(projectId)
            .then((res) => {
                setProject(res);
            })
            .catch((err) => {
                console.log(err);
            });
    }

    return (
        <Box sx={{
            display: "flex",
            flexDirection: "row",
            height: '90%',
            width: '100%',
        }}>
            <FileManager/>
            {outlet || <EmptyProject
                projectName={"Project Name"}
                projectDescription={"Project Description"}
            />}
        </Box>
    );
}
