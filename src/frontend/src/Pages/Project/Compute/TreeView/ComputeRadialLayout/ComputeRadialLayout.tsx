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
 * ComputeRadialLayout page.
 */
export default function ComputeRadialLayout() {
    const {
        trees,
        selectedTree,
        handleTreeChange,
        triedSubmitting,
        handleCancel,
        handleCompute,
        error,
        clearError
    } = useComputeTreeView(ComputeTreeViewLayout.RADIAL)

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
                        Compute Radial Layout
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
                                    "Radial layout algorithms are a class of algorithms for organizing and visualizing graphs " +
                                    "in a circular or radial arrangement. Their purpose is to position the nodes of a graph " +
                                    "along concentric circles or arcs, with edges radiating from the center outward. " +
                                    "This arrangement allows for a clear visualization of the graph's structure and " +
                                    "hierarchy, with the central node typically representing a focal point or root node." +
                                    "Radial layouts are often used for visualizing hierarchical data, such as organizational " +
                                    "charts or file directory structures." +
                                    "The nodes in a radial layout are placed based on various factors, including their " +
                                    "level or depth in the hierarchy, their connections to other nodes, and aesthetic " +
                                    "considerations. By positioning the nodes in a circular fashion, radial layouts " +
                                    "minimize edge crossings and provide a balanced and visually appealing representation " +
                                    "of the graph."
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
