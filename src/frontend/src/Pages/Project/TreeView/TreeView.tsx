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
    const {
        treeView,
        toPrintRef,
        handlePrint,
        canvasRef,
        restartAnimation,
        pauseAnimation,
        linkSpring,
        updateLinkSpring,
        linkDistance,
        updateLinkDistance
    } = useTreeView()

    return (
        <Box sx={{position: "relative", width: "90%"}}>
            <TreeViewInfoCard treeView={treeView}/>
            <div ref={toPrintRef}>
                <canvas ref={canvasRef}/>
            </div>
            <TreeViewSettingsCard
                onPauseAnimation={pauseAnimation} onRestartAnimation={restartAnimation}
                linkSpring={linkSpring} linkDistance={linkDistance}
                onChangeLinkSpring={updateLinkSpring} onChangeLinkDistance={updateLinkDistance}
                onPrint={handlePrint}
            />
            <TreeViewSearchCard/>
        </Box>
    )
}
