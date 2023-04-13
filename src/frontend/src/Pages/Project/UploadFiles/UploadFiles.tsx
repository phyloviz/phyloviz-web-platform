import * as React from "react"
import {Button, Container, FormControl, FormControlLabel, FormLabel, Radio, RadioGroup} from "@mui/material"
import Box from "@mui/material/Box"
import Paper from "@mui/material/Paper"
import Typography from "@mui/material/Typography"
import FinishIcon from "@mui/icons-material/Done"
import {FileUploader} from "react-drag-drop-files"
import CancelIcon from "@mui/icons-material/Cancel"
import {FileType, useUploadFiles} from "./useUploadFiles"
import Alert from '@mui/material/Alert'
import LoadingSpinner from "../../../Components/Shared/LoadingSpinner";

/**
 * Upload Files page.
 */
export default function UploadFiles() {
    const {
        fileType,
        handleFileTypeChange,
        handleFileChange,
        handleCancel,
        handleSubmit,
        isUploading,
        error
    } = useUploadFiles()

    return (
        <Container>
            <Box
                display="flex"
                justifyContent="center"
                sx={{mb: 4}}
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
                        Upload Files
                    </Typography>
                    <Box sx={{
                        width: "100%",
                        display: "flex",
                        flexDirection: "column",
                        alignItems: "center",
                    }}>
                        <Box sx={{
                            width: "100%",
                            display: "flex",
                            flexDirection: "column",
                            alignItems: "center",
                            mt: 4,
                            mb: 4
                        }}>
                            <FormControl required sx={{mb: 4}}>
                                <FormLabel>Data Type</FormLabel>
                                <RadioGroup value={fileType} row>
                                    {
                                        Object.values(FileType).map((value) => (
                                            <FormControlLabel
                                                key={value}
                                                value={value}
                                                control={<Radio/>}
                                                label={value}
                                                onChange={() => handleFileTypeChange(value)}
                                            />
                                        ))
                                    }
                                </RadioGroup>
                            </FormControl>

                            <FileUploader handleChange={handleFileChange} name="file" required/>
                            {error && <Alert severity="error" sx={{mt: 2}}>{error}</Alert>}
                        </Box>

                        {
                            isUploading && (
                                <LoadingSpinner text={"Uploading..."}/>
                            )
                        }

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
                                disabled={isUploading}
                                sx={{mt: 4, width: "50%"}}
                            >
                                Cancel
                            </Button>
                            <Button
                                variant="contained"
                                startIcon={<FinishIcon/>}
                                onClick={handleSubmit}
                                disabled={isUploading}
                                sx={{mt: 4, ml: 2, width: "50%"}}
                            >
                                Upload
                            </Button>
                        </Box>
                    </Box>
                </Paper>
            </Box>
        </Container>
    )
}
