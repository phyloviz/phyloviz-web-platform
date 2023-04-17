import * as React from "react"
import Paper from "@mui/material/Paper"
import Typography from "@mui/material/Typography"
import {Button, Container} from "@mui/material"
import Box from "@mui/material/Box"
import FinishIcon from "@mui/icons-material/Done"
import CancelIcon from "@mui/icons-material/Cancel"
import {useComputeHammingDistance} from "./useComputeHammingDistance";
import LoadingSpinner from "../../../../../Components/Shared/LoadingSpinner";
import Alert from "@mui/material/Alert";

/**
 * ComputeHammingDistance page.
 */
export default function ComputeHammingDistance() {
    const {
        handleCancel,
        handleCompute,
        computing,
        error
    } = useComputeHammingDistance()

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
                    mb: 4,
                    alignItems: "center",
                    width: "50%"
                }}>
                    <Typography component="h1" variant="h4">
                        Compute Hamming Distance
                    </Typography>
                    <Box sx={{
                        width: "100%",
                        display: "flex",
                        flexDirection: "column",
                        justifyContent: "left",
                    }}>
                        {computing && <LoadingSpinner text={"Computing Hamming Distance..."}/>}
                        {error && <Alert severity="error">{error}</Alert>}

                        <Box sx={{
                            width: "100%",
                            display: "flex",
                            flexDirection: "column",
                            justifyContent: "left",
                            mt: 2
                        }}>
                            <Typography display="inline" variant="caption" align={"left"}
                                        sx={{width: "100%", whiteSpace: "pre-wrap"}}>
                                {
                                    "Hamming distance is a metric for comparing two binary data strings. " +
                                    "It is the number of positions at which the corresponding symbols are different. " +
                                    "In other words, it measures the minimum number of substitutions required to change " +
                                    "one string into the other, or the minimum number of errors that could have " +
                                    "transformed one string into the other. \n" +
                                    "In a more general context, the Hamming distance is one of several string metrics " +
                                    "for measuring the edit distance between two sequences. " +
                                    "It is named after the American mathematician Richard Hamming. \n" +
                                    "The Hamming distance between two strings of equal length is the number of positions " +
                                    "at which the corresponding symbols are different. "
                                }
                            </Typography>
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
                                sx={{mt: 4, width: "30%"}}
                            >
                                Cancel
                            </Button>

                            <Button
                                variant="contained"
                                startIcon={<FinishIcon/>}
                                onClick={handleCompute}
                                sx={{mt: 4, width: "30%"}}
                            >
                                Compute
                            </Button>
                        </Box>
                    </Box>
                </Paper>
            </Box>
        </Container>
    )
}
