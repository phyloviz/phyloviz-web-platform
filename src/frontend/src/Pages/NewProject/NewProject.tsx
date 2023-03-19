import * as React from "react"
import Paper from "@mui/material/Paper";
import Typography from "@mui/material/Typography";

/**
 * NewProject page.
 */
export default function NewProject() {
    return (
        <Paper sx={{
            p: 4,
            display: "flex",
            flexDirection: "column",
            marginTop: 4,
            alignItems: "center"
        }}>
            <Typography component="h1" variant="h4">
                New Project
            </Typography>
            {/*Add content*/}
        </Paper>
    );
}
