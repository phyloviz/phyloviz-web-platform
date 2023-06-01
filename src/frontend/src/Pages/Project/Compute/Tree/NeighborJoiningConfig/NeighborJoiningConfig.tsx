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
    NeighborJoiningConfigDistanceStep
} from "../../../../../Components/Project/Compute/Tree/NeighborJoiningConfig/NeighborJoiningConfigDistanceStep"
import {
    NeighborJoiningConfigMethodStep
} from "../../../../../Components/Project/Compute/Tree/NeighborJoiningConfig/NeighborJoiningConfigMethodStep"
import {NeighborJoiningConfigurationStep, useNeighborJoiningConfig} from "./useNeighborJoiningConfig"
import {ErrorAlert} from "../../../../../Components/Shared/ErrorAlert"

/**
 * NeighborJoiningConfig page.
 */
export default function NeighborJoiningConfig() {
    const {
        step,
        currStep,

        distances,
        selectedDistance,
        handleDistanceChange,

        selectedCriteria,
        handleCriteriaChange,

        handleCancel,
        handleBack,
        handleNext,

        triedSubmitting,

        error,
        clearError
    } = useNeighborJoiningConfig()

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
                        Neighbor-Joining Configuration
                    </Typography>
                    <Stepper activeStep={currStep} alternativeLabel sx={{width: '100%', mt: 2, mb: 2}}>
                        {Object.values(NeighborJoiningConfigurationStep).map((label) => (
                            <Step key={label}>
                                <StepLabel>{label}</StepLabel>
                            </Step>
                        ))}
                    </Stepper>
                    <ErrorAlert error={error} clearError={clearError}/>
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
                                step === NeighborJoiningConfigurationStep.DISTANCE
                                    ? <NeighborJoiningConfigDistanceStep
                                        distances={distances}
                                        selectedDistance={selectedDistance}
                                        onDistanceChange={handleDistanceChange}
                                        triedSubmitting={triedSubmitting}
                                    />
                                    : <NeighborJoiningConfigMethodStep
                                        selectedCriteria={selectedCriteria}
                                        onCriteriaChange={handleCriteriaChange}
                                        triedSubmitting={triedSubmitting}
                                    />
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
                                disabled={step === NeighborJoiningConfigurationStep.DISTANCE}
                                onClick={handleBack}
                                sx={{mt: 4, width: "30%"}}
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
                                onClick={handleNext}
                                sx={{mt: 4, width: "30%"}}
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
    )
}
