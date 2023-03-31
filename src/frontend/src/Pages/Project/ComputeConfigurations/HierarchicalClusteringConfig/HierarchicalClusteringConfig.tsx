import * as React from "react"
import Paper from "@mui/material/Paper";
import Typography from "@mui/material/Typography";
import {Button, Container, Step, StepLabel, Stepper} from "@mui/material";
import Box from "@mui/material/Box";
import NextIcon from "@mui/icons-material/ArrowForwardIos";
import BackIcon from "@mui/icons-material/ArrowBackIos";
import FinishIcon from "@mui/icons-material/Done";
import CancelIcon from "@mui/icons-material/Cancel";
import {
    HierarchicalClusteringConfigDistanceStep
} from "../../../../Components/Project/ComputeConfigurations/HierarchicalClusteringConfig/HierarchicalClusteringConfigDistanceStep";
import {
    HierarchicalClusteringConfigMethodStep
} from "../../../../Components/Project/ComputeConfigurations/HierarchicalClusteringConfig/HierarchicalClusteringConfigMethodStep";
import {
    HierarchicalClusteringConfigStep,
    steps,
    useHierarchicalClusteringConfig
} from "./useHierarchicalClusteringConfig";

/**
 * HierarchicalClusteringConfig page.
 */
export default function HierarchicalClusteringConfig() {
    const {
        step,
        currStep,
        handleCancel,
        handleBack,
        handleNext
    } = useHierarchicalClusteringConfig();

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
                    mt: 4,
                    alignItems: "center",
                    width: "50%"
                }}>
                    <Typography component="h1" variant="h4">
                        Hierarchical Clustering Configuration
                    </Typography>
                    <Stepper activeStep={currStep} alternativeLabel sx={{width: '100%', mt: 2, mb: 2}}>
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
                                step === HierarchicalClusteringConfigStep.DISTANCE
                                    ? <HierarchicalClusteringConfigDistanceStep/>
                                    : <HierarchicalClusteringConfigMethodStep/>
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
    );
}
