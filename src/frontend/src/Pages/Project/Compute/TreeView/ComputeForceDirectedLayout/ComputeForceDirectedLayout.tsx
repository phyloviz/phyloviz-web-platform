import * as React from "react"
import Paper from "@mui/material/Paper"
import Typography from "@mui/material/Typography"
import {Button, Container} from "@mui/material"
import Box from "@mui/material/Box"
import FinishIcon from "@mui/icons-material/Done"
import CancelIcon from "@mui/icons-material/Cancel"
import {ErrorAlert} from "../../../../../Components/Shared/ErrorAlert"
import {ComputeTreeViewTreeStep} from "../../../../../Components/Project/Compute/TreeView/ComputeTreeViewTreeStep";
import {ComputeTreeViewLayout, useComputeTreeView} from "../useComputeTreeView";

/**
 * ComputeForceDirectedLayout page.
 */
export default function ComputeForceDirectedLayout() {
    const {
        trees,
        selectedTree,
        handleTreeChange,
        triedSubmitting,
        handleCancel,
        handleCompute,
        error,
        clearError
    } = useComputeTreeView(ComputeTreeViewLayout.FORCE_DIRECTED)

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
                        Compute Force Directed Layout
                    </Typography>
                    <Box sx={{
                        width: "100%",
                        display: "flex",
                        flexDirection: "column",
                        justifyContent: "left",
                    }}>
                        <ErrorAlert error={error} clearError={clearError}/>
                        <Box sx={{
                            width: "100%",
                            display: "flex",
                            flexDirection: "column",
                            justifyContent: "left",
                            mt: 2
                        }}>
                            <Typography display="inline" variant="caption" align={"left"}
                                        sx={{width: "100%", whiteSpace: "pre-wrap"}}>
                                {
                                    "Force-directed graph drawing algorithms are a class of algorithms for drawing graphs " +
                                    "in an aesthetically-pleasing way. Their purpose is to position the nodes of a graph " +
                                    "in two-dimensional or three-dimensional space so that all the edges are of more or " +
                                    "less equal length and there are as few crossing edges as possible, by assigning " +
                                    "forces among the set of edges and the set of nodes, based on their relative " +
                                    "positions, and then using these forces either to simulate the motion of the edges " +
                                    "and nodes or to minimize their energy."
                                }
                            </Typography>
                            <ComputeTreeViewTreeStep
                                trees={trees}
                                selectedTree={selectedTree}
                                onTreeChange={handleTreeChange}
                                triedSubmitting={triedSubmitting}
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
                                onClick={handleCompute}
                                sx={{mt: 4, width: "30%"}}
                            >
                                Compute
                            </Button>
                        </Box>
                    </Box>
                </Paper>
            </Box>
        </Container>
    )
}
