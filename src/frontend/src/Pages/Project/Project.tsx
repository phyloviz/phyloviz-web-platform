import * as React from "react"
import {useEffect, useState} from "react"
import {useOutlet, useParams} from "react-router-dom";
import {AdministrationService} from "../../Services/administration/AdministrationService";
import {FileManager} from "../../Components/Project/FileManager/FileManager";
import Box from "@mui/material/Box";
import EmptyProject from "./EmptyProject";
import {GetProjectOutputModel} from "../../Services/administration/models/getProject/GetProjectOutputModel";

/**
 * Project page.
 */
export default function Project() {
    const {projectId} = useParams<{ projectId: string }>();
    const outlet = useOutlet();
    const [project, setProject] = useState<GetProjectOutputModel | null>(null);

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
            <FileManager project={project}/>
            {outlet || <EmptyProject
                projectName={"Project Name"}
                projectDescription={"Project Description"}
            />}
        </Box>
    );
}
