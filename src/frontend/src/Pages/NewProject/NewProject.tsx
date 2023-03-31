import * as React from "react"
import Paper from "@mui/material/Paper";
import Typography from "@mui/material/Typography";
import Box from "@mui/material/Box";
import {Button, Container, TextField} from "@mui/material";
import CancelIcon from "@mui/icons-material/Cancel";
import FinishIcon from "@mui/icons-material/Done";
import {Alert} from "@mui/lab";
import {useNewProject} from "./useNewProject";

/**
 * NewProject page.
 */
export default function NewProject() {
    const {
        handleProjectNameChange,
        handleProjectDescriptionChange,
        handleSubmit,
        handleCancel,
        error
    } = useNewProject();

    return (
        <Container>
            <Box
                display="flex"
                justifyContent="center"
            >
                <Paper sx={{
                    p: 4,
                    display: "flex",
                    flexDirection: "column",
                    mt: 4,
                    alignItems: "center",
                    width: "50%"
                }}>
                    <Typography component="h1" variant="h4">
                        New Project
                    </Typography>
                    <Box sx={{
                        width: "100%",
                        display: "flex",
                        flexDirection: "column",
                        justifyContent: "left",
                        mt: 4
                    }}>
                        <Box sx={{
                            width: "100%",
                            display: "flex",
                            flexDirection: "column",
                            justifyContent: "left",
                        }}>
                            <TextField
                                label="Project Name"
                                variant="outlined"
                                required
                                onChange={handleProjectNameChange}
                                sx={{width: "100%", mb: 1}}
                            />
                            <Typography variant="caption" align={"justify"} sx={{mb: 4, width: "100%"}}>
                                Choose a Project name to identify your analysis.
                            </Typography>

                            <TextField
                                label="Description"
                                variant="outlined"
                                multiline
                                rows={4}
                                required
                                onChange={handleProjectDescriptionChange}
                                sx={{width: "100%", mb: 1}}/>
                            <Typography variant="caption" align={"justify"} sx={{mb: 4, width: "100%"}}>
                                Describe your project.
                            </Typography>

                            {error && <Alert severity="error">{error}</Alert>}
                        </Box>

                        <Box
                            sx={{
                                width: "100%",
                                display: "flex",
                                flexDirection: "row",
                                justifyContent: "space-between"
                            }}>
                            <Button
                                variant="contained"
                                startIcon={<CancelIcon/>}
                                onClick={handleCancel}
                                sx={{mt: 4, width: "50%"}}
                            >
                                Cancel
                            </Button>

                            <Button
                                variant="contained"
                                startIcon={<FinishIcon/>}
                                onClick={handleSubmit}
                                sx={{mt: 4, ml: 2, width: "50%"}}
                            >
                                Create
                            </Button>
                        </Box>
                    </Box>
                </Paper>
            </Box>
        </Container>
    );
}
