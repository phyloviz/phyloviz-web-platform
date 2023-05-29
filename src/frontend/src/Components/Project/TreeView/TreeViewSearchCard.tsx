import {Box, TextField} from "@mui/material"
import IconButton from "@mui/material/IconButton"
import {Search} from "@mui/icons-material"
import * as React from "react"

/**
 * Card for searching for a specific ST in the tree view page.
 */
export function TreeViewSearchCard({onSearch}: { onSearch: (searchST: string) => boolean }) {
    const [searchST, setSearchST] = React.useState<string>("")
    const [searchSTError, setSearchSTError] = React.useState<boolean>(false)

    function search() {
        if (!onSearch(searchST))
            setSearchSTError(true)
        else
            setSearchSTError(false)
    }

    return <Box sx={{
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
                search()
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
    </Box>
}