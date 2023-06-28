import * as React from "react"
import Paper from "@mui/material/Paper"
import Typography from "@mui/material/Typography"
import {Button, Container} from "@mui/material"
import {useOpenProject} from "./useOpenProject"
import {ProjectCard} from "../../Components/OpenProject/ProjectCard"
import Box from "@mui/material/Box"
import LoadingSpinner from "../../Components/Shared/LoadingSpinner"
import {ErrorAlert} from "../../Components/Shared/ErrorAlert"
import NewProjectIcon from "@mui/icons-material/CreateNewFolder";
import {WebUiUris} from "../WebUiUris";
import {useNavigate} from "react-router-dom";

/**
 * OpenProject page.
 */
export default function OpenProject() {
    const {
        projects,
        handleOpenProject,
        handleDeleteProject,
        error,
        clearError
    } = useOpenProject()
    const navigate = useNavigate()

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
                <ErrorAlert error={error} clearError={clearError}/>
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
                            <>
                                <Typography component="h1" variant="body1" textAlign="justify">
                                    No projects found.
                                </Typography>
                                <Button
                                    variant="contained"
                                    startIcon={<NewProjectIcon/>}
                                    onClick={() => navigate(WebUiUris.NEW_PROJECT)}
                                    sx={{mt: 4, width: "30%"}}
                                >
                                    New Project
                                </Button>
                            </>
                        )
                    }
                    {
                        projects?.map((project) => {
                            return (
                                <ProjectCard
                                    key={project.projectId}
                                    project={project}
                                    handleOpenProject={() => handleOpenProject(project.projectId)}
                                    handleDeleteProject={() => handleDeleteProject(project.projectId)}
                                    error={error}
                                    clearError={clearError}
                                />
                            )
                        })
                    }
                </Box>
            </Paper>
        </Container>
    )
}
