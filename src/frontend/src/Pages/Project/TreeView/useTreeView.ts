import {useNavigate, useOutletContext, useParams} from "react-router-dom"
import * as React from 'react';
import {useRef, useState} from 'react';
import {TreeView} from "../../../Services/Administration/models/projects/getProject/GetProjectOutputModel"
import {useProjectContext} from "../useProject"
import {GraphConfigInterface} from "./cosmos/config"
import {useReactToPrint} from "react-to-print";
import {BubbleDataPoint, ChartData, Point} from "chart.js";
import {WebUiUris} from "../../WebUiUris";
import {useCascadingInfoTreeView} from "./useCascadingInfoTreeView";
import {useGraph} from "./useGraph";
import {useIsolateDataRows} from "./useIsolateDataRows";
import {useSelectIsolateDataHeader} from "./useSelectIsolateDataHeader";
import {useIsolateDataHeaders} from "./useIsolateDataHeaders";
import {useSimulationConfig} from "./useSimulationConfig";
import {useNodeTransformationsConfig} from "./useNodeTransformationsConfig";

export type VizNode = {
    id: string
    // x: number
    // y: number
}

export type VizLink = {
    source: string
    target: string
    weight: number
}

export const defaultConfig: GraphConfigInterface<VizNode, VizLink> = {
    backgroundColor: "#FFFFFF",
    nodeSize: 4,
    nodeColor: "#4B5BBF",
    nodeGreyoutOpacity: 0.1,
    linkWidth: 0.1,
    linkColor: "#5F74C2",
    linkArrows: false,
    linkGreyoutOpacity: 0,
    spaceSize: 8192,
    simulation: {
        decay: 100000,
        gravity: 0.01,
        repulsionTheta: 0.5,
        linkDistance: 10,
        linkSpring: 1,
        friction: 0.85,
        repulsion: 0.2,
    }
}

const ZOOM_IN_SCALE = 1.1
const ZOOM_OUT_SCALE = 0.9
const ZOOM_DURATION = 100

/**
 * Hook for the TreeView page.
 */
export function useTreeView() {
    const pathParams = useParams<{ projectId: string, datasetId: string, treeViewId: string }>()
    const projectId = pathParams.projectId!
    const datasetId = pathParams.datasetId!
    const treeViewId = pathParams.treeViewId!

    const {project} = useProjectContext()
    const dataset = project?.datasets.find(dataset => dataset.datasetId === datasetId)
    const typingDataId = dataset?.typingDataId
    const isolateDataId = dataset?.isolateDataId

    const {cascadingInfoTreeView} = useCascadingInfoTreeView(dataset, treeViewId)

    const {isolateDataHeaders} = useIsolateDataHeaders(projectId, isolateDataId)
    const {isolateDataRows} = useIsolateDataRows(projectId, isolateDataId)

    const {graphRef, canvasRef} = useGraph(projectId, datasetId, treeViewId)

    const navigate = useNavigate()

    const simulationConfig = useSimulationConfig(graphRef)
    const nodeTransformationsConfig = useNodeTransformationsConfig(graphRef)

    const [doughnutChartTitle, setDoughnutChartTitle] = useState<string>("")
    const [doughnutChartData, setDoughnutChartData] = useState<ChartData<"doughnut", (number | [number, number] | Point | BubbleDataPoint | null)[], unknown> | null>(null)

    const {
        selectedIsolateHeader,
        setSelectedIsolateHeader
    } = useSelectIsolateDataHeader(isolateDataRows, graphRef, setDoughnutChartData, setDoughnutChartTitle)

    const toPrintRef = useRef(null);
    const reactToPrintContent = React.useCallback(() => {
        return toPrintRef.current;
    }, [toPrintRef.current]);
    const handlePrint = useReactToPrint({
        content: reactToPrintContent,
        documentTitle: "Tree Visualization"
    });

    return {
        canvasRef,
        treeView: cascadingInfoTreeView,

        pauseAnimation: () => graphRef.current?.pause(),
        restartAnimation: () => graphRef.current?.restart(),

        resetSimulationFilters: () => {
            // TODO: reset filters
        },

        simulationConfig,
        nodeTransformationsConfig,

        isolateDataHeaders,
        selectedIsolateHeader,
        setSelectedIsolateHeader,

        doughnutChartTitle,
        doughnutChartData,

        handleTypingDataFilter: () => navigate(WebUiUris.treeViewTypingData(projectId, datasetId, treeViewId)),
        typingDataId,
        handleIsolateDataFilter: () => navigate(WebUiUris.treeViewIsolateData(projectId, datasetId, treeViewId)),
        isolateDataId,

        handleSearch: (st: string): boolean => {
            return graphRef.current?.zoomToNodeById(st, 1000, 15, false)!
        },
        handleExportOptions: () => {
            // TODO: implement export options
        },
        handleExportFilters: () => {
            // TODO: implement export filters
        },

        handleZoomIn: () => graphRef.current?.zoom(graphRef.current.getZoomLevel() * ZOOM_IN_SCALE, ZOOM_DURATION),
        handleZoomOut: () => graphRef.current?.zoom(graphRef.current.getZoomLevel() * ZOOM_OUT_SCALE, ZOOM_DURATION),

        toPrintRef,
        handlePrint
    }
}

function capitalize(s: string): string {
    return s.charAt(0).toUpperCase() + s.slice(1)
}

/**
 * Context for the TreeView page.
 *
 * @property treeView - the tree view to display
 * @property typingDataId - the typing data id to filter by
 * @property isolateDataId - the isolate data id to filter by
 */
interface TreeViewContext {
    treeView: TreeView
    typingDataId: string
    isolateDataId?: string
}

/**
 * Hook to use the TreeView context.
 */
export function useTreeViewContext() {
    return useOutletContext<TreeViewContext>()
}

export function parseRGB(rgbString: string) {
    const rgbPattern = /^rgb\((\d+),(\d+),(\d+)\)$/;

    const matches = rgbString.match(rgbPattern);

    if (matches) {
        const red = parseInt(matches[1], 10);
        const green = parseInt(matches[2], 10);
        const blue = parseInt(matches[3], 10);

        return [red, green, blue];
    } else {
        throw new Error("Invalid RGB string");
    }
}