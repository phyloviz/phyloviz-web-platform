import * as React from "react"
import {
    Button,
    Container,
    FormControl,
    FormControlLabel,
    FormLabel,
    InputLabel,
    MenuItem,
    Radio,
    RadioGroup,
    Select,
    TextField
} from "@mui/material"
import Box from "@mui/material/Box"
import Paper from "@mui/material/Paper"
import Typography from "@mui/material/Typography"
import FinishIcon from "@mui/icons-material/Done"
import {FileUploader} from "react-drag-drop-files"
import CancelIcon from "@mui/icons-material/Cancel"
import {FileType, useUploadFiles} from "./useUploadFiles"
import LoadingSpinner from "../../../Components/Shared/LoadingSpinner"
import {ErrorAlert} from "../../../Components/Shared/ErrorAlert"
import {TypingDataType} from "../CreateDataset/useCreateDataset";

/**
 * Upload Files page.
 */
export default function UploadFiles() {
    const {
        fileType,
        typingDataType,
        fileUrl,
        onFileUrlChange,
        handleFileTypeChange,
        handleTypingDataTypeChange,
        handleFileChange,
        handleCancel,
        handleSubmit,
        isUploading,
        error,
        clearError
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
                    <Typography variant="body1" sx={{mt: 2, width: "100%"}}>
                        Here you can upload new files to the project
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
                                <FormLabel>File Type</FormLabel>
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
                            {fileType === FileType.TYPING_DATA &&
                                (
                                    <FormControl required sx={{width: "100%", mb: 1}}>
                                        <InputLabel id="typing-data-type">Typing Data Type</InputLabel>
                                        <Select
                                            labelId="typing-data-type"
                                            value={typingDataType.valueOf()}
                                            label="Typing Data Type"
                                            onChange={(event) => handleTypingDataTypeChange(event.target.value as TypingDataType)}
                                            MenuProps={{PaperProps: {sx: {maxHeight: 150}}}}
                                            sx={{mb: 2}}
                                        >
                                            {
                                                Object.values(TypingDataType).map((typingDataType) => (
                                                    <MenuItem
                                                        key={typingDataType.valueOf()}
                                                        value={typingDataType.valueOf()}>{typingDataType.valueOf()}</MenuItem>
                                                ))
                                            }
                                        </Select>
                                    </FormControl>
                                )
                            }

                            <Typography variant="caption" align={"justify"} sx={{mb: 1, width: "100%"}}>
                                You can upload a file from your computer by dragging and dropping it in the box below.
                            </Typography>
                            <FileUploader handleChange={handleFileChange} name="file" required/>
                            <Typography variant="body2" align={"center"} sx={{mt: 2, mb: 1, width: "100%"}}>
                                Or
                            </Typography>
                            <Typography variant="caption" align={"justify"} sx={{mb: 1, width: "100%"}}>
                                You can upload a file by providing a URL to the file, if it is publicly available.
                            </Typography>
                            <TextField
                                fullWidth
                                label="URL to file"
                                variant="outlined"
                                sx={{mb: 1}}
                                value={fileUrl}
                                onChange={onFileUrlChange}
                            />
                            <ErrorAlert error={error} clearError={clearError}/>
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
                                justifyContent: "space-between",
                                mt: 2,
                            }}>

                            <Button
                                variant="contained"
                                startIcon={<CancelIcon/>}
                                onClick={handleCancel}
                                disabled={isUploading}
                                sx={{width: "50%"}}
                            >
                                Cancel
                            </Button>
                            <Button
                                variant="contained"
                                startIcon={<FinishIcon/>}
                                onClick={handleSubmit}
                                disabled={isUploading}
                                sx={{ml: 2, width: "50%"}}
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
