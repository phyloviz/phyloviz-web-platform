import * as React from "react"
import Paper from "@mui/material/Paper";
import Typography from "@mui/material/Typography";
import {Button, Container} from "@mui/material";
import Box from "@mui/material/Box";
import FinishIcon from "@mui/icons-material/Done";
import CancelIcon from "@mui/icons-material/Cancel";
import {useNavigate} from "react-router-dom";
import {GoeBURSTFullMSTConfigDistanceStep} from "./GoeBURSTFullMSTConfigDistanceStep";

/**
 * GoeBURSTFullMSTConfig page.
 */
export default function GoeBURSTFullMSTConfig() {
    const navigate = useNavigate();

    return (
        <Container>
            <Box
                display="flex"
                justifyContent="center"
                height={'810px'}
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
                        goeBURST Full MST Configuration
                    </Typography>
                    <Box sx={{
                        width: "100%",
                        display: "flex",
                        flexDirection: "column",
                        justifyContent: "left",
                    }}>
                        <Box sx={{
                            width: "100%",
                            height: "510px",
                            display: "flex",
                            flexDirection: "column",
                            justifyContent: "left",
                        }}>
                            <GoeBURSTFullMSTConfigDistanceStep/>
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
                                onClick={() => {
                                    navigate(-1); // Back to project page
                                }}
                                sx={{
                                    marginTop: 4,
                                    width: "30%"
                                }}
                            >
                                Cancel
                            </Button>

                            <Button
                                variant="contained"
                                startIcon={<FinishIcon/>}
                                onClick={() => {
                                    // TODO:finish
                                }}
                                sx={{
                                    marginTop: 4,
                                    width: "30%"
                                }}
                            >
                                Finish
                            </Button>
                        </Box>
                    </Box>
                </Paper>
            </Box>
        </Container>
    );
}
