import * as React from "react"
import {useState} from "react"
import Paper from "@mui/material/Paper";
import Typography from "@mui/material/Typography";
import {Button, Step, StepLabel, Stepper} from "@mui/material";
import Box from "@mui/material/Box";
import NextIcon from "@mui/icons-material/ArrowForwardIos";
import BackIcon from "@mui/icons-material/ArrowBackIos";
import FinishIcon from "@mui/icons-material/Done";
import {DatasetInfoStepCard} from "../../Components/LoadDataset/DatasetInfoStepCardProps";
import {DatasetType} from "../../Domain/DatasetType";
import {TypingDataStepCard} from "../../Components/LoadDataset/TypingDataStepCard";
import {IsolateDataStepCard} from "../../Components/LoadDataset/IsolateDataStepCard";

enum LoadDatasetStep {
    INFO = "Dataset Info",
    TYPING_DATA = "Typing Data",
    ISOLATE_DATA = "Isolate Data",
}

// TODO: Sus, criei para fazer o stepper
const steps = [
    'Dataset Info',
    'Typing Data',
    'Isolate Data',
];

/**
 * LoadDataset page.
 */
export default function LoadDataset() {
    const [datasetType, setDatasetType] = useState(DatasetType.MLST);
    const [loadDatasetStep, setLoadDatasetStep] = useState(LoadDatasetStep.INFO);
    const [currStep, setCurrStep] = useState(0);

    return (
        <Box
            display="flex"
            justifyContent="center"
            height={'720px'}
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
                    Load Dataset
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
                        height: "420px",
                        display: "flex",
                        flexDirection: "column",
                        justifyContent: "left",
                    }}>
                        {
                            loadDatasetStep === LoadDatasetStep.INFO
                                ? <DatasetInfoStepCard
                                    datasetType={datasetType}
                                    onChange={(event) => {
                                        setDatasetType(event.target.value as DatasetType);
                                    }}
                                />
                                : loadDatasetStep === LoadDatasetStep.TYPING_DATA
                                    ? <TypingDataStepCard datasetType={datasetType}/>
                                    : <IsolateDataStepCard onChange={(event) => {
                                        // TODO
                                    }}/>
                        }
                    </Box>

                    <Box
                        sx={{
                            width: "100%",
                            display: "flex",
                            flexDirection: "row",
                            justifyContent: "space-between"
                        }}>
                        <Button
                            variant="contained"
                            startIcon={<BackIcon/>}
                            disabled={loadDatasetStep === LoadDatasetStep.INFO}
                            onClick={() => {
                                if (loadDatasetStep === LoadDatasetStep.TYPING_DATA) {
                                    setLoadDatasetStep(LoadDatasetStep.INFO)
                                    setCurrStep(0)
                                } else if (loadDatasetStep === LoadDatasetStep.ISOLATE_DATA) {
                                    setLoadDatasetStep(LoadDatasetStep.TYPING_DATA)
                                    setCurrStep(1)
                                }
                            }}
                            sx={{
                                marginTop: 4,
                                width: "50%"
                            }}
                        >
                            Back
                        </Button>

                        <Button
                            variant="contained"
                            startIcon={
                                loadDatasetStep === LoadDatasetStep.ISOLATE_DATA
                                    ? <FinishIcon/>
                                    : <NextIcon/>
                            }
                            onClick={() => {
                                if (loadDatasetStep === LoadDatasetStep.INFO) {
                                    setLoadDatasetStep(LoadDatasetStep.TYPING_DATA)
                                    setCurrStep(1)
                                } else if (loadDatasetStep === LoadDatasetStep.TYPING_DATA) {
                                    setLoadDatasetStep(LoadDatasetStep.ISOLATE_DATA)
                                    setCurrStep(2)
                                }
                                // TODO: else

                            }}
                            sx={{
                                marginTop: 4,
                                marginLeft: 2,
                                width: "50%"
                            }}
                        >
                            {
                                loadDatasetStep === LoadDatasetStep.ISOLATE_DATA
                                    ? "Finish"
                                    : "Next"
                            }
                        </Button>
                    </Box>
                </Box>
            </Paper>
        </Box>
    );
}
