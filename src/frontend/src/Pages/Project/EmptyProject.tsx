import * as React from "react"
import Paper from "@mui/material/Paper";
import Typography from "@mui/material/Typography";
import {Container} from "@mui/material";
import Box from "@mui/material/Box";
import {CreateDatasetCard} from "../../Components/Project/CreateDataset/CreateDatasetCard";
import {UploadFilesCard} from "../../Components/Project/UploadFiles/UploadFilesCard";

interface EmptyProjectProps {
    projectName: string;
    projectDescription: string;
}

/**
 * EmptyProject page.
 */
export default function EmptyProject({projectName, projectDescription}: EmptyProjectProps) {
    return (
        <Container>
            <Paper sx={{
                p: 4,
                display: "flex",
                flexDirection: "column",
                marginTop: 4,
                alignItems: "center",
                width: "100%"
            }}>
                <Typography component="h1" variant="h4">
                    {projectName}
                </Typography>
                <Typography component="h1" variant="h6">
                    {projectDescription}
                </Typography>
                <Box sx={{
                    width: "100%",
                    display: "flex",
                    flexDirection: "row",
                    justifyContent: "space-between",
                    mt: 4
                }}>
                    <CreateDatasetCard/>
                    <UploadFilesCard/>
                </Box>
            </Paper>
        </Container>
    );
}
