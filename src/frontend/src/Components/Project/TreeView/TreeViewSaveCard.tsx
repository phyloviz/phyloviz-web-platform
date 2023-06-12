import * as React from "react"
import {Box, FormControlLabel, Switch} from "@mui/material"
import IconButton from "@mui/material/IconButton"
import {Save} from "@mui/icons-material"

/**
 * Props for TreeViewSaveCard component.
 */
interface TreeViewSaveCardProps {
    autosave: boolean
    switchAutosave: () => void
    forceSave: () => void
    savingGraph: boolean
}

/**
 * Card with save options for TreeView.
 */
export function TreeViewSaveCard(
    {
        autosave,
        switchAutosave,
        forceSave,
        savingGraph
    }: TreeViewSaveCardProps
) {
    return <Box sx={{
        position: "absolute",
        top: 0,
        right: 0,
        zIndex: 1,
        backgroundColor: "white",
        borderRadius: 1,
        p: 1,
        m: 1,
        border: 1,
        borderColor: 'divider',
    }}>
        <Box sx={{
            display: "flex",
            flexDirection: "row",
            justifyContent: "space-between",
        }}>
            <FormControlLabel control={
                <Switch size="small" checked={autosave} onChange={switchAutosave}/>
            } label="AutoSave"
            />
            <IconButton size="small" onClick={forceSave}>
                <Save/>
            </IconButton>
        </Box>
    </Box>
}