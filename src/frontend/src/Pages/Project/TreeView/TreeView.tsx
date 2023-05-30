import * as React from "react"
import {Box} from "@mui/material"
import {TreeViewInfoCard} from "../../../Components/Project/TreeView/TreeViewInfoCard"
import ForceDirectedLayout from "./Layouts/ForceDirectedLayout/ForceDirectedLayout"
import PhylogramLayout from "./Layouts/PhylogramLayout/PhylogramLayout";
import RadialLayout from "./Layouts/RadialLayout/RadialLayout";
import {useTreeView} from "./useTreeView";


/**
 * TreeView page.
 */
export default function TreeView() {
    const {treeView} = useTreeView()

    return (
        <Box sx={{position: "relative", width: "90%"}}>
            <TreeViewInfoCard treeView={treeView}/>
            {
                treeView.layout === "force-directed"
                    ? <ForceDirectedLayout/>
                    : treeView.layout == "radial"
                        ? <RadialLayout/>
                        : <PhylogramLayout/> // TODO: Discuss the name of this layout: rectangular, rectangular-cladogram, rectangular-phylogram?
            }
        </Box>
    )
}
