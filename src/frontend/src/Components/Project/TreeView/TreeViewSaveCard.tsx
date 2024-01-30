import * as React from "react"
import {Box, CircularProgress, FormControlLabel, Switch} from "@mui/material"
import IconButton from "@mui/material/IconButton"
import {Save} from "@mui/icons-material"
import Typography from "@mui/material/Typography";

/**
 * Props for TreeViewSaveCard component.
 */
interface TreeViewSaveCardProps {
    loadingGraph: boolean

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
        loadingGraph,
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
            opacity: loadingGraph ? 0.5 : 1,
            pointerEvents: loadingGraph ? "none" : "initial",
            display: "flex",
            flexDirection: "column"
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
            {savingGraph &&
                <Box sx={{
                    display: "flex",
                    flexDirection: "row",
                    alignItems: "center",
                    justifyContent: "center"
                }}>
                    <CircularProgress size={20} sx={{mr: 1}}/>
                    {savingGraph && <Typography>Saving...</Typography>}
                </Box>
            }
        </Box>
    </Box>
}