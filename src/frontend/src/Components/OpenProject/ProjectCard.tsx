import {ProjectModel} from "../../Services/administration/models/ProjectModel"
import Typography from "@mui/material/Typography"
import * as React from "react"
import Paper from "@mui/material/Paper"
import Box from "@mui/material/Box"
import {Delete, OpenInNew} from "@mui/icons-material"
import {DeleteResourceBackdrop, useDeleteResourceBackdrop} from "../Shared/DeleteResourceBackdrop"
import IconButton from "@mui/material/IconButton";
import Tooltip from "@mui/material/Tooltip";

/**
 * Props for the ProjectCard component.
 *
 * @property project The project to display.
 * @property handleOpenProject Callback for when the user wants to open the project.
 * @property handleDeleteProject Callback for when the user wants to delete the project.
 */
interface ProjectCardProps {
    project: ProjectModel
    handleOpenProject: () => void
    handleDeleteProject: () => void
    error: string | null
    clearError: () => void
}

/**
 * Component for displaying the details of a project.
 */
export function ProjectCard({project, handleOpenProject, handleDeleteProject, error, clearError}: ProjectCardProps) {
    const {deleteBackdropOpen, handleDeleteBackdropOpen, handleDeleteBackdropClose} = useDeleteResourceBackdrop()

    return (
        <Paper sx={{
            p: 4,
            mt: 2,
            display: "flex",
            flexDirection: "row",
            width: "100%",
            justifyContent: "space-between",
            boxShadow: 12
        }}>
            <Box sx={{
                display: "flex",
                flexDirection: "column",
                width: "90%",
            }}>
                <Typography component="h1" variant="h5" textAlign="justify">
                    {project.name}
                </Typography>
                <Typography component="h1" variant="body1" textAlign="justify">
                    {project.description}
                </Typography>
            </Box>
            <Box sx={{
                display: "flex",
                flexDirection: "row",
                width: "10%",
                justifyContent: "space-between",
            }}>
                <Tooltip title="Open">
                    <IconButton onClick={handleOpenProject} color={"primary"}>
                        <OpenInNew/>
                    </IconButton>
                </Tooltip>

                <Tooltip title="Delete">
                    <IconButton color={"error"} onClick={handleDeleteBackdropOpen}>
                        <Delete/>
                    </IconButton>
                </Tooltip>
            </Box>
            <DeleteResourceBackdrop
                open={deleteBackdropOpen}
                title={"Delete Project?"}
                subheader={"Are you sure you want to delete this project? This action cannot be undone."}
                handleClose={handleDeleteBackdropClose}
                handleDelete={handleDeleteProject}
                error={error}
                clearError={clearError}
            />
        </Paper>
    )
}
