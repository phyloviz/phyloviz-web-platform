import * as React from "react"
import Paper from "@mui/material/Paper";
import Typography from "@mui/material/Typography";

/**
 * OpenProject page.
 */
export default function OpenProject() {
    return (
        <Paper sx={{
            p: 4,
            display: "flex",
            flexDirection: "column",
            marginTop: 4,
            alignItems: "center"
        }}>
            <Typography component="h1" variant="h4">
                Open Project
            </Typography>
            {/*Add content*/}
        </Paper>
    );
}
