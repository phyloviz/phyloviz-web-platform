import * as React from "react"
import Paper from "@mui/material/Paper"
import Typography from "@mui/material/Typography"
import Box from "@mui/material/Box"
import {Button, Container, TextField} from "@mui/material"
import CancelIcon from "@mui/icons-material/Cancel"
import FinishIcon from "@mui/icons-material/Done"
import {useEditProject} from "./useEditProject";
import {ErrorAlert} from "../../../Components/Shared/ErrorAlert";

/**
 * EditProject page.
 */
export default function EditProject() {
    const {
        newProjectName,
        newProjectDescription,
        handleProjectNameChange,
        handleProjectDescriptionChange,
        handleSubmit,
        handleCancel,
        error,
        clearError
    } = useEditProject()

    return (
        <Container>
            <Box display="flex" justifyContent="center">
                <Paper sx={{
                    p: 4,
                    display: "flex",
                    flexDirection: "column",
                    mt: 4,
                    alignItems: "center",
                    width: "50%"
                }}>
                    <Typography component="h1" variant="h4">
                        Edit Project
                    </Typography>
                    <Box sx={{
                        width: "100%",
                        display: "flex",
                        flexDirection: "column",
                        justifyContent: "left",
                        mt: 4
                    }}>
                        <Typography variant="caption" align={"justify"} sx={{mb: 2, width: "100%"}}>
                            You can edit the project information here.
                        </Typography>
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
                                value={newProjectName}
                                sx={{width: "100%", mb: 2}}
                            />

                            <TextField
                                label="Description"
                                variant="outlined"
                                multiline
                                rows={4}
                                required
                                onChange={handleProjectDescriptionChange}
                                value={newProjectDescription}
                                sx={{width: "100%", mb: 1}}/>
                            <ErrorAlert error={error} clearError={clearError}/>
                        </Box>

                        <Box sx={{
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
                                Update
                            </Button>
                        </Box>
                    </Box>
                </Paper>
            </Box>
        </Container>
    )
}
