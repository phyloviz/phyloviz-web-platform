import * as React from "react";
import {ReactNode} from "react";
import {FormControl, InputLabel, Select, SelectChangeEvent} from "@mui/material";
import Typography from "@mui/material/Typography";
import {FileUploader} from "react-drag-drop-files";

/**
 * Props for the IsolateDataStepCard component.
 *
 * @param onChange the function to be called when the isolate key is changed
 */
interface IsolateDataStepCardProps {
    onChange: (event: SelectChangeEvent, child: ReactNode) => void
}

/**
 * Card for the Isolate Data step of the Create Dataset page.
 */
export function IsolateDataStepCard({onChange}: IsolateDataStepCardProps) {
    return (
        <>
            <Typography variant="caption" align={"justify"} sx={{mb: 1, width: "100%"}}>
                Select the isolate data file from the project files or upload a new one.
            </Typography>
            <FormControl sx={{width: "100%", mb: 1}}>
                <InputLabel id="isolate-key">Isolate Data</InputLabel>
                <Select
                    labelId="isolate-key"
                    //value={}
                    label="Isolate Data"
                >
                </Select>
            </FormControl>
            <Typography variant="body2" align={"center"} sx={{mb: 1, width: "100%"}}>
                Or
            </Typography>
            <FileUploader
                //handleChange={handleChange}
                name="file"
                required
            />

            <Typography variant="caption" align={"justify"} sx={{mt: 4, width: "100%"}}>
                Select the isolate data key.
            </Typography>
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