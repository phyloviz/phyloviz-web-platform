import * as React from "react"
import Paper from "@mui/material/Paper";
import Typography from "@mui/material/Typography";
import Box from "@mui/material/Box";
import {Button} from "@mui/material";
import CancelIcon from "@mui/icons-material/Cancel";
import FinishIcon from "@mui/icons-material/Done";
import {NewProjectForm} from "../../Components/NewProject/NewProjectForm";
import {Uris} from "../../Utils/navigation/Uris";
import {useNavigate} from "react-router-dom";

/**
 * NewProject page.
 */
export default function NewProject() {
    const navigate = useNavigate();

    return (
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
                        <NewProjectForm/>
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
                                navigate(Uris.HOME);
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
                            onClick={() => {/*TODO*/
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
    );
}
