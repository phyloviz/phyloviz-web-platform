import * as React from "react"
import {useState} from "react"
import Paper from "@mui/material/Paper";
import Typography from "@mui/material/Typography";
import {Button, Container, Step, StepLabel, Stepper} from "@mui/material";
import Box from "@mui/material/Box";
import NextIcon from "@mui/icons-material/ArrowForwardIos";
import BackIcon from "@mui/icons-material/ArrowBackIos";
import FinishIcon from "@mui/icons-material/Done";
import {DatasetInfoStepCard} from "../../Components/Project/EmptyProject/CreateDataset/DatasetInfoStepCard";
import {DatasetType} from "../../Domain/DatasetType";
import {TypingDataStepCard} from "../../Components/Project/EmptyProject/CreateDataset/TypingDataStepCard";
import {IsolateDataStepCard} from "../../Components/Project/EmptyProject/CreateDataset/IsolateDataStepCard";
import CancelIcon from "@mui/icons-material/Cancel";
import {useNavigate} from "react-router-dom";

enum CreateDatasetStep {
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
 * CreateDataset page.
 */
export default function CreateDataset() {
    const [datasetType, setDatasetType] = useState(DatasetType.MLST);
    const [loadDatasetStep, setLoadDatasetStep] = useState(CreateDatasetStep.INFO);
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
                        Create Dataset
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
                                loadDatasetStep === CreateDatasetStep.INFO
                                    ? <DatasetInfoStepCard
                                        datasetType={datasetType}
                                        onChange={(event) => {
                                            setDatasetType(event.target.value as DatasetType);
                                        }}
                                    />
                                    : loadDatasetStep === CreateDatasetStep.TYPING_DATA
                                        ? <TypingDataStepCard datasetType={datasetType}/>
                                        : <IsolateDataStepCard onChange={(event) => {
                                            // TODO
                                        }}/>
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
                                disabled={loadDatasetStep === CreateDatasetStep.INFO}
                                onClick={() => {
                                    if (loadDatasetStep === CreateDatasetStep.TYPING_DATA) {
                                        setLoadDatasetStep(CreateDatasetStep.INFO)
                                        setCurrStep(0)
                                    } else if (loadDatasetStep === CreateDatasetStep.ISOLATE_DATA) {
                                        setLoadDatasetStep(CreateDatasetStep.TYPING_DATA)
                                        setCurrStep(1)
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
                                    loadDatasetStep === CreateDatasetStep.ISOLATE_DATA
                                        ? <FinishIcon/>
                                        : <NextIcon/>
                                }
                                onClick={() => {
                                    if (loadDatasetStep === CreateDatasetStep.INFO) {
                                        setLoadDatasetStep(CreateDatasetStep.TYPING_DATA)
                                        setCurrStep(1)
                                    } else if (loadDatasetStep === CreateDatasetStep.TYPING_DATA) {
                                        setLoadDatasetStep(CreateDatasetStep.ISOLATE_DATA)
                                        setCurrStep(2)
                                    }
                                    // TODO: else, finish
                                }}
                                sx={{
                                    marginTop: 4,
                                    width: "30%"
                                }}
                            >
                                {
                                    loadDatasetStep === CreateDatasetStep.ISOLATE_DATA
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
