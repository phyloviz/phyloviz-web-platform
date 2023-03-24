import * as React from "react"
import {useEffect} from "react"
import Paper from "@mui/material/Paper";
import Typography from "@mui/material/Typography";
import {useParams} from "react-router-dom";
import {AdministrationService} from "../../Services/administration/AdministrationService";
import {ProjectModel} from "../../Services/administration/models/ProjectModel";
import {FileManager} from "../../Components/Project/FileManager";

/**
 * Project page.
 */
export default function Project() {
    const {projectId} = useParams<{ projectId: string }>();
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
        <Paper sx={{
            p: 4,
            display: "flex",
            flexDirection: "column",
            marginTop: 4,
            height: "500px",
            alignItems: "center"
        }}>
            <Typography component="h1" variant="h4">
                Project
            </Typography>
            <FileManager/>
            {/*Add content*/}
        </Paper>
    );
}
