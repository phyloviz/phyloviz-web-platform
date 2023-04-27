import * as React from "react"
import {Box} from "@mui/material"
import {useTreeView} from "./useTreeView"
import {TreeViewInfoCard} from "../../../Components/Project/TreeView/TreeViewInfoCard"
import {TreeViewSearchCard} from "../../../Components/Project/TreeView/TreeViewSearchCard"
import {TreeViewSettingsCard} from "../../../Components/Project/TreeView/TreeViewSettingsCard"


/**
 * TreeView page.
 */
export default function TreeView() {
    const {
        treeView,
        canvasRef,
        restartAnimation,
        pauseAnimation,

        resetSimulationConfig,
        resetSimulationFilters,

        linkSpring,
        linkDistance,
        gravity,
        friction,
        repulsion,
        repulsionTheta,
        decay,

        updateLinkSpring,
        updateLinkDistance,
        updateGravity,
        updateFriction,
        updateRepulsion,
        updateRepulsionTheta,
        updateDecay,

        handleExportOptions,
        handleExportFilters,

        handleZoomIn,
        handleZoomOut,
        toPrintRef,
        handlePrint,
    } = useTreeView()

    return (
        <Box sx={{position: "relative", width: "90%"}}>
            <TreeViewInfoCard treeView={treeView}/>
            <div ref={toPrintRef}>
                <canvas ref={canvasRef}/>
            </div>
            <TreeViewSettingsCard
                onPauseAnimation={pauseAnimation}
                onRestartAnimation={restartAnimation}
                resetSimulationConfig={resetSimulationConfig}
                resetSimulationFilters={resetSimulationFilters}

                linkSpring={linkSpring}
                linkDistance={linkDistance}
                gravity={gravity}
                friction={friction}
                repulsion={repulsion}
                repulsionTheta={repulsionTheta}
                decay={decay}

                onChangeLinkSpring={updateLinkSpring}
                onChangeLinkDistance={updateLinkDistance}
                onChangeGravity={updateGravity}
                onChangeFriction={updateFriction}
                onChangeRepulsion={updateRepulsion}
                onChangeRepulsionTheta={updateRepulsionTheta}
                onChangeDecay={updateDecay}

                onExportOptions={handleExportOptions}
                onExportFilters={handleExportFilters}

                onZoomIn={handleZoomIn}
                onZoomOut={handleZoomOut}
                onPrint={handlePrint}
            />
            <TreeViewSearchCard/>
        </Box>
    )
}
