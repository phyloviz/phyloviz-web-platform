import {Box, InputBase} from "@mui/material"
import IconButton from "@mui/material/IconButton"
import {Search} from "@mui/icons-material"
import * as React from "react"

/**
 * Card for searching for a specific ST in the tree view page.
 */
export function TreeViewSearchCard() {
    // TODO: Implement search functionality

    return <Box sx={{
        position: "absolute",
        bottom: 0,
        right: 0,
        zIndex: 1,
        backgroundColor: "white",
        borderRadius: 1,
        p: 1,
        m: 1,
        border: 1,
        borderColor: 'divider',
    }}>
        <InputBase
            sx={{ml: 1, flex: 1}}
            margin={"dense"}
            placeholder="Search for a ST"
        />
        <IconButton type="button" size={"small"} sx={{p: '10px'}}>
            <Search/>
        </IconButton>
    </Box>
}