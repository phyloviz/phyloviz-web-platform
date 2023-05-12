import * as React from "react"
import {ReactNode, useState} from "react"
import {Box, Button, Collapse, SelectChangeEvent} from "@mui/material"
import IconButton from "@mui/material/IconButton"
import {Pause, PhotoCamera, PlayArrow, ZoomIn, ZoomOut} from "@mui/icons-material"
import {TreeViewOptions} from "./TreeViewOptions";
import {TreeViewFilters} from "./TreeViewFilters";

/**
 * Props for TreeViewSettingsCard component.
 */
interface TreeViewSettingsCardProps {
    onPauseAnimation: () => void
    onRestartAnimation: () => void
    resetSimulationConfig: () => void
    resetSimulationFilters: () => void

    linkSpring: number
    linkDistance: number
    gravity: number
    friction: number
    repulsion: number
    repulsionTheta: number
    decay: number

    onChangeLinkSpring: (linkSpring: number) => void
    onChangeLinkDistance: (linkDistance: number) => void
    onChangeGravity: (gravity: number) => void
    onChangeFriction: (friction: number) => void
    onChangeRepulsion: (repulsion: number) => void
    onChangeRepulsionTheta: (repulsionTheta: number) => void
    onChangeDecay: (decay: number) => void

    nodeSize: number
    nodeLabel: boolean
    nodeLabelSize: number
    linkLength: number
    linkLabel: boolean
    linkLabelSize: number
    linkLabelType: string

    onChangeNodeSize: (nodeSize: number) => void
    onChangeNodeLabel: (event: React.ChangeEvent<HTMLInputElement>) => void
    onChangeNodeLabelSize: (nodeLabelSize: number) => void
    onChangeLinkLength: (linkLength: number) => void
    onChangeLinkLabel: (event: React.ChangeEvent<HTMLInputElement>) => void
    onChangeLinkLabelSize: (linkLabelSize: number) => void
    onChangeLinkLabelType: (event: SelectChangeEvent, child: ReactNode) => void

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
        resetSimulationConfig,
        resetSimulationFilters,

        linkSpring,
        linkDistance,
        gravity,
        friction,
        repulsion,
        repulsionTheta,
        decay,

        onChangeLinkSpring,
        onChangeLinkDistance,
        onChangeGravity,
        onChangeFriction,
        onChangeRepulsion,
        onChangeRepulsionTheta,
        onChangeDecay,

        nodeSize,
        nodeLabel,
        nodeLabelSize,
        linkLength,
        linkLabel,
        linkLabelSize,
        linkLabelType,

        onChangeNodeSize,
        onChangeNodeLabel,
        onChangeNodeLabelSize,
        onChangeLinkLength,
        onChangeLinkLabel,
        onChangeLinkLabelSize,
        onChangeLinkLabelType,

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
                linkSpring={linkSpring} onChangeLinkSpring={onChangeLinkSpring}
                linkDistance={linkDistance} onChangeLinkDistance={onChangeLinkDistance}
                gravity={gravity} onChangeGravity={onChangeGravity}
                friction={friction} onChangeFriction={onChangeFriction}
                repulsion={repulsion} onChangeRepulsion={onChangeRepulsion}
                repulsionTheta={repulsionTheta} onChangeRepulsionTheta={onChangeRepulsionTheta}
                nodeSize={nodeSize} onChangeNodeSize={onChangeNodeSize}
                nodeLabel={nodeLabel} onChangeNodeLabel={onChangeNodeLabel}
                nodeLabelSize={nodeLabelSize} onChangeNodeLabelSize={onChangeNodeLabelSize}
                linkLength={linkLength} onChangeLinkLength={onChangeLinkLength}
                linkLabel={linkLabel} onChangeLinkLabel={onChangeLinkLabel}
                linkLabelSize={linkLabelSize} onChangeLinkLabelSize={onChangeLinkLabelSize}
                linkLabelType={linkLabelType} onChangeLinkLabelType={onChangeLinkLabelType}
                onExport={onExportOptions}
                resetSimulationConfig={resetSimulationConfig}/>
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