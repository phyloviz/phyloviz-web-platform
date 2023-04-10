import * as React from "react"
import Paper from "@mui/material/Paper"
import Typography from "@mui/material/Typography"
import {Container} from "@mui/material"
import Box from "@mui/material/Box"
import {CreateDatasetCard} from "../../Components/Project/CreateDataset/CreateDatasetCard"
import {UploadFilesCard} from "../../Components/Project/UploadFiles/UploadFilesCard"
import {GetProjectOutputModel} from "../../Services/administration/models/getProject/GetProjectOutputModel"

/**
 * Props for the EmptyProject page.
 *
 * @property project The project.
 */
interface EmptyProjectProps {
    project: GetProjectOutputModel | null
}

/**
 * EmptyProject page.
 */
export default function EmptyProject({project}: EmptyProjectProps) {
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
                    {project?.name}
                </Typography>
                <Typography component="h1" variant="body1" textAlign={"justify"}>
                    {project?.description}
                </Typography>
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
