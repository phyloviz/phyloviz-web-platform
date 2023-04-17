import * as React from "react"
import Paper from "@mui/material/Paper"
import Typography from "@mui/material/Typography"
import {Button, Container, Step, StepLabel, Stepper} from "@mui/material"
import Box from "@mui/material/Box"
import NextIcon from "@mui/icons-material/ArrowForwardIos"
import BackIcon from "@mui/icons-material/ArrowBackIos"
import FinishIcon from "@mui/icons-material/Done"
import CancelIcon from "@mui/icons-material/Cancel"
import {
    GoeBURSTConfigDistanceStep
} from "../../../../../Components/Project/Compute/Tree/GoeBURSTConfig/GoeBURSTConfigDistanceStep"
import {
    GoeBURSTConfigLevelStep
} from "../../../../../Components/Project/Compute/Tree/GoeBURSTConfig/GoeBURSTConfigLevelStep"
import {GoeBURSTConfigurationStep, useGoeBURSTConfig} from "./useGoeBURSTConfig"

/**
 * GoeBURSTConfig page.
 */
export default function GoeBURSTConfig() {
    const {
        step,
        currStep,

        distances,
        selectedDistance,
        handleDistanceChange,

        handleCancel,
        handleBack,
        handleNext
    } = useGoeBURSTConfig()

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
                        goeBURST Configuration
                    </Typography>
                    <Stepper activeStep={currStep} alternativeLabel sx={{width: '100%', mt: 2, mb: 2}}>
                        {Object.values(GoeBURSTConfigurationStep).map((label) => (
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
                            display: "flex",
                            flexDirection: "column",
                            justifyContent: "left",
                        }}>
                            {
                                step === GoeBURSTConfigurationStep.DISTANCE
                                    ? <GoeBURSTConfigDistanceStep
                                        distances={distances}
                                        selectedDistance={selectedDistance}
                                        onDistanceChange={handleDistanceChange}
                                    />
                                    : <GoeBURSTConfigLevelStep/>
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
                                onClick={handleCancel}
                                sx={{mt: 4, width: "30%"}}
                            >
                                Cancel
                            </Button>

                            <Button
                                variant="contained"
                                startIcon={<BackIcon/>}
                                disabled={step === GoeBURSTConfigurationStep.DISTANCE}
                                onClick={handleBack}
                                sx={{mt: 4, width: "30%"}}
                            >
                                Back
                            </Button>

                            <Button
                                variant="contained"
                                startIcon={
                                    step === GoeBURSTConfigurationStep.LEVEL
                                        ? <FinishIcon/>
                                        : <NextIcon/>
                                }
                                onClick={handleNext}
                                sx={{mt: 4, width: "30%"}}
                            >
                                {
                                    step === GoeBURSTConfigurationStep.LEVEL
                                        ? "Finish"
                                        : "Next"
                                }
                            </Button>
                        </Box>
                    </Box>
                </Paper>
            </Box>
        </Container>
    )
}
