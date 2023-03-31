import * as React from "react"
import Paper from "@mui/material/Paper";
import Typography from "@mui/material/Typography";
import {Container} from "@mui/material";
import {useOpenProject} from "./useOpenProject";
import {ProjectCard} from "../../Components/OpenProject/ProjectCard";

/**
 * OpenProject page.
 */
export default function OpenProject() {
    const {
        projects,
        handleOpenProject
    } = useOpenProject();

    return (
        <Container>
            <Paper sx={{
                p: 4,
                display: "flex",
                flexDirection: "column",
                mt: 4,
                alignItems: "center"
            }}>
                <Typography component="h1" variant="h4">
                    Open Project
                </Typography>
                {
                    projects.map((project, index) => {
                        return (
                            <ProjectCard
                                key={index}
                                project={project}
                                handleOpenProject={handleOpenProject}
                            />
                        );
                    })
                }
            </Paper>
        </Container>
    );
}
