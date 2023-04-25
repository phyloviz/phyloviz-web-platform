import * as React from "react"
import Paper from "@mui/material/Paper"
import Typography from "@mui/material/Typography"
import Box from "@mui/material/Box"
import {Button, Container, TextField} from "@mui/material"
import CancelIcon from "@mui/icons-material/Cancel"
import FinishIcon from "@mui/icons-material/Done"
import {useEditDataset} from "./useEditDataset";
import {ErrorAlert} from "../../../Components/Shared/ErrorAlert";

/**
 * EditDataset page.
 */
export default function EditDataset() {
    const {
        newDatasetName,
        newDatasetDescription,
        handleDatasetNameChange,
        handleDatasetDescriptionChange,
        handleSubmit,
        handleCancel,
        error,
        clearError
    } = useEditDataset()

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
                        Edit Dataset
                    </Typography>
                    <Box sx={{
                        width: "100%",
                        display: "flex",
                        flexDirection: "column",
                        justifyContent: "left",
                        mt: 4
                    }}>
                        <Typography variant="caption" align={"justify"} sx={{mb: 2, width: "100%"}}>
                            You can edit the dataset information here.
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
                                onChange={handleDatasetNameChange}
                                value={newDatasetName}
                                sx={{width: "100%", mb: 2}}
                            />

                            <TextField
                                label="Description"
                                variant="outlined"
                                multiline
                                rows={4}
                                required
                                onChange={handleDatasetDescriptionChange}
                                value={newDatasetDescription}
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
