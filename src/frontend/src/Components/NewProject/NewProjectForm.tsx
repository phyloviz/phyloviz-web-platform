import {TextField} from "@mui/material";
import Typography from "@mui/material/Typography";
import * as React from "react";

/**
 * Form for the new project page.
 */
export function NewProjectForm() {
    return (
        <>
            <TextField label="Project Name" variant="outlined" required sx={{
                width: "100%", mb: 1
            }}/>
            <Typography variant="caption" align={"justify"} sx={{mb: 4, width: "100%"}}>
                Choose a Project name to identify your analysis.
            </Typography>

            <TextField
                label="Description"
                variant="outlined"
                multiline
                rows={4}
                required
                sx={{
                    width: "100%", mb: 1
                }}/>
            <Typography variant="caption" align={"justify"} sx={{mb: 4, width: "100%"}}>
                Describe your project.
            </Typography>
        </>
    );
}