import Paper from "@mui/material/Paper"
import Typography from "@mui/material/Typography"
import {Button} from "@mui/material"
import * as React from "react"
import {WebUiUris} from "../../Pages/WebUiUris"
import {Login} from "@mui/icons-material";

/**
 * Card for the sign up section in the home page.
 */
export function SignUpCard() {
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
                <strong>Sign In</strong>
            </Typography>
            <Typography component="h1" variant="body1">
                Sign in to PHYLOViZ Web Platform to start using it. Sign up if you don't have an account yet.
            </Typography>
            <Button
                variant="contained"
                startIcon={<Login/>}
                onClick={() => window.location.href = WebUiUris.LOGIN}
                sx={{mt: 4, width: "75%"}}
            >
                Sign In
            </Button>
        </Paper>
    )
}