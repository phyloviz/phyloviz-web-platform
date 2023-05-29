import * as React from "react"
import {Box} from "@mui/material"
import {useTreeView} from "./useTreeView"
import {TreeViewInfoCard} from "../../../Components/Project/TreeView/TreeViewInfoCard"
import {TreeViewSearchCard} from "../../../Components/Project/TreeView/TreeViewSearchCard"
import {TreeViewSettingsCard} from "../../../Components/Project/TreeView/TreeViewSettingsCard"
import {ArcElement, Chart as ChartJS, Legend, Tooltip} from "chart.js";
import {Outlet, useOutlet} from "react-router-dom";
import {DoughnutChart} from "../../../Components/Project/TreeView/DoughnutChart";

ChartJS.register(ArcElement, Tooltip, Legend);

/**
 * TreeView page.
 */
export default function TreeView() {
    const outlet = useOutlet()
    const {
        canvasRef,
        treeView,

        pauseAnimation,
        restartAnimation,

        resetSimulationFilters,

        simulationConfig,
        nodeTransformationsConfig,

        isolateDataHeaders,
        selectedIsolateHeader,
        setSelectedIsolateHeader,

        doughnutChartTitle,
        doughnutChartData,

        handleTypingDataFilter,
        typingDataId,
        handleIsolateDataFilter,
        isolateDataId,

        handleSearch,
        handleExportOptions,
        handleExportFilters,

        handleZoomIn,
        handleZoomOut,
        toPrintRef,
        handlePrint,
    } = useTreeView()

    console.log("TreeView.tsx: treeView", treeView?.treeViewId)

    return (
        <Box sx={{position: "relative", width: "90%"}}>
            <TreeViewInfoCard treeView={treeView}/>
            {outlet
                ? <Outlet context={{treeView, typingDataId, isolateDataId}}/>
                : <>
                    <div ref={toPrintRef}>
                        <canvas ref={canvasRef}/>
                    </div>
                    {doughnutChartData != null &&
                        <DoughnutChart doughnutChartData={doughnutChartData} title={doughnutChartTitle}/>
                    }
                    <TreeViewSettingsCard
                        onPauseAnimation={pauseAnimation}
                        onRestartAnimation={restartAnimation}

                        simulationConfig={simulationConfig}
                        nodeTransformationsConfig={nodeTransformationsConfig}

                        isolateDataHeaders={isolateDataHeaders}
                        selectedIsolateHeader={selectedIsolateHeader}
                        setSelectedIsolateHeader={setSelectedIsolateHeader}

                        resetSimulationFilters={resetSimulationFilters}
                        onTypingDataFilter={handleTypingDataFilter}
                        onIsolateDataFilter={handleIsolateDataFilter}

                        onExportOptions={handleExportOptions}
                        onExportFilters={handleExportFilters}

                        onZoomIn={handleZoomIn}
                        onZoomOut={handleZoomOut}
                        onPrint={handlePrint}
                    />
                    <TreeViewSearchCard onSearch={handleSearch}/>
                </>
            }
        </Box>
    )
}
