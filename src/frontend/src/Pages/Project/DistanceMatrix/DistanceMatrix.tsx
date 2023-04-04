import * as React from "react"
import Paper from "@mui/material/Paper"
import Typography from "@mui/material/Typography"
import {Container} from "@mui/material"

/**
 * DistanceMatrix page.
 */
export default function DistanceMatrix() {
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
                    Distance Matrix
                </Typography>
                {
                    // TODO: Add distance matrix information
                }
            </Paper>
        </Container>
    )
}
