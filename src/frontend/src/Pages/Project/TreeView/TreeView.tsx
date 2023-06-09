import * as React from "react"
import {Box} from "@mui/material"
import {TreeViewInfoCard} from "../../../Components/Project/TreeView/TreeViewInfoCard"
import ForceDirectedLayout from "./Layouts/ForceDirectedLayout/ForceDirectedLayout"
import RadialLayout from "./Layouts/RadialLayout/RadialLayout";
import {useTreeView} from "./useTreeView";
import RectangularLayout from "./Layouts/RectangularLayout/RectangularLayout";


/**
 * TreeView page.
 */
export default function TreeView() {
    const {treeView} = useTreeView()

    return (
        <Box sx={{position: "relative", width: "100%"}}>
            <TreeViewInfoCard treeView={treeView}/>
            {
                treeView.layout === "force-directed"
                    ? <ForceDirectedLayout/>
                    : treeView.layout == "radial"
                        ? <RadialLayout/>
                        : <RectangularLayout/>
            }
        </Box>
    )
}
