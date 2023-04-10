import * as React from "react"
import Paper from "@mui/material/Paper"
import Typography from "@mui/material/Typography"
import {Button, Container} from "@mui/material"
import Box from "@mui/material/Box"
import FinishIcon from "@mui/icons-material/Done"
import CancelIcon from "@mui/icons-material/Cancel"
import {
    GoeBURSTFullMSTConfigDistanceStep
} from "../../../../Components/Project/ComputeConfigurations/GoeBURSTFullMSTConfig/GoeBURSTFullMSTConfigDistanceStep"
import {useGoeBURSTFullMSTConfig} from "./useGoeBURSTFullMSTConfig"

/**
 * GoeBURSTFullMSTConfig page.
 */
export default function GoeBURSTFullMSTConfig() {
    const {
        distances,
        selectedDistance,
        handleDistanceChange,

        handleCancel,
        handleFinish
    } = useGoeBURSTFullMSTConfig()

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
                        goeBURST Full MST Configuration
                    </Typography>
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
                            <GoeBURSTFullMSTConfigDistanceStep
                                distances={distances}
                                selectedDistance={selectedDistance}
                                onDistanceChange={handleDistanceChange}
                            />
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
                                onClick={handleFinish}
                                sx={{mt: 4, width: "30%"}}
                            >
                                Finish
                            </Button>
                        </Box>
                    </Box>
                </Paper>
            </Box>
        </Container>
    )
}
