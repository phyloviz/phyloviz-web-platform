import * as React from "react"
import {useState} from "react"
import Paper from "@mui/material/Paper";
import Typography from "@mui/material/Typography";
import Box from "@mui/material/Box";
import {Button, Container, TextField} from "@mui/material";
import CancelIcon from "@mui/icons-material/Cancel";
import FinishIcon from "@mui/icons-material/Done";
import {WebUiUris} from "../../Utils/navigation/WebUiUris";
import {useNavigate} from "react-router-dom";
import {AdministrationService} from "../../Services/administration/AdministrationService";
import {CreateProjectInputModel} from "../../Services/administration/models/createProject/CreateProjectInputModel";
import {Alert} from "@mui/lab";

/**
 * NewProject page.
 */
export default function NewProject() {
    const navigate = useNavigate();

    const [projectName, setProjectName] = useState<string | null>(null);
    const [projectDescription, setProjectDescription] = useState<string | null>(null);

    const [error, setError] = useState<string | null>(null);

    function handleSubmit() {
        if (projectName == null || projectName === "" || projectDescription == null || projectDescription === "") {
            setError("Please fill out all fields");
            return;
        }

        navigate(WebUiUris.project("test")); // TODO: Remove this when the backend is ready

        AdministrationService.createProject({
            name: projectName,
            description: projectDescription
        } as CreateProjectInputModel)
            .then(res => {
                navigate(WebUiUris.project(res.projectId));
            })
            .catch(err => {
                setError(err.message);
            });
    }

    return (
        <Container>
            <Box
                display="flex"
                justifyContent="center"
                height={'600px'}
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
                            height: "360px",
                            display: "flex",
                            flexDirection: "column",
                            justifyContent: "left",
                        }}>
                            <TextField
                                label="Project Name"
                                variant="outlined"
                                required
                                onChange={(event) => {
                                    setProjectName(event.target.value);
                                }}
                                sx={{
                                    width: "100%", mb: 1
                                }}
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
                                onChange={(event) => {
                                    setProjectDescription(event.target.value);
                                }}
                                sx={{
                                    width: "100%", mb: 1
                                }}/>
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
                                onClick={() => {
                                    navigate(WebUiUris.HOME);
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
                                    handleSubmit()
                                }}
                                sx={{
                                    marginTop: 4,
                                    marginLeft: 2,
                                    width: "50%"
                                }}
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
