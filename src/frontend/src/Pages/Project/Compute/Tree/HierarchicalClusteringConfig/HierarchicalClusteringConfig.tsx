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
    HierarchicalClusteringConfigDistanceStep
} from "../../../../../Components/Project/Compute/Tree/HierarchicalClusteringConfig/HierarchicalClusteringConfigDistanceStep"
import {
    HierarchicalClusteringConfigMethodStep
} from "../../../../../Components/Project/Compute/Tree/HierarchicalClusteringConfig/HierarchicalClusteringConfigMethodStep"
import {HierarchicalClusteringConfigStep, useHierarchicalClusteringConfig} from "./useHierarchicalClusteringConfig"
import {ErrorAlert} from "../../../../../Components/Shared/ErrorAlert"

/**
 * HierarchicalClusteringConfig page.
 */
export default function HierarchicalClusteringConfig() {
    const {
        step,
        currStep,

        distances,
        selectedDistance,
        handleDistanceChange,

        selectedMethod,
        handleMethodChange,

        handleCancel,
        handleBack,
        handleNext,

        triedSubmitting,

        error,
        clearError
    } = useHierarchicalClusteringConfig()

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
                        Hierarchical Clustering Configuration
                    </Typography>
                    <Stepper activeStep={currStep} alternativeLabel sx={{width: '100%', mt: 2, mb: 2}}>
                        {Object.values(HierarchicalClusteringConfigStep).map((label) => (
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
                                step === HierarchicalClusteringConfigStep.DISTANCE
                                    ? <HierarchicalClusteringConfigDistanceStep
                                        distances={distances}
                                        selectedDistance={selectedDistance}
                                        onDistanceChange={handleDistanceChange}
                                        triedSubmitting={triedSubmitting}
                                    />
                                    : <HierarchicalClusteringConfigMethodStep
                                        selectedMethod={selectedMethod}
                                        onMethodChange={handleMethodChange}
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
                                disabled={step === HierarchicalClusteringConfigStep.DISTANCE}
                                onClick={handleBack}
                                sx={{mt: 4, width: "30%"}}
                            >
                                Back
                            </Button>

                            <Button
                                variant="contained"
                                startIcon={
                                    step === HierarchicalClusteringConfigStep.METHOD
                                        ? <FinishIcon/>
                                        : <NextIcon/>
                                }
                                onClick={handleNext}
                                sx={{mt: 4, width: "30%"}}
                            >
                                {
                                    step === HierarchicalClusteringConfigStep.METHOD
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
