import Box from "@mui/material/Box";
import {Container} from "@mui/material";
import Paper from "@mui/material/Paper";
import Typography from "@mui/material/Typography";
import {useWorkflow} from "./useWorkflow";
import React from "react";
import {Error} from "@mui/icons-material";


/**
 * Workflow page.
 */
export default function Workflow() {
    const {
        workflow,
        error
    } = useWorkflow();

    return (
        <Box sx={{position: "relative", width: "90%"}}>
            {error &&
                <Error sx={{
                    position: "absolute"
                }}>
                    error
                </Error>
            }

            <Container>
                <Paper sx={{
                    p: 4,
                    display: "flex",
                    flexDirection: "column",
                    mt: 4,
                    alignItems: "center"
                }}>
                    <Typography component="h1" variant="h4">
                        Workflow
                    </Typography>
                    {workflow &&
                        <Box>
                            <Typography component="h2" variant="h6">
                                {workflow.name}
                            </Typography>
                            <Typography component="p" variant="body1">
                                Status: {workflow.status}
                            </Typography>
                            {
                                workflow.status === "FAILED" &&
                                <Typography component="p" variant="body1">
                                    Failure Reason: {workflow.failureReason}
                                </Typography>
                            }
                            {
                                workflow.status === "FAILED" && workflow.failureReason == "Internal Server Error" &&
                                <Typography component="p" variant="body1">
                                    Log: {workflow.failureLog}
                                </Typography>
                            }
                        </Box>
                    }
                </Paper>
            </Container>
        </Box>
)
}