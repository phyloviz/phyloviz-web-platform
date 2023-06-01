import {Box, Button} from "@mui/material";
import * as React from "react";
import {SimulationConfig} from "../../../Pages/Project/TreeView/Layouts/ForceDirectedLayout/utils/useSimulationConfig";
import {
    GraphTransformationsConfig
} from "../../../Pages/Project/TreeView/Layouts/ForceDirectedLayout/utils/useGraphTransformationsConfig";
import {TreeViewLayoutPropertiesOptions} from "./TreeViewLayoutPropertiesOptions";
import {TreeViewNodePropertiesOptions} from "./TreeViewNodePropertiesOptions";
import {TreeViewLinkPropertiesOptions} from "./TreeViewLinkPropertiesOptions";
import {TreeViewAssignColorsOptions} from "./TreeViewAssignColorsOptions";


interface TreeViewOptionsProps {
    simulationConfig: SimulationConfig
    nodeTransformationsConfig: GraphTransformationsConfig

    isolateDataHeaders: string[]
    selectedIsolateHeader: string
    setSelectedIsolateHeader: (selectedHeader: string) => void

    loadingIsolateDataRows: boolean

    onExport: () => void
}

// Maybe create a context to manage the state of the tree view
export function TreeViewOptions(
    {
        simulationConfig,
        nodeTransformationsConfig,

        isolateDataHeaders,
        selectedIsolateHeader,
        setSelectedIsolateHeader,

        loadingIsolateDataRows,

        onExport
    }: TreeViewOptionsProps
) {
    const [layoutPropertiesExpanded, setLayoutPropertiesExpanded] = React.useState(false)
    const [nodePropertiesExpanded, setNodePropertiesExpanded] = React.useState(false)
    const [linkPropertiesExpanded, setLinkPropertiesExpanded] = React.useState(false)
    const [assignColorsExpanded, setAssignColorsExpanded] = React.useState(false)

    return <Box sx={{
        display: "flex",
        flexDirection: "column",
        justifyContent: "space-between",
    }}>
        <TreeViewLayoutPropertiesOptions
            expanded={layoutPropertiesExpanded}
            simulationConfig={simulationConfig}
            onClick={() => {
                setLayoutPropertiesExpanded((prev) => !prev)
                setNodePropertiesExpanded(false)
                setAssignColorsExpanded(false)
                setLinkPropertiesExpanded(false)
            }}
        />

        <TreeViewNodePropertiesOptions
            expanded={nodePropertiesExpanded}
            nodeTransformationsConfig={nodeTransformationsConfig}
            onClick={() => {
                setNodePropertiesExpanded((prev) => !prev);
                setLayoutPropertiesExpanded(false);
                setAssignColorsExpanded(false);
                setLinkPropertiesExpanded(false);
            }}
        />

        <TreeViewLinkPropertiesOptions
            expanded={linkPropertiesExpanded}
            nodeTransformationsConfig={nodeTransformationsConfig}
            onClick={() => {
                setLinkPropertiesExpanded((prev) => !prev)
                setLayoutPropertiesExpanded(false)
                setAssignColorsExpanded(false)
                setNodePropertiesExpanded(false)
            }}
        />

        <TreeViewAssignColorsOptions
            expanded={assignColorsExpanded}
            isolateDataHeaders={isolateDataHeaders}
            selectedIsolateHeader={selectedIsolateHeader}
            setSelectedIsolateHeader={setSelectedIsolateHeader}
            loadingIsolateDataRows={loadingIsolateDataRows}
            onClick={() => {
                setAssignColorsExpanded((prev) => !prev)
                setLinkPropertiesExpanded(false)
                setLayoutPropertiesExpanded(false)
                setNodePropertiesExpanded(false)
            }}
        />

        <Box sx={{display: "flex"}}>
            <Button size="small" sx={{flex: 1,}} onClick={onExport}>
                Export
            </Button>
            <Button size="small" sx={{flex: 1,}} onClick={() => {
                simulationConfig.resetAll()
                nodeTransformationsConfig.resetAll()
            }}>
                Reset
            </Button>
        </Box>
    </Box>;
}