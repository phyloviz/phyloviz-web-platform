import * as React from "react"
import Paper from "@mui/material/Paper"
import Typography from "@mui/material/Typography"
import {Container} from "@mui/material"
import LoadingSpinner from "../../../Components/Shared/LoadingSpinner"
import Box from "@mui/material/Box"
import {useDatasetDetails} from "./useDatasetDetails"
import {ComputeDistancesCard} from "../../../Components/Project/DatasetDetails/ComputeDistancesCard"
import {ComputeTreesCard} from "../../../Components/Project/DatasetDetails/ComputeTreesCard"
import {GenerateReportCard} from "../../../Components/Project/DatasetDetails/GenerateReportCard"
import {ErrorAlert} from "../../../Components/Shared/ErrorAlert"
import IconButton from "@mui/material/IconButton";
import {Edit} from "@mui/icons-material";

/**
 * DatasetDetails page.
 */
export default function DatasetDetails() {
    const {
        dataset,
        loading,
        handleEditDataset,
        error,
        clearError
    } = useDatasetDetails()

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
                    {dataset?.name ?? "Loading..."}
                </Typography>
                <Typography component="h1" variant="body1" textAlign={"justify"}>
                    {dataset?.description ?? "Loading..."}
                </Typography>
                {loading && <LoadingSpinner text={"Loading dataset..."}/>}
                <ErrorAlert error={error} clearError={clearError}/>
                <IconButton
                    disabled={loading}
                    onClick={handleEditDataset}
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
                    <ComputeDistancesCard/>
                    <ComputeTreesCard/>
                    <GenerateReportCard/>
                </Box>
            </Paper>
        </Container>
    )
}

