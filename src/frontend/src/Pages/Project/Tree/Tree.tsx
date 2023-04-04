import * as React from "react"
import Paper from "@mui/material/Paper"
import Typography from "@mui/material/Typography"
import {Container} from "@mui/material"

/**
 * Tree page.
 */
export default function Tree() {
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
                    Tree
                </Typography>
                {
                    // TODO: Add tree information
                }
            </Paper>
        </Container>
    )
}
