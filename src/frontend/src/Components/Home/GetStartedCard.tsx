import Paper from "@mui/material/Paper"
import Typography from "@mui/material/Typography"
import {Button} from "@mui/material"
import * as React from "react"
import {WebUiUris} from "../../Pages/WebUiUris"
import {Info} from "@mui/icons-material";

/**
 * Card for the get started section in the home page.
 */
export function GetStartedCard() {
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
                <strong>Get Started</strong>
            </Typography>
            <Typography component="h1" variant="body1">
                If you are new to PHYLOViZ Web Platform, read our documentation to learn how to use it.
            </Typography>
            <Button
                variant="contained"
                startIcon={<Info/>}
                onClick={() => window.location.href = WebUiUris.GET_STARTED}
                sx={{mt: 4, width: "75%"}}
            >
                Get Started
            </Button>
        </Paper>
    )
}