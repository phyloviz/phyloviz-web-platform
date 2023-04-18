import * as React from "react"
import Paper from "@mui/material/Paper"
import Typography from "@mui/material/Typography"
import {Container} from "@mui/material"
import LoadingSpinner from "../../../Components/Shared/LoadingSpinner"
import Alert from "@mui/material/Alert"
import Box from "@mui/material/Box"
import {useDatasetDetails} from "./useDatasetDetails"
import {ComputeDistancesCard} from "../../../Components/Project/DatasetDetails/ComputeDistancesCard"
import {ComputeTreesCard} from "../../../Components/Project/DatasetDetails/ComputeTreesCard"
import {GenerateReportCard} from "../../../Components/Project/DatasetDetails/GenerateReportCard"

/**
 * DatasetDetails page.
 */
export default function DatasetDetails() {
    const {
        dataset,
        loading,
        error
    } = useDatasetDetails()

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
                    {dataset?.name ?? "Loading..."}
                </Typography>
                <Typography component="h1" variant="body1" textAlign={"justify"}>
                    {dataset?.description ?? "Loading..."}
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
                    <ComputeDistancesCard/>
                    <ComputeTreesCard/>
                    <GenerateReportCard/>
                </Box>
            </Paper>
        </Container>
    )
}

