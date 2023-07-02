import Box from "@mui/material/Box";
import {Container} from "@mui/material";
import Paper from "@mui/material/Paper";
import Typography from "@mui/material/Typography";
import {useWorkflow} from "./useWorkflow";
import React from "react";
import LoadingSpinner from "../../../Components/Shared/LoadingSpinner";
import {ErrorAlert} from "../../../Components/Shared/ErrorAlert";


/**
 * Workflow page.
 */
export default function Workflow() {
    const {
        workflow,
        loading,
        error, clearError
    } = useWorkflow();

    return (
        <Box sx={{
            position: "relative",
            width: "90%",
            overflow: "auto"
        }}>
            <Container>
                <Paper sx={{
                    p: 4,
                    display: "flex",
                    flexDirection: "column",
                    mt: 4,
                    alignItems: "center"
                }}>
                    <Typography component="h1" variant="h3">
                        Workflow
                    </Typography>

                    {loading && <LoadingSpinner text={"Loading workflow..."}/>}
                    <ErrorAlert error={error} clearError={clearError}/>

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
                                (!workflow.logs && <Typography component="p" variant="body1">
                                    No logs present.
                                </Typography>)
                                ||
                                (workflow.logs &&
                                    <Box sx={{
                                        //border: "3px solid red",
                                        //overflow: "auto"
                                    }}>
                                        <Typography component="h1" variant="h4">
                                            Logs
                                        </Typography>
                                        {Object.keys(workflow.logs).map((taskName) => {
                                            return <Box key={taskName}>
                                                <Typography component="h1" variant="h5">
                                                    {taskName}
                                                </Typography>
                                                <Typography component="p" variant="body1" sx={{
                                                    backgroundColor: "#F0F0F0",
                                                    borderRadius: "5px",
                                                    whiteSpace: "pre-line",
                                                    textAlign: 'left',
                                                    m: 1
                                                }}>
                                                    {workflow.logs[taskName].replace(/(\\n)/g, '\n')}
                                                </Typography>
                                            </Box>
                                        })}
                                    </Box>)
                            }
                        </Box>
                    }
                </Paper>
            </Container>
        </Box>
    )
}