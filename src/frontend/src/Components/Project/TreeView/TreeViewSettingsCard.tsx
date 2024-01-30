import * as React from "react"
import {useState} from "react"
import {Box, Button, Collapse} from "@mui/material"
import IconButton from "@mui/material/IconButton"
import {Pause, PhotoCamera, PlayArrow, ZoomIn, ZoomOut} from "@mui/icons-material"
import {TreeViewOptions} from "./TreeViewOptions";
import {SimulationConfig} from "../../../Pages/Project/TreeView/Layouts/ForceDirectedLayout/utils/useSimulationConfig";
import {
    GraphTransformationsConfig
} from "../../../Pages/Project/TreeView/Layouts/ForceDirectedLayout/utils/useGraphTransformationsConfig";

/**
 * Props for TreeViewSettingsCard component.
 */
interface TreeViewSettingsCardProps {
    loadingGraph: boolean

    simulationRunning: boolean

    onPauseAnimation: () => void
    onRestartAnimation: () => void

    simulationConfig: SimulationConfig,
    graphTransformationsConfig: GraphTransformationsConfig,

    isolateDataHeaders: string[]
    selectedIsolateHeader: string
    setSelectedIsolateHeader: (selectedHeader: string) => void

    loadingIsolateDataRows: boolean

    resetSimulationFilters: () => void
    onTypingDataFilter: () => void
    onIsolateDataFilter: () => void

    onExportOptions: () => void
    onExportFilters: () => void

    onZoomIn: () => void
    onZoomOut: () => void
    onPrint: () => void
}

/**
 * Card with settings for the tree view.
 */
export function TreeViewSettingsCard(
    {
        loadingGraph,

        simulationRunning,

        onPauseAnimation,
        onRestartAnimation,

        simulationConfig,
        graphTransformationsConfig,

        isolateDataHeaders,
        selectedIsolateHeader,
        setSelectedIsolateHeader,

        loadingIsolateDataRows,

        resetSimulationFilters,
        onTypingDataFilter,
        onIsolateDataFilter,

        onExportOptions,
        onExportFilters,

        onZoomIn,
        onZoomOut,
        onPrint
    }: TreeViewSettingsCardProps
) {
    const [animationRunning, setAnimationRunning] = useState(simulationRunning)
    const [transformationsOpen, setTransformationsOpen] = useState(false)

    return <Box sx={{
        opacity: loadingGraph ? 0.5 : 1,
        pointerEvents: loadingGraph ? "none" : "initial",
        position: "absolute",
        bottom: 0,
        left: 0,
        zIndex: 1,
        backgroundColor: "white",
        borderRadius: 1,
        p: 1,
        m: 1,
        border: 1,
        borderColor: 'divider',
    }}>
        <Collapse in={transformationsOpen}>
            <TreeViewOptions
                simulationConfig={simulationConfig}
                graphTransformationsConfig={graphTransformationsConfig}

                isolateDataHeaders={isolateDataHeaders}
                selectedIsolateHeader={selectedIsolateHeader}
                setSelectedIsolateHeader={setSelectedIsolateHeader}

                loadingIsolateDataRows={loadingIsolateDataRows}

                onExport={onExportOptions}
            />
        </Collapse>
        <Box sx={{
            display: "flex",
            flexDirection: "row",
            justifyContent: "space-between",
        }}>
            <Button
                onClick={() => setTransformationsOpen((transformationsOpen) => !transformationsOpen)}
                size="small"
                variant={transformationsOpen ? "contained" : "text"}
                sx={{mr: 1}}
            >
                Transformations
            </Button>
            <IconButton size="small" onClick={onZoomOut}>
                <ZoomOut/>
            </IconButton>
            <IconButton size="small" onClick={onZoomIn}>
                <ZoomIn/>
            </IconButton>
            <IconButton size="small" onClick={onPrint}>
                <PhotoCamera/>
            </IconButton>
            <IconButton size="small" onClick={() => {
                if (animationRunning)
                    onPauseAnimation()
                else
                    onRestartAnimation()

                setAnimationRunning((animationRunning) => !animationRunning)
            }}>
                {
                    animationRunning
                        ? <Pause/>
                        : <PlayArrow/>
                }
            </IconButton>
        </Box>
    </Box>
}