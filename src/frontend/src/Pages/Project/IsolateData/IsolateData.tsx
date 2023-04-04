import * as React from "react"
import Paper from "@mui/material/Paper"
import Typography from "@mui/material/Typography"
import {Container} from "@mui/material"

/**
 * IsolateData page.
 */
export default function IsolateData() {
    // const { } = useIsolateData()

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
                    Isolate Data
                </Typography>
                {
                    // TODO: Add isolate data information
                }
            </Paper>
        </Container>
    )
}
