import {Box, Button} from "@mui/material";
import * as React from "react";
import {IsolateDataIcon, TypingDataIcon} from "../../Shared/Icons";


/**
 * Props for TreeViewFilters component.
 *
 * @property onTypingDataFilter - Callback for when the user wants to filter by typing data.
 * @property onIsolateDataFilter - Callback for when the user wants to filter by isolate data.
 * @property onExport - Callback for when the user wants to export the filters.
 * @property resetSimulationFilters - Callback for when the user wants to reset the simulation filters.
 */
interface TreeViewFiltersProps {
    onTypingDataFilter: () => void
    onIsolateDataFilter: () => void
    onExport: () => void
    resetSimulationFilters: () => void
}

export function TreeViewFilters(
    {
        onTypingDataFilter,
        onIsolateDataFilter,
        onExport,
        resetSimulationFilters,
    }: TreeViewFiltersProps
) {
    return <Box>
        <Box sx={{display: "flex"}}>
            <Button
                size="small"
                sx={{flex: 1,}}
                onClick={onTypingDataFilter}
                startIcon={<TypingDataIcon/>}
            >
                Typing Data
            </Button>
            <Button
                size="small"
                sx={{flex: 1,}}
                onClick={onIsolateDataFilter}
                startIcon={<IsolateDataIcon/>}
            >
                Isolate Data
            </Button>
        </Box>

        <Box sx={{display: "flex"}}>
            <Button size="small" sx={{flex: 1,}} onClick={onExport}>
                Export
            </Button>
            <Button size="small" sx={{flex: 1,}} onClick={resetSimulationFilters}>
                Reset
            </Button>
        </Box>
    </Box>;
}