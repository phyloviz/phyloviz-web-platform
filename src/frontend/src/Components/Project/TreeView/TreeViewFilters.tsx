import {Box, Button} from "@mui/material";
import * as React from "react";
import {IsolateDataIcon, TypingDataIcon} from "../../Shared/Icons";


interface TreeViewFiltersProps {
    onExport: () => void
    resetSimulationFilters: () => void
}

export function TreeViewFilters(
    {
        onExport,
        resetSimulationFilters,
    }: TreeViewFiltersProps
) {
    return <Box>
        {
            // TODO: ver mais filtros
        }
        <Box sx={{display: "flex"}}>
            <Button
                size="small"
                sx={{flex: 1,}}
                onClick={() => {
                    /*TODO: To be implemented*/
                    // Navigate to Typing Data associated with this tree view for filtering
                }}
                startIcon={<TypingDataIcon/>}
            >
                Typing Data
            </Button>
            <Button
                size="small"
                sx={{flex: 1,}}
                onClick={() => {
                    /*TODO: To be implemented*/
                    // Navigate to Isolate Data associated with this tree view for filtering
                }}
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