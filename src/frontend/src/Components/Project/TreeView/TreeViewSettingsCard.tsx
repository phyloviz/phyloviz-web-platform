import * as React from "react"
import {useState} from "react"
import {Box, Button, Collapse} from "@mui/material"
import IconButton from "@mui/material/IconButton"
import {Pause, PhotoCamera, PlayArrow, ZoomIn, ZoomOut} from "@mui/icons-material"
import {TreeViewOptions} from "./TreeViewOptions";
import {TreeViewFilters} from "./TreeViewFilters";
import {SimulationConfig} from "../../../Pages/Project/TreeView/useSimulationConfig";
import {NodeTransformationsConfig} from "../../../Pages/Project/TreeView/useNodeTransformationsConfig";

/**
 * Props for TreeViewSettingsCard component.
 */
interface TreeViewSettingsCardProps {
    onPauseAnimation: () => void
    onRestartAnimation: () => void

    simulationConfig: SimulationConfig,
    nodeTransformationsConfig: NodeTransformationsConfig,

    isolateDataHeaders: string[]
    selectedIsolateHeader: string
    setSelectedIsolateHeader: (selectedHeader: string) => void

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
        onPauseAnimation,
        onRestartAnimation,

        simulationConfig,
        nodeTransformationsConfig,

        isolateDataHeaders,
        selectedIsolateHeader,
        setSelectedIsolateHeader,

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
    const [animationRunning, setAnimationRunning] = useState(true)
    const [settingsOpen, setSettingsOpen] = useState(false)
    const [filtersOpen, setFiltersOpen] = useState(false)

    return <Box sx={{
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
        <Collapse in={settingsOpen}>
            <TreeViewOptions
                simulationConfig={simulationConfig}
                nodeTransformationsConfig={nodeTransformationsConfig}

                isolateDataHeaders={isolateDataHeaders}
                selectedIsolateHeader={selectedIsolateHeader}
                setSelectedIsolateHeader={setSelectedIsolateHeader}

                onExport={onExportOptions}
            />
        </Collapse>
        <Collapse in={filtersOpen}>
            <TreeViewFilters
                onTypingDataFilter={onTypingDataFilter}
                onIsolateDataFilter={onIsolateDataFilter}
                onExport={onExportFilters}
                resetSimulationFilters={resetSimulationFilters}
            />
        </Collapse>
        <Box sx={{
            display: "flex",
            flexDirection: "row",
            justifyContent: "space-between",
        }}>
            <Button
                onClick={() => {
                    setFiltersOpen(false)
                    setSettingsOpen((settingsOpen) => !settingsOpen)
                }}
                size="small"
                variant={settingsOpen ? "contained" : "text"}
                sx={{mr: 1}}
            >
                Options
            </Button>
            <Button
                onClick={() => {
                    setSettingsOpen(false)
                    setFiltersOpen((filtersOpen) => !filtersOpen)
                }}
                size="small"
                variant={filtersOpen ? "contained" : "text"}
            >
                Filters
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