import * as React from "react"
import Paper from "@mui/material/Paper"
import Typography from "@mui/material/Typography"
import {Container} from "@mui/material"
import Box from "@mui/material/Box"
import {CreateDatasetCard} from "../../Components/Project/EmptyProject/CreateDatasetCard"
import {UploadFilesCard} from "../../Components/Project/EmptyProject/UploadFilesCard"
import {Project} from "../../Services/Administration/models/projects/getProject/GetProjectOutputModel"
import LoadingSpinner from "../../Components/Shared/LoadingSpinner"
import {ErrorAlert} from "../../Components/Shared/ErrorAlert"
import IconButton from "@mui/material/IconButton";
import {Edit} from "@mui/icons-material";

/**
 * Props for the EmptyProject page.
 *
 * @property project The project.
 * @property loading Whether the project is loading.
 * @property handleEditProject Handles the edit project button.
 * @property error The error message.
 * @property clearError Clears the error message.
 */
interface EmptyProjectProps {
    project: Project | null
    loading: boolean
    handleEditProject: () => void
    error: string | null,
    clearError: () => void
}

/**
 * EmptyProject page.
 */
export default function EmptyProject(
    {
        project,
        loading,
        handleEditProject,
        error,
        clearError
    }: EmptyProjectProps
) {
    return (
        <Container>
            <Paper sx={{
                p: 4,
                display: "flex",
                flexDirection: "column",
                mt: 4,
                alignItems: "center",
                width: "100%",
                position: "relative"
            }}>
                <Typography component="h1" variant="h4">
                    {project?.name ?? "Loading..."}
                </Typography>
                <Typography component="h1" variant="body1" textAlign={"justify"}>
                    {project?.description ?? "Loading..."}
                </Typography>
                {loading && <LoadingSpinner text={"Loading dataset..."}/>}
                <ErrorAlert error={error} clearError={clearError}/>
                <IconButton
                    disabled={loading}
                    onClick={handleEditProject}
                    sx={{
                        position: "absolute",
                        top: 2,
                        right: 2
                    }}>
                    <Edit/>
                </IconButton>

                <Box sx={{
                    width: "100%",
                    display: "flex",
                    flexDirection: "row",
                    justifyContent: "space-between",
                    mt: 8
                }}>
                    <CreateDatasetCard/>
                    <UploadFilesCard/>
                </Box>
            </Paper>
        </Container>
    )
}
