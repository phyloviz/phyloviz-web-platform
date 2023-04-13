import * as React from "react"
import {StyledTreeItem} from "../../StyledTreeItem"
import {Cyclone, Download, Info, ScatterPlot, TableView} from "@mui/icons-material"
import {Menu, MenuItem} from "@mui/material"
import DatasetIcon from "@mui/icons-material/Dataset"
import {Dataset} from "../../../../../Services/administration/models/getProject/GetProjectOutputModel"
import {useContextMenu} from "../../useContextMenu"
import {NestedMenuItem} from "mui-nested-menu"
import ArrowRightIcon from "@mui/icons-material/ArrowRight"
import {WebUiUris} from "../../../../../Utils/WebUiUris"
import {useNavigate, useParams} from "react-router-dom"
import {TreesTreeItem} from "./Trees/TreesTreeItem"
import {DistancesTreeItem} from "./Distances/DistancesTreeItem"
import {IsolateDataTreeItem} from "./IsolateDataTreeItem"
import {TypingDataTreeItem} from "./TypingDataTreeItem"
import {TreeViewsTreeItem} from "./TreeViews/TreeViewsTreeItem";
import ListItemIcon from "@mui/material/ListItemIcon";
import ListItemText from "@mui/material/ListItemText";

/**
 * Props for the DatasetTreeItem component.
 *
 * @property nodeId id of the tree item
 * @property dataset dataset of the tree item
 * @property children children of the tree item
 */
interface DatasetTreeItemProps {
    nodeId: string
    dataset: Dataset
}


/**
 * Tree item for a dataset of a project.
 */
export function DatasetTreeItem({nodeId, dataset}: DatasetTreeItemProps) {
    const {
        contextMenu,
        handleContextMenu,
        handleClose
    } = useContextMenu()

    const {projectId} = useParams<{ projectId: string }>()
    const navigate = useNavigate()
    const computeTreeOptions = [
        {
            label: "goeBURST",
            url: WebUiUris.computeConfigGoeburst(projectId!, dataset.datasetId)
        },
        {
            label: "goeBURST Full MST",
            url: WebUiUris.computeConfigGoeburstFullMst(projectId!, dataset.datasetId)
        },
        {
            label: "Hierarchical Clustering",
            url: WebUiUris.computeConfigHierarchicalClustering(projectId!, dataset.datasetId)
        },
        {
            label: "Neighbor Joining",
            url: WebUiUris.computeConfigNeighborJoining(projectId!, dataset.datasetId)
        },
        {
            label: "nLV Graph",
            url: WebUiUris.computeConfigNlvGraph(projectId!, dataset.datasetId)
        }
    ]

    const computeDistanceMatrixOptions = [
        {
            label: "Hamming Distance",
            url: WebUiUris.computeConfigHammingDistance(projectId!, dataset.datasetId)
        }
    ]

    return (
        <>
            <StyledTreeItem
                nodeId={nodeId}
                labelText={dataset.name}
                labelIcon={DatasetIcon}
                handleContextMenu={handleContextMenu}
            >
                <TypingDataTreeItem nodeId="4" typingDataId={dataset.typingDataId}/>
                <IsolateDataTreeItem nodeId="5" isolateDataId={dataset.isolateDataId}/>
                <DistancesTreeItem nodeId="7" datasetId={dataset.datasetId}
                                   distanceMatrices={dataset.distanceMatrices}/>
                <TreesTreeItem nodeId="10" datasetId={dataset.datasetId} trees={dataset.trees}/>
                <TreeViewsTreeItem nodeId="11" datasetId={dataset.datasetId} treeViews={dataset.treeViews}/>
            </StyledTreeItem>
            <Menu
                open={contextMenu !== null}
                onClose={handleClose}
                anchorReference="anchorPosition"
                anchorPosition={
                    contextMenu !== null
                        ? {top: contextMenu.mouseY, left: contextMenu.mouseX}
                        : undefined
                }
            >
                <NestedMenuItem
                    leftIcon={<TableView color={"primary"}/>}
                    rightIcon={<ArrowRightIcon/>}
                    label={"Compute Distances"}
                    parentMenuOpen={true}
                    sx={{pl: 2}}
                >
                    {
                        computeDistanceMatrixOptions.map((option, index) => {
                            return (
                                <MenuItem key={index} onClick={() => {
                                    handleClose()
                                    navigate(option.url)
                                }}>
                                    {option.label}
                                </MenuItem>
                            )
                        })
                    }
                </NestedMenuItem>
                <NestedMenuItem
                    leftIcon={<ScatterPlot color={"primary"}/>}
                    rightIcon={<ArrowRightIcon/>}
                    label={"Compute Tree"}
                    parentMenuOpen={true}
                    sx={{pl: 2}}
                >
                    {
                        computeTreeOptions.map((option, index) => {
                            return (
                                <MenuItem key={index} onClick={() => {
                                    handleClose()
                                    navigate(option.url)
                                }}>
                                    {option.label}
                                </MenuItem>
                            )
                        })
                    }
                </NestedMenuItem>
                <MenuItem onClick={() => {/*TODO: To be implemented*/
                }}>
                    <ListItemIcon><Info color={"primary"}/></ListItemIcon>
                    <ListItemText>Dataset Details</ListItemText>
                </MenuItem>
            </Menu>
        </>
    )
}

