import * as React from "react";
import {StyledTreeItem} from "./StyledTreeItem";
import {Description, FilePresent, ScatterPlot, Sync, TableView} from "@mui/icons-material";
import {Menu, MenuItem} from "@mui/material";
import DatasetIcon from "@mui/icons-material/Dataset";
import {Dataset} from "../../../Services/administration/models/getProject/GetProjectOutputModel";
import {useContextMenu} from "./useContextMenu";
import {NestedMenuItem} from "mui-nested-menu";
import ArrowRightIcon from "@mui/icons-material/ArrowRight";
import {WebUiUris} from "../../../Utils/WebUiUris";
import {useNavigate, useParams} from "react-router-dom";

/**
 * Props for the DatasetTreeItem component.
 *
 * @param nodeId id of the tree item
 * @param dataset dataset of the tree item
 * @param children children of the tree item
 */
interface DatasetTreeItemProps {
    nodeId: string;
    dataset: Dataset;
}

/**
 * Tree item for a dataset of a project.
 */
export function DatasetTreeItem({nodeId, dataset}: DatasetTreeItemProps) {
    const {
        contextMenu,
        handleContextMenu,
        handleClose
    } = useContextMenu();


    const {projectId} = useParams<{ projectId: string }>();
    const navigate = useNavigate();
    const computeOptions = [
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

    return (
        <>
            <StyledTreeItem
                nodeId={nodeId}
                labelText={dataset.name}
                labelIcon={DatasetIcon}
                onContextMenu={handleContextMenu}
            >
                {/*TODO: Create a component to each file, with each own right click menu, etc*/}
                <StyledTreeItem nodeId="5" labelText="Isolate Data" labelIcon={FilePresent}/>
                <StyledTreeItem nodeId="4" labelText="Typing Data" labelIcon={Description}/>
                <StyledTreeItem nodeId="7" labelText="Distances" labelIcon={TableView}>
                    {
                        dataset.distanceMatrices.map((distance, index) => {
                            return (
                                <StyledTreeItem nodeId={index.toString()} labelText={distance.name}
                                                labelIcon={TableView}/>
                            );
                        })
                    }
                </StyledTreeItem>
                <StyledTreeItem nodeId="10" labelText="Trees" labelIcon={ScatterPlot}>
                    {
                        dataset.trees.map((tree, index) => {
                            return (
                                <StyledTreeItem nodeId={index.toString()} labelText={tree.name}
                                                labelIcon={ScatterPlot}/>
                            );
                        })
                    }
                </StyledTreeItem>
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
                    left={<Sync color={"primary"}/>}
                    rightIcon={<ArrowRightIcon/>}
                    label={"Compute"}
                    parentMenuOpen={true}
                >
                    {
                        computeOptions.map((option, index) => {
                            return (
                                <MenuItem key={index} onClick={() => {
                                    handleClose();
                                    navigate(option.url)
                                }}>
                                    {option.label}
                                </MenuItem>
                            );
                        })
                    }
                </NestedMenuItem>
            </Menu>
        </>
    );
}
