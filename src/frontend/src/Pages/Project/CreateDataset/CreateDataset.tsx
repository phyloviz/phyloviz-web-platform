import * as React from "react"
import Paper from "@mui/material/Paper"
import Typography from "@mui/material/Typography"
import {Button, Container, Step, StepLabel, Stepper} from "@mui/material"
import Box from "@mui/material/Box"
import NextIcon from "@mui/icons-material/ArrowForwardIos"
import BackIcon from "@mui/icons-material/ArrowBackIos"
import FinishIcon from "@mui/icons-material/Done"
import {DatasetInfoStepCard} from "../../../Components/Project/CreateDataset/DatasetInfoStepCard"
import {TypingDataStepCard} from "../../../Components/Project/CreateDataset/TypingDataStepCard"
import {IsolateDataStepCard} from "../../../Components/Project/CreateDataset/IsolateDataStepCard"
import CancelIcon from "@mui/icons-material/Cancel"
import {CreateDatasetStep, useCreateDataset} from "./useCreateDataset"


/**
 * CreateDataset page.
 */
export default function CreateDataset() {
    const {
        datasetType,
        project,
        handleDatasetNameChange,
        handleDatasetDescriptionChange,
        handleDatasetTypeChange,
        selectedTypingData,
        handleTypingDataFileSelectorChange,
        handleTypingDataFileUploaderChange,
        selectedIsolateData,
        handleIsolateDataFileSelectorChange,
        handleIsolateDataFileUploaderChange,
        isolateDataKeys,
        selectedIsolateDataKey,
        handleIsolateDataKeyChange,
        handleCancel,
        handleBack,
        handleNext,
        createDatasetStep,
        currStep,
    } = useCreateDataset()

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
                        Create Dataset
                    </Typography>
                    <Stepper activeStep={currStep} alternativeLabel sx={{width: '100%', mt: 2, mb: 2}}>
                        {Object.values(CreateDatasetStep).map((label) => (
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
                                createDatasetStep === CreateDatasetStep.INFO
                                    ? <DatasetInfoStepCard
                                        datasetType={datasetType}
                                        onDatasetNameChange={handleDatasetNameChange}
                                        onDatasetDescriptionChange={handleDatasetDescriptionChange}
                                        onDatasetTypeChange={handleDatasetTypeChange}
                                    />
                                    : createDatasetStep === CreateDatasetStep.TYPING_DATA
                                        ? <TypingDataStepCard
                                            datasetType={datasetType}
                                            selectedTypingData={selectedTypingData}
                                            typingData={project?.files.typingData!}
                                            onFileSelecterChange={handleTypingDataFileSelectorChange}
                                            onFileUploaderChange={handleTypingDataFileUploaderChange}
                                        />
                                        : <IsolateDataStepCard
                                            selectedIsolateData={selectedIsolateData}
                                            isolateData={project?.files.isolateData!}
                                            onFileSelecterChange={handleIsolateDataFileSelectorChange}
                                            onFileUploaderChange={handleIsolateDataFileUploaderChange}
                                            isolateDataKeys={isolateDataKeys}
                                            selectedIsolateDataKey={selectedIsolateDataKey}
                                            onIsolateDataKeyChange={handleIsolateDataKeyChange}
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
                                disabled={createDatasetStep === CreateDatasetStep.INFO}
                                onClick={handleBack}
                                sx={{mt: 4, width: "30%"}}
                            >
                                Back
                            </Button>

                            <Button
                                variant="contained"
                                startIcon={
                                    createDatasetStep === CreateDatasetStep.ISOLATE_DATA
                                        ? <FinishIcon/>
                                        : <NextIcon/>
                                }
                                onClick={handleNext}
                                sx={{mt: 4, width: "30%"}}
                            >
                                {
                                    createDatasetStep === CreateDatasetStep.ISOLATE_DATA
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
