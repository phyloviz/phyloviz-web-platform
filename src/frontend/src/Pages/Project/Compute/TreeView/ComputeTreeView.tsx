import React from "react";
import {Button, Container, Step, StepLabel, Stepper} from "@mui/material";
import Box from "@mui/material/Box";
import Paper from "@mui/material/Paper";
import Typography from "@mui/material/Typography";
import {ErrorAlert} from "../../../../Components/Shared/ErrorAlert";
import CancelIcon from "@mui/icons-material/Cancel";
import BackIcon from "@mui/icons-material/ArrowBackIos";
import FinishIcon from "@mui/icons-material/Done";
import NextIcon from "@mui/icons-material/ArrowForwardIos";
import {ComputeTreeViewStep, useComputeTreeView} from "./useComputeTreeView";
import {ComputeTreeViewLayoutStep} from "../../../../Components/Project/Compute/TreeView/ComputeTreeViewLayoutStep";
import {ComputeTreeViewTreeStep} from "../../../../Components/Project/Compute/TreeView/ComputeTreeViewTreeStep";

export function ComputeTreeView() {
    const {
        step,
        currStep,

        trees,
        selectedTree,
        handleTreeChange,

        selectedLayout,
        handleLayoutChange,

        handleCancel,
        handleBack,
        handleNext,
        error,
        clearError
    } = useComputeTreeView()

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
                        Compute Tree View Configuration
                    </Typography>
                    <Stepper activeStep={currStep} alternativeLabel sx={{width: '100%', mt: 2, mb: 2}}>
                        {Object.values(ComputeTreeViewStep).map((label) => (
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
                                step === ComputeTreeViewStep.TREE
                                    ? <ComputeTreeViewTreeStep
                                        trees={trees}
                                        selectedTree={selectedTree}
                                        onTreeChange={handleTreeChange}
                                    />
                                    : <ComputeTreeViewLayoutStep
                                        selectedLayout={selectedLayout}
                                        onLayoutChange={handleLayoutChange}
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
                                disabled={step === ComputeTreeViewStep.TREE}
                                onClick={handleBack}
                                sx={{mt: 4, width: "30%"}}
                            >
                                Back
                            </Button>

                            <Button
                                variant="contained"
                                startIcon={
                                    step === ComputeTreeViewStep.LAYOUT
                                        ? <FinishIcon/>
                                        : <NextIcon/>
                                }
                                onClick={handleNext}
                                sx={{mt: 4, width: "30%"}}
                            >
                                {
                                    step === ComputeTreeViewStep.LAYOUT
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