import * as React from "react";
import {useState} from "react";
import {Button, Container, FormControl, FormControlLabel, FormLabel, Radio, RadioGroup} from "@mui/material";
import Box from "@mui/material/Box";
import Paper from "@mui/material/Paper";
import Typography from "@mui/material/Typography";
import FinishIcon from "@mui/icons-material/Done";
import {FileUploader} from "react-drag-drop-files";
import CancelIcon from "@mui/icons-material/Cancel";
import {useNavigate} from "react-router-dom";

enum FileType {
    TYPING_DATA = "Typing Data",
    ISOLATE_DATA = "Isolate Data",
}

/**
 * Upload Files page.
 */
export default function UploadFiles() {
    const [fileType, setfileType] = useState<FileType | null>(null);

    const [file, setFile] = useState(null);
    const handleChange = (file: React.SetStateAction<null>) => {
        setFile(file);
    };

    const navigate = useNavigate();

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
                    marginTop: 4,
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
                            mt: 4
                        }}>
                            <FormControl
                                required
                                sx={{
                                    mb: 4
                                }}
                            >
                                <FormLabel>Data Type</FormLabel>
                                <RadioGroup row>
                                    {
                                        Object.values(FileType).map((value) => (
                                            <FormControlLabel
                                                key={value}
                                                value={value}
                                                control={<Radio/>}
                                                label={value}
                                                onChange={() => setfileType(value)}
                                            />
                                        ))
                                    }
                                </RadioGroup>
                            </FormControl>

                            <FileUploader
                                handleChange={handleChange}
                                name="file"
                                required
                            />
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
                                onClick={() => {
                                    navigate(-1); // Back to project page
                                }}
                                sx={{
                                    marginTop: 4,
                                    width: "50%"
                                }}
                            >
                                Cancel
                            </Button>
                            <Button
                                variant="contained"
                                startIcon={<FinishIcon/>}
                                onClick={() => {
                                    // TODO
                                }}
                                sx={{
                                    marginTop: 4,
                                    marginLeft: 2,
                                    width: "50%"
                                }}
                            >
                                Upload
                            </Button>
                        </Box>
                    </Box>
                </Paper>
            </Box>
        </Container>
    );
}
