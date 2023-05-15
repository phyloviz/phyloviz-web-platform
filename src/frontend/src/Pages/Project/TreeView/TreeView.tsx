import * as React from "react"
import {Box} from "@mui/material"
import {useTreeView} from "./useTreeView"
import {TreeViewInfoCard} from "../../../Components/Project/TreeView/TreeViewInfoCard"
import {TreeViewSearchCard} from "../../../Components/Project/TreeView/TreeViewSearchCard"
import {TreeViewSettingsCard} from "../../../Components/Project/TreeView/TreeViewSettingsCard"
import {Outlet, useOutlet} from "react-router-dom";


/**
 * TreeView page.
 */
export default function TreeView() {
    const outlet = useOutlet()
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

        nodeSize,
        nodeLabel,
        nodeLabelSize,
        linkLength,
        linkLabel,
        linkLabelSize,
        linkLabelType,

        updateNodeSize,
        updateNodeLabel,
        updateNodeLabelSize,
        updateLinkLength,
        updateLinkLabel,
        updateLinkLabelSize,
        updateLinkLabelType,

        handleTypingDataFilter,
        typingDataId,
        handleIsolateDataFilter,
        isolateDataId,

        handleExportOptions,
        handleExportFilters,

        handleZoomIn,
        handleZoomOut,
        toPrintRef,
        handlePrint,
    } = useTreeView()

    // TODO: Fix bug where the outlet is not updated when the project is changed

    return (
        <Box sx={{position: "relative", width: "90%"}}>
            <TreeViewInfoCard treeView={treeView}/>
            {outlet
                ? <Outlet context={{treeView, typingDataId, isolateDataId}}/>
                : <>
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

                        nodeSize={nodeSize}
                        nodeLabel={nodeLabel}
                        nodeLabelSize={nodeLabelSize}
                        linkLength={linkLength}
                        linkLabel={linkLabel}
                        linkLabelSize={linkLabelSize}
                        linkLabelType={linkLabelType}

                        onChangeNodeSize={updateNodeSize}
                        onChangeNodeLabel={updateNodeLabel}
                        onChangeNodeLabelSize={updateNodeLabelSize}
                        onChangeLinkLength={updateLinkLength}
                        onChangeLinkLabel={updateLinkLabel}
                        onChangeLinkLabelSize={updateLinkLabelSize}
                        onChangeLinkLabelType={updateLinkLabelType}

                        onTypingDataFilter={handleTypingDataFilter}
                        onIsolateDataFilter={handleIsolateDataFilter}

                        onExportOptions={handleExportOptions}
                        onExportFilters={handleExportFilters}

                        onZoomIn={handleZoomIn}
                        onZoomOut={handleZoomOut}
                        onPrint={handlePrint}
                    />
                    <TreeViewSearchCard/>
                </>
            }
        </Box>
    )
}
