import * as React from "react"
import Paper from "@mui/material/Paper"
import Typography from "@mui/material/Typography"
import {Container} from "@mui/material"
import Box from "@mui/material/Box"
import {CreateDatasetCard} from "../../Components/Project/EmptyProject/CreateDatasetCard"
import {UploadFilesCard} from "../../Components/Project/EmptyProject/UploadFilesCard"
import {Project} from "../../Services/administration/models/getProject/GetProjectOutputModel"
import LoadingSpinner from "../../Components/Shared/LoadingSpinner";
import Alert from "@mui/material/Alert";

/**
 * Props for the EmptyProject page.
 *
 * @property project The project.
 * @property loading Whether the project is loading.
 * @property error The error message.
 */
interface EmptyProjectProps {
    project: Project | null
    loading: boolean
    error: string | null
}

/**
 * EmptyProject page.
 */
export default function EmptyProject(
    {
        project,
        loading,
        error
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
                width: "100%"
            }}>
                <Typography component="h1" variant="h4">
                    {project?.name ?? "Loading..."}
                </Typography>
                <Typography component="h1" variant="body1" textAlign={"justify"}>
                    {project?.description ?? "Loading..."}
                </Typography>
                {
                    loading && <LoadingSpinner text={"Loading dataset..."}/>
                }
                {
                    error && <Alert severity="error">{error}</Alert>
                }
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
