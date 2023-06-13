import {Outlet, useOutlet} from "react-router-dom";
import {TreeViewSettingsCard} from "../../../../../Components/Project/TreeView/TreeViewSettingsCard";
import {DoughnutChart} from "../../../../../Components/Project/TreeView/DoughnutChart";
import {TreeViewSearchCard} from "../../../../../Components/Project/TreeView/TreeViewSearchCard";
import React from "react";
import {useForceDirectedLayout} from "./useForceDirectedLayout";
import LoadingSpinner from "../../../../../Components/Shared/LoadingSpinner";


/**
 * Force Directed Layout for visualizing the tree.
 */
export default function ForceDirectedLayout() {
    const outlet = useOutlet()
    const {
        canvasRef,
        loadingGraph,
        treeView,

        pauseAnimation,
        restartAnimation,

        resetSimulationFilters,

        simulationConfig,
        nodeTransformationsConfig,

        isolateDataHeaders,
        selectedIsolateHeader,
        setSelectedIsolateHeader,

        loadingIsolateDataRows,

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
            <div style={{
                width: "100%",
                height: "100%"
            }}>
                {loadingGraph &&
                    <div style={{
                        width: "100%",
                        height: "100%",
                        position: "absolute",
                        display: "flex",
                        justifyContent: "center",
                        alignItems: "center"
                    }}>
                        <div style={{transform: "scale(1.5)"}}>
                            <LoadingSpinner text={"Loading Graph..."}/>
                        </div>
                    </div>
                }
                <div ref={toPrintRef} style={{
                    width: "100%",
                    height: "100%",
                    visibility: loadingGraph ? "hidden" : "visible"
                }}>
                    <canvas ref={canvasRef}/>
                </div>
            </div>
            {doughnutChartData != null &&
                <DoughnutChart doughnutChartData={doughnutChartData}
                               title={doughnutChartTitle}
                />
            }
            <TreeViewSettingsCard
                loadingGraph={loadingGraph}

                onPauseAnimation={pauseAnimation}
                onRestartAnimation={restartAnimation}

                simulationConfig={simulationConfig}
                nodeTransformationsConfig={nodeTransformationsConfig}

                isolateDataHeaders={isolateDataHeaders}
                selectedIsolateHeader={selectedIsolateHeader}
                setSelectedIsolateHeader={setSelectedIsolateHeader}

                loadingIsolateDataRows={loadingIsolateDataRows}

                resetSimulationFilters={resetSimulationFilters}
                onTypingDataFilter={handleTypingDataFilter}
                onIsolateDataFilter={handleIsolateDataFilter}

                onExportOptions={handleExportOptions}
                onExportFilters={handleExportFilters}

                onZoomIn={handleZoomIn}
                onZoomOut={handleZoomOut}
                onPrint={handlePrint}
            />
            <TreeViewSearchCard
                loadingGraph={loadingGraph}

                onSearch={handleSearch}
            />
        </>
}
