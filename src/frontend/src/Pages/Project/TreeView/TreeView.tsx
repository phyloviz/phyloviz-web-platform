import * as React from "react"
import {Box} from "@mui/material"
import {useTreeView} from "./useTreeView"
import {TreeViewInfoCard} from "../../../Components/Project/TreeView/TreeViewInfoCard"
import {TreeViewSearchCard} from "../../../Components/Project/TreeView/TreeViewSearchCard"
import {TreeViewSettingsCard} from "../../../Components/Project/TreeView/TreeViewSettingsCard"
import {ArcElement, Chart as ChartJS, ChartOptions, Legend, Tooltip} from "chart.js";
import {Doughnut} from "react-chartjs-2";
import Typography from "@mui/material/Typography";

ChartJS.register(ArcElement, Tooltip, Legend);


const options: ChartOptions = {
    plugins: {
        legend: {
            position: 'bottom'
        }
    }
};
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

        handleSearch,

        isolateDataHeaders,
        colorByIsolateData,
        updateColorByIsolateData,

        handleExportOptions,
        handleExportFilters,
        doughnutChartData,
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
            {doughnutChartData != null && (
                <Box
                    sx={{
                        position: "absolute",
                        right: 0,
                        top: "20%",
                        zIndex: 1,
                        backgroundColor: "white",
                        borderRadius: 3,
                        p: 1,
                        mr: "10px",
                        border: 1,
                        borderColor: 'divider',
                        maxHeight: "300px",
                        overflow: "auto",
                    }}
                >
                    <Typography sx={{mb:2}}>{colorByIsolateData}</Typography>
                    <Doughnut data={doughnutChartData!} options={options}/>
                </Box>
            )
            }

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

                isolateDataHeaders={isolateDataHeaders}
                colorByIsolateData={colorByIsolateData}
                onChangeColorByIsolateData={updateColorByIsolateData}

                onExportOptions={handleExportOptions}
                onExportFilters={handleExportFilters}

                onZoomIn={handleZoomIn}
                onZoomOut={handleZoomOut}
                onPrint={handlePrint}
            />
            <TreeViewSearchCard onSearch={handleSearch}/>
        </Box>
    )
}
