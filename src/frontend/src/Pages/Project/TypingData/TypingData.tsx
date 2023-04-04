import * as React from "react"
import Paper from "@mui/material/Paper"
import Typography from "@mui/material/Typography"
import {Container} from "@mui/material"

/**
 * TypingData page.
 */
export default function TypingData() {
    // const { } = useTypingData()

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
                    Typing Data
                </Typography>
                {
                    // TODO: Add typing data information
                }
            </Paper>
        </Container>
    )
}
