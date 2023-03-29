import * as React from "react"
import {useState} from "react"
import Paper from "@mui/material/Paper";
import Typography from "@mui/material/Typography";
import {Button, Container, Step, StepLabel, Stepper} from "@mui/material";
import Box from "@mui/material/Box";
import NextIcon from "@mui/icons-material/ArrowForwardIos";
import BackIcon from "@mui/icons-material/ArrowBackIos";
import FinishIcon from "@mui/icons-material/Done";
import CancelIcon from "@mui/icons-material/Cancel";
import {useNavigate} from "react-router-dom";
import {NeighborJoiningConfigDistanceStep} from "./NeighborJoiningConfigDistanceStep";
import {NeighborJoiningConfigMethodStep} from "./NeighborJoiningConfigMethodStep";

enum NeighborJoiningConfigurationStep {
    DISTANCE = "Distance",
    METHOD = "Method",
}

// TODO: Sus, criei para fazer o stepper
const steps = [
    'Distance',
    'Method',
];

/**
 * NeighborJoiningConfig page.
 */
export default function NeighborJoiningConfig() {
    const [step, setStep] = useState(NeighborJoiningConfigurationStep.DISTANCE);
    const [currStep, setCurrStep] = useState(0);
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
                        Neighbor-Joining Configuration
                    </Typography>
                    <Stepper activeStep={currStep} alternativeLabel
                             sx={{
                                 width: '100%',
                                 mt: 2,
                                 mb: 2
                             }}
                    >
                        {steps.map((label) => (
                            <Step key={label}>
                                <StepLabel>{label}</StepLabel>
                            </Step>
                        ))}
                    </Stepper>
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
                            {
                                step === NeighborJoiningConfigurationStep.DISTANCE
                                    ? <NeighborJoiningConfigDistanceStep/>
                                    : <NeighborJoiningConfigMethodStep/>
                            }
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
                                startIcon={<BackIcon/>}
                                disabled={step === NeighborJoiningConfigurationStep.DISTANCE}
                                onClick={() => {
                                    if (step === NeighborJoiningConfigurationStep.METHOD) {
                                        setStep(NeighborJoiningConfigurationStep.DISTANCE)
                                        setCurrStep(0)
                                    }
                                }}
                                sx={{
                                    marginTop: 4,
                                    width: "30%"
                                }}
                            >
                                Back
                            </Button>

                            <Button
                                variant="contained"
                                startIcon={
                                    step === NeighborJoiningConfigurationStep.METHOD
                                        ? <FinishIcon/>
                                        : <NextIcon/>
                                }
                                onClick={() => {
                                    if (step === NeighborJoiningConfigurationStep.DISTANCE) {
                                        setStep(NeighborJoiningConfigurationStep.METHOD)
                                        setCurrStep(1)
                                    }
                                    // TODO: else, finish
                                }}
                                sx={{
                                    marginTop: 4,
                                    width: "30%"
                                }}
                            >
                                {
                                    step === NeighborJoiningConfigurationStep.METHOD
                                        ? "Finish"
                                        : "Next"
                                }
                            </Button>
                        </Box>
                    </Box>
                </Paper>
            </Box>
        </Container>
    );
}
