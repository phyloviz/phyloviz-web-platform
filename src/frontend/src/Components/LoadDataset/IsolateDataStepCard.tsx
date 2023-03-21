import * as React from "react";
import {ReactNode} from "react";
import UploadIcon from "@mui/icons-material/Upload";
import {Button, FormControl, InputLabel, Select, SelectChangeEvent} from "@mui/material";
import Typography from "@mui/material/Typography";

/**
 * Props for the IsolateDataStepCard component.
 *
 * @param onChange the function to be called when the isolate key is changed
 */
interface IsolateDataStepCardProps {
    onChange: (event: SelectChangeEvent, child: ReactNode) => void
}

/**
 * Card for the Isolate Data step of the Load Dataset page.
 */
export function IsolateDataStepCard({onChange}: IsolateDataStepCardProps) {
    return (
        <>
            <Button
                variant="contained"
                component="label"
                startIcon={<UploadIcon/>}
                sx={{
                    mt: 4,
                    mb: 2,
                    width: "100%"
                }}
            >
                Upload Isolate Data
                <input type="file" hidden/>
            </Button>

            <FormControl sx={{width: "100%", mb: 2}}>
                <InputLabel id="isolate-key">Key</InputLabel>
                <Select
                    labelId="isolate-key"
                    //value={}
                    label="Key"
                    onChange={onChange}
                >
                </Select>
            </FormControl>

            <Typography display="inline" variant="caption" align={"left"} sx={{width: "100%", whiteSpace: "pre-wrap"}}>
                If you have any type of ancillary data about the isolates (Demographic information, Epidemiological
                information, Antibiotic Resistance, Gene presence absence,etc) you can load a tab separated file to
                further represent it on the algorithm. This file should be a tab separated file with the first row with
                column headers (field names). One of the fields should be the Sequence identifier. Choose this field in
                the Key drop down menu to link the ancillary data to the allelic profile data.
                If you don't have any available data or don't wish do load any leave this fields blank an press Finish.
            </Typography>
        </>
    );
}