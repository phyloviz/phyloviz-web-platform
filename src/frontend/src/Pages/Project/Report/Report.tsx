import * as React from "react"
import Paper from "@mui/material/Paper"
import Typography from "@mui/material/Typography"
import {Container} from "@mui/material"
import Alert from "@mui/material/Alert"

/**
 * Report page.
 */
export default function Report() {
    // const { } = useReport()

    return (
        <Container>
            <Paper sx={{
                p: 4,
                display: "flex",
                flexDirection: "column",
                mt: 4,
                alignItems: "center"
            }}>
                <Typography component="h1" variant="h4">
                    Report
                </Typography>
                {
                    // TODO: To be implemented
                }
                <Alert severity="info">
                    This feature is not yet implemented.
                </Alert>
            </Paper>
        </Container>
    )
}
