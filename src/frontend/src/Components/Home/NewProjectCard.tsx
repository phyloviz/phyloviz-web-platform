import Paper from "@mui/material/Paper"
import Typography from "@mui/material/Typography"
import {Button} from "@mui/material"
import NewProjectIcon from "@mui/icons-material/CreateNewFolder"
import * as React from "react"
import {useNavigate} from "react-router-dom"
import {WebUiUris} from "../../Pages/WebUiUris"

/**
 * Card for the new project feature in the home page.
 */
export function NewProjectCard() {
    const navigate = useNavigate()

    return (
        <Paper sx={{
            p: 4,
            display: "flex",
            flexDirection: "column",
            alignItems: "center",
            width: "48%",
            justifyContent: "space-between"
        }}>
            <Typography component="h1" variant="h5">
                <strong>New Project</strong>
            </Typography>
            <Typography component="h1" variant="body1">
                Create a new project, load a dataset <br/> and start exploring your data!
            </Typography>
            <Button
                variant="contained"
                startIcon={<NewProjectIcon/>}
                onClick={() => navigate(WebUiUris.NEW_PROJECT)}
                sx={{mt: 4, width: "75%"}}
            >
                New Project
            </Button>
        </Paper>
    )
}