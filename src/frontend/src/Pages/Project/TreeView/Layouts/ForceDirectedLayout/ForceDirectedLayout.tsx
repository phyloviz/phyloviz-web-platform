import {Outlet, useOutlet} from "react-router-dom";
import {TreeViewSettingsCard} from "../../../../../Components/Project/TreeView/TreeViewSettingsCard";
import {DoughnutChart} from "../../../../../Components/Project/TreeView/DoughnutChart";
import {TreeViewSearchCard} from "../../../../../Components/Project/TreeView/TreeViewSearchCard";
import React from "react";
import {useForceDirectedLayout} from "./useForceDirectedLayout";
import LoadingSpinner from "../../../../../Components/Shared/LoadingSpinner";
import {TreeViewSaveCard} from "../../../../../Components/Project/TreeView/TreeViewSaveCard";
import ListItem from '@mui/material/ListItem';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemText from '@mui/material/ListItemText';
import Box from "@mui/material/Box";
import List from '@mui/material/List';

function ClusterList({numClusters, setSelectedCluster, selectedCluster}: any) {
    return (
        <Box
            sx={{
                position: "absolute",
                left: 0,
                top: "10%",
                zIndex: 1,
                backgroundColor: "white",
                borderRadius: 3,
                p: 1,
                ml: "10px",
                border: 1,
                borderColor: 'divider',
                width: "170px",
                height: "450px",
                display: "flex",
                flexDirection: "column",
                overflow: "auto",
            }}>

            <List>
                {
                    Array.from(Array(numClusters).keys()).map((i) =>
                        <ListItem key={i} disablePadding>
                            <ListItemButton selected={i == selectedCluster} onClick={(event) => setSelectedCluster(i)}>
                                <ListItemText primary={`Cluster ${i}`}/>
                            </ListItemButton>
                        </ListItem>
                    )
                }
            </List>
        </Box>
    );
}

/**
 * Force Directed Layout for visualizing the tree.
 */
export default function ForceDirectedLayout() {
    const outlet = useOutlet()
    const {
        canvasRef,
        loadingGraph,
        treeView,
        numClusters,
        selectedCluster,
        setSelectedCluster,
        focusRandom,

        autosave,
        switchAutosave,
        forceSave,
        savingGraph,

        pauseAnimation,
        restartAnimation,

        resetSimulationFilters,

        simulationConfig,
        graphTransformationsConfig,

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
            {
                !loadingGraph &&
                <ClusterList numClusters={numClusters} selectedCluster={selectedCluster}
                             setSelectedCluster={setSelectedCluster}/>
            }

            {doughnutChartData != null &&
                <DoughnutChart doughnutChartData={doughnutChartData}
                               title={doughnutChartTitle}
                />
            }
            <TreeViewSettingsCard
                loadingGraph={loadingGraph}

                simulationRunning={simulationConfig.simulationRunning}

                onPauseAnimation={pauseAnimation}
                onRestartAnimation={restartAnimation}

                simulationConfig={simulationConfig}
                graphTransformationsConfig={graphTransformationsConfig}

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

                focusRandom={focusRandom}
                onSearch={handleSearch}
            />
            <TreeViewSaveCard
                loadingGraph={loadingGraph}

                autosave={autosave}
                switchAutosave={switchAutosave}
                forceSave={forceSave}
                savingGraph={savingGraph}
            />
        </>
}
