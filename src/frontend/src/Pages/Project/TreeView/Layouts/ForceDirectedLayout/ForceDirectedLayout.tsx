import {Outlet, useOutlet} from "react-router-dom";
import {ArcElement, Chart as ChartJS, Legend, Tooltip} from "chart.js";
import {TreeViewSettingsCard} from "../../../../../Components/Project/TreeView/TreeViewSettingsCard";
import {DoughnutChart} from "../../../../../Components/Project/TreeView/DoughnutChart";
import {TreeViewSearchCard} from "../../../../../Components/Project/TreeView/TreeViewSearchCard";
import React from "react";
import {useForceDirectedLayout} from "./useForceDirectedLayout";

ChartJS.register(ArcElement, Tooltip, Legend);


/**
 * Force Directed Layout for visualizing the tree.
 */
export default function ForceDirectedLayout() {
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
    } = useForceDirectedLayout()


    return outlet
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
