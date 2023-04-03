import * as React from "react"
import Paper from "@mui/material/Paper";
import Typography from "@mui/material/Typography";
import {Container} from "@mui/material";
import {useOpenProject} from "./useOpenProject";
import {ProjectCard} from "../../Components/OpenProject/ProjectCard";
import Box from "@mui/material/Box";
import LoadingSpinner from "../../Components/Shared/LoadingSpinner";
import Alert from "@mui/material/Alert";

/**
 * OpenProject page.
 */
export default function OpenProject() {
    const {
        projects,
        handleOpenProject,
        error
    } = useOpenProject();

    return (
        <Container>
            <Paper sx={{
                p: 4,
                display: "flex",
                flexDirection: "column",
                mt: 4,
                mb: 4,
                alignItems: "center"
            }}>
                <Typography component="h1" variant="h4">
                    Open Project
                </Typography>
                <Typography component="h1" variant="body1" textAlign="justify">
                    Select a project to open
                </Typography>
                {error && <Alert severity="error" sx={{mt: 2}}>{error}</Alert>}
                <Box sx={{
                    width: "100%",
                    display: "flex",
                    flexDirection: "column",
                    alignItems: "center",
                    justifyContent: "space-between",
                    mt: 4
                }}>
                    {
                        projects == null && (<LoadingSpinner text={"Searching for available projects..."}/>)
                    }
                    {
                        projects?.length == 0 && (
                            <Typography component="h1" variant="body1" textAlign="justify">
                                No projects found.
                            </Typography>
                        )
                    }
                    {
                        projects?.map((project, index) => {
                            return (
                                <ProjectCard
                                    key={index}
                                    project={project}
                                    handleOpenProject={handleOpenProject}
                                />
                            );
                        })
                    }
                </Box>
            </Paper>
        </Container>
    );
}
