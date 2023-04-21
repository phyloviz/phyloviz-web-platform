import Paper from "@mui/material/Paper"
import Typography from "@mui/material/Typography"
import {Button} from "@mui/material"
import * as React from "react"
import {useNavigate, useParams} from "react-router-dom"
import {WebUiUris} from "../../../Pages/WebUiUris"
import {Summarize} from "@mui/icons-material"

/**
 * Card for the report generation feature.
 */
export function GenerateReportCard() {
    const navigate = useNavigate()
    const {projectId, datasetId} = useParams<{ projectId: string, datasetId: string }>()


    return <Paper sx={{
        p: 4,
        display: "flex",
        flexDirection: "column",
        alignItems: "center",
        width: "30%",
        justifyContent: "space-between",
        boxShadow: 12
    }}>
        <Typography component="h1" variant="h5">
            <strong>Generate Report</strong>
        </Typography>
        <Typography component="h1" variant="body1" sx={{mt: 2}}>
            Generate a report with the results of your analysis.
        </Typography>
        <Button
            variant="contained"
            startIcon={<Summarize/>}
            onClick={() => navigate(WebUiUris.report(projectId!, datasetId!))}
            sx={{mt: 4, width: "100%"}}
        >
            Generate Report
        </Button>
    </Paper>
}