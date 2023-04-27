import {Box, Button} from "@mui/material";
import * as React from "react";

interface TreeViewFiltersProps {
    onExport: () => void,
    resetSimulationFilters: () => void,
}

export function TreeViewFilters(
    {
        onExport,
        resetSimulationFilters,
    }: TreeViewFiltersProps
) {
    return <Box>
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