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
 * ComputeRectangularLayout page.
 */
export default function ComputeRectangularLayout() {
    const {
        trees,
        selectedTree,
        handleTreeChange,
        triedSubmitting,
        handleCancel,
        handleCompute,
        error,
        clearError
    } = useComputeTreeView(ComputeTreeViewLayout.RECTANGULAR)

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
                        Compute Rectangular Layout
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
                                    "Rectangular layout algorithms are a class of algorithms for arranging and visualizing graphs " +
                                    "in a rectangular or grid-like fashion. Their purpose is to position the nodes of a graph " +
                                    "in a two-dimensional grid, with edges connecting adjacent nodes. This arrangement " +
                                    "provides a structured and organized representation of the graph, making it easier to " +
                                    "identify patterns, clusters, and relationships between nodes." +
                                    "In a rectangular layout, the nodes are typically placed in rows and columns, with " +
                                    "each node occupying a cell in the grid. The position of a node within the grid is " +
                                    "determined based on various factors, such as its connectivity to other nodes, its " +
                                    "importance or centrality in the graph, or specific constraints imposed by the " +
                                    "application or domain." +
                                    "Rectangular layouts are commonly used for visualizing networks, social graphs, " +
                                    "and other types of structured data. They offer a straightforward and intuitive " +
                                    "representation that facilitates easy comprehension and analysis of the underlying " +
                                    "graph structure."
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
