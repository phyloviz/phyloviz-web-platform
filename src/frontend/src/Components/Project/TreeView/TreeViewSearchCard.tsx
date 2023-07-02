import {Box, TextField} from "@mui/material"
import IconButton from "@mui/material/IconButton"
import {Search} from "@mui/icons-material"
import * as React from "react"
import ShuffleIcon from '@mui/icons-material/Shuffle';
import CasinoIcon from '@mui/icons-material/Casino';
/**
 * Card for searching for a specific ST in the tree view page.
 */
export function TreeViewSearchCard({loadingGraph, onSearch, focusRandom}: {
    loadingGraph: boolean,
    onSearch: (searchST: string) => boolean,
    focusRandom: () => void
}) {
    const [searchST, setSearchST] = React.useState<string>("")
    const [searchSTError, setSearchSTError] = React.useState<boolean>(false)

    function search() {
        if (!onSearch(searchST))
            setSearchSTError(true)
        else
            setSearchSTError(false)
    }

    return <Box sx={{
        opacity: loadingGraph ? 0.5 : 1,
        pointerEvents: loadingGraph ? "none": "initial",
        position: "absolute",
        bottom: 0,
        right: 0,
        zIndex: 1,
        backgroundColor: "white",
        borderRadius: 1,
        p: 1,
        me: 1,
        border: 1,
        borderColor: 'divider',
    }}>
        <TextField
            sx={{ml: 1, flex: 1}}
            margin={"dense"}
            placeholder="Search for a ST"

            value={searchST}
            onChange={(e) => setSearchST(e.target.value)}
            onKeyPress={(e) => {
                if (e.key === 'Enter') {
                    search()
                }
            }}
            variant={"standard"}
            size={"small"}
            error={searchSTError}
        />
        <IconButton type="button" size={"small"} sx={{p: '10px'}} onClick={() => {
            search()
        }}>
            <Search/>
        </IconButton>
        <IconButton type="button" size={"small"} sx={{p: '10px'}} onClick={() => {
            focusRandom()
        }}>
            <CasinoIcon/>
        </IconButton>

    </Box>
}