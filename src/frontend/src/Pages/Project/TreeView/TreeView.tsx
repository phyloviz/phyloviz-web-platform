import * as React from "react"
import Paper from "@mui/material/Paper"
import Typography from "@mui/material/Typography"
import {Container} from "@mui/material"

/**
 * TreeView page.
 */
export default function TreeView() {
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
                    Tree View
                </Typography>
                {
                    // TODO: Add tree view information
                }
            </Paper>
        </Container>
    )
}
