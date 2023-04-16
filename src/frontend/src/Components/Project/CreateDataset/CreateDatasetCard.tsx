import Paper from "@mui/material/Paper"
import Typography from "@mui/material/Typography"
import {Button} from "@mui/material"
import * as React from "react"
import {useNavigate, useParams} from "react-router-dom"
import {WebUiUris} from "../../../Pages/WebUiUris"
import {Add} from "@mui/icons-material"

/**
 * Card for the create dataset feature.
 */
export function CreateDatasetCard() {
    const navigate = useNavigate()
    const {projectId} = useParams<{ projectId: string }>()

    return <Paper sx={{
        p: 4,
        display: "flex",
        flexDirection: "column",
        alignItems: "center",
        width: "48%",
        justifyContent: "space-between",
        boxShadow: 12
    }}>
        <Typography component="h1" variant="h5">
            <strong>Create Dataset</strong>
        </Typography>
        <Typography component="h1" variant="body1" sx={{mt: 2}}>
            Create a dataset with your typing data and isolates data, and start your analysis
            in a few seconds.
            The dataset will be stored in this project, and you will be able to access it
            whenever you want.
        </Typography>
        <Button
            variant="contained"
            startIcon={<Add/>}
            onClick={() => navigate(WebUiUris.createDataset(projectId!))}
            sx={{mt: 4, width: "75%"}}
        >
            Create Dataset
        </Button>
    </Paper>
}