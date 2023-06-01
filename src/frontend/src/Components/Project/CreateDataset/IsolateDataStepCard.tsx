import * as React from "react"
import {ReactNode} from "react"
import {FormControl, InputLabel, MenuItem, Select, SelectChangeEvent} from "@mui/material"
import Typography from "@mui/material/Typography"
import {FileUploader} from "react-drag-drop-files"
import {IsolateDataFile} from "../../../Services/Administration/models/projects/getProject/GetProjectOutputModel"

/**
 * Props for the IsolateDataStepCard component.
 *
 * @property isolateData the isolate data files of the project
 * @property selectedIsolateData the selected isolate data file
 * @property onFileSelecterChange the function to call when the file on the file selecter changes
 * @property onFileUploaderChange the function to call when the file on the file uploader changes
 * @property isolateDataKeys the isolate data keys of the project
 * @property selectedIsolateDataKey the selected isolate data key
 * @property onIsolateDataKeyChange the function to call when the isolate data key changes
 */
interface IsolateDataStepCardProps {
    isolateData: IsolateDataFile[]
    selectedIsolateData: string | null
    onFileSelecterChange: (event: SelectChangeEvent, child: ReactNode) => void
    onFileUploaderChange: (file: React.SetStateAction<File | null>) => void
    isolateDataKeys: string[]
    selectedIsolateDataKey: string | null
    onIsolateDataKeyChange: (event: SelectChangeEvent, child: ReactNode) => void
    triedSubmitting: boolean
}

/**
 * Card for the Isolate Data step of the Create Dataset page.
 */
export function IsolateDataStepCard(
    {
        isolateData,
        selectedIsolateData,
        onFileSelecterChange,
        onFileUploaderChange,
        isolateDataKeys,
        selectedIsolateDataKey,
        onIsolateDataKeyChange,
        triedSubmitting
    }: IsolateDataStepCardProps
) {
    return (
        <>
            <Typography variant="caption" align={"justify"} sx={{mb: 1, width: "100%"}}>
                Select the isolate data file from the project files or upload a new one.
            </Typography>
            <FormControl sx={{width: "100%", mb: 1}}>
                <InputLabel id="isolate-data">Isolate Data</InputLabel>
                <Select
                    labelId="isolate-data"
                    label="Isolate Data"
                    value={selectedIsolateData ?? ""}
                    onChange={onFileSelecterChange}
                    MenuProps={{PaperProps: {sx: {maxHeight: 150}}}}
                >
                    {isolateData.map((isolateDataFile) => (
                        <MenuItem key={isolateDataFile.isolateDataId}
                                  value={isolateDataFile.isolateDataId}>{isolateDataFile.name}</MenuItem>
                    ))}
                </Select>
            </FormControl>
            <Typography variant="body2" align={"center"} sx={{mb: 1, width: "100%"}}>
                Or
            </Typography>
            <FileUploader
                handleChange={onFileUploaderChange}
                name="file"
                required
            />

            <Typography variant="caption" align={"justify"} sx={{mb: 1, mt: 4, width: "100%"}}>
                Select the isolate data key.
            </Typography>
            <FormControl sx={{width: "100%", mb: 2}}>
                <InputLabel id="isolate-key">Key</InputLabel>
                <Select
                    labelId="isolate-key"
                    label="Key"
                    value={selectedIsolateDataKey ?? ""}
                    onChange={onIsolateDataKeyChange}
                    error={triedSubmitting && !!selectedIsolateData && !selectedIsolateDataKey}
                    MenuProps={{PaperProps: {sx: {maxHeight: 150}}}}
                >
                    {isolateDataKeys.map((isolateDataKey) => (
                        <MenuItem key={isolateDataKey} value={isolateDataKey}>{isolateDataKey}</MenuItem>
                    ))}
                </Select>
            </FormControl>

            <Typography display="inline" variant="caption" align={"left"} sx={{width: "100%", whiteSpace: "pre-wrap"}}>
                If you have any type of ancillary data about the isolates (Demographic information, Epidemiological
                information, Antibiotic Resistance, Gene presence absence,etc) you can load a tab separated file to
                further represent it on the algorithm. This file should be a tab separated file with the first row with
                column headers (field names). One of the fields should be the Sequence identifier. Choose this field in
                the Key drop down menu to link the ancillary data to the allelic profile data.
                If you don't have any available data or don't wish do load any leave these fields blank an press Finish.
            </Typography>
        </>
    )
}