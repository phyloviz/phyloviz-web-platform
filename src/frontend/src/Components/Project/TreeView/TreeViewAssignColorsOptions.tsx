import {Button, Collapse, FormControl, FormHelperText, InputLabel, MenuItem, Select} from "@mui/material";
import ExpandMoreIcon from "@mui/icons-material/ExpandMore";
import * as React from "react";

interface TreeViewAssignColorsOptionsProps {
    expanded: boolean

    isolateDataHeaders: string[]
    selectedIsolateHeader: string
    setSelectedIsolateHeader: (selectedHeader: string) => void

    loadingIsolateDataRows: boolean

    onClick: () => void
}

export function TreeViewAssignColorsOptions(
    {
        expanded,

        isolateDataHeaders,
        selectedIsolateHeader,
        setSelectedIsolateHeader,

        loadingIsolateDataRows,

        onClick
    }: TreeViewAssignColorsOptionsProps
) {
    return <>
        <Button
            onClick={() => onClick()}
            size={"small"}
            startIcon={<ExpandMoreIcon color={"inherit"}/>}>
            Assign Colors
        </Button>
        <Collapse in={expanded} timeout={"auto"}
                  sx={{border: 1, borderColor: 'divider', borderRadius: 1, p: 3}}
                  unmountOnExit>
            <FormControl sx={{width: "100%", mb: 1, mt: 2}} size="small">
                <InputLabel id="color-by-profile">Color by Profile</InputLabel>
                <Select
                    labelId="color-by-profile"
                    label="Color by Profile"
                    disabled={true} // TODO: Implement
                    value={""}
                    onChange={() => {
                    }}

                    // value={colorByProfile}
                    // onChange={(event, child) => setSelectedTypingLocus(event.target.value)}
                    MenuProps={{PaperProps: {sx: {maxHeight: 150}}}}
                >
                    {/*{profiles != null && profiles.map((value) => (*/}
                    {/*    <MenuItem key={value} value={value}>*/}
                    {/*        {value}*/}
                    {/*    </MenuItem>*/}
                    {/*))}*/}
                </Select>
                <FormHelperText>Not implemented yet.</FormHelperText>
            </FormControl>
            <FormControl sx={{width: "100%", mb: 1, mt: 2}} size="small">
                <InputLabel id="color-by-isolate-data">Color by Isolate Data</InputLabel>
                <Select
                    labelId="color-by-isolate-data"
                    label="Color by Isolate Data"
                    value={selectedIsolateHeader}
                    disabled={isolateDataHeaders.length === 0 && loadingIsolateDataRows}
                    onChange={(event, child) => setSelectedIsolateHeader(event.target.value)}
                    MenuProps={{PaperProps: {sx: {maxHeight: 150}}}}
                >
                    <MenuItem value="">
                        <em>None</em>
                    </MenuItem>
                    {isolateDataHeaders.map((value) => (
                        <MenuItem key={value} value={value}>
                            {value}
                        </MenuItem>
                    ))}
                </Select>
                {(isolateDataHeaders.length === 0 && loadingIsolateDataRows) ?
                    <FormHelperText>Can't select. Isolate Data still loading.</FormHelperText>
                    : (isolateDataHeaders.length === 0 && !loadingIsolateDataRows) ?
                        <FormHelperText>Can't select. Couldn't load Isolate Data.</FormHelperText> : null
                }
            </FormControl>
        </Collapse>
    </>
}