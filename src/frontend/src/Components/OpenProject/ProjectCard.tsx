import {ProjectModel} from "../../Services/administration/models/ProjectModel"
import Typography from "@mui/material/Typography"
import {Button} from "@mui/material"
import * as React from "react"
import Paper from "@mui/material/Paper"
import Box from "@mui/material/Box"
import {OpenInNew} from "@mui/icons-material"

/**
 * Props for the ProjectCard component.
 *
 * @property project The project to display.
 * @property handleOpenProject Callback for when the user wants to open the project.
 */
interface ProjectCardProps {
    project: ProjectModel
    handleOpenProject: (projectId: string) => void
}

/**
 * Component for displaying the details of a project.
 */
export function ProjectCard({project, handleOpenProject}: ProjectCardProps) {
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
                width: "80%",
            }}>
                <Typography component="h1" variant="h5" textAlign="justify">
                    {project.name}
                </Typography>
                <Typography component="h1" variant="body1" textAlign="justify">
                    {project.description}
                </Typography>
            </Box>
            <Button
                variant="contained"
                onClick={() => handleOpenProject(project.projectId)}
                startIcon={<OpenInNew/>}
                sx={{maxHeight: "40px"}}
            >
                Open Project
            </Button>
        </Paper>
    )
}