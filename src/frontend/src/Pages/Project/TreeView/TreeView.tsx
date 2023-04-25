import * as React from "react"
import {Box} from "@mui/material"
import {useTreeView} from "./useTreeView"
import {TreeViewInfoCard} from "../../../Components/Project/TreeView/TreeViewInfoCard"
import {TreeViewSearchCard} from "../../../Components/Project/TreeView/TreeViewSearchCard"
import {TreeViewSettingsCard} from "../../../Components/Project/TreeView/TreeViewSettingsCard"


/**
 * TreeView page.
 */
export default function TreeView() {
    const {treeView, canvasRef} = useTreeView()

    return (
        <Box sx={{position: "relative", width: "90%"}}>
            <TreeViewInfoCard treeView={treeView}/>
            <canvas ref={canvasRef}/>
            <TreeViewSettingsCard/>
            <TreeViewSearchCard/>
        </Box>
    )
}
