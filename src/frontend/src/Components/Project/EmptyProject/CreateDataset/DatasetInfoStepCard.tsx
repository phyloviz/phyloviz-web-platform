import {FormControl, InputLabel, MenuItem, Select, SelectChangeEvent, TextField} from "@mui/material";
import * as React from "react";
import {ReactNode} from "react";
import Typography from "@mui/material/Typography";
import {DatasetType} from "../../../../Domain/DatasetType";

/**
 * Props for the DatasetInfoStepCard component.
 *
 * @param datasetType the type of the dataset
 * @param onChange the function to be called when the dataset type is changed
 */
interface DatasetInfoStepCard {
    datasetType: DatasetType,
    onChange: (event: SelectChangeEvent, child: ReactNode) => void
}

/**
 * Card for the Dataset Info step of the Create Dataset page.
 */
export function DatasetInfoStepCard({datasetType, onChange}: DatasetInfoStepCard) {
    return <>
        <TextField label="Dataset Name" variant="outlined" required sx={{
            width: "100%", mb: 1
        }}/>
        <Typography variant="caption" align={"justify"} sx={{mb: 4, width: "100%"}}>
            Choose a Dataset name to identify your analysis and choose the Dataset type depending on the
            typing data to be used.
        </Typography>

        <FormControl sx={{width: "100%", mb: 1}}>
            <InputLabel id="dataset-type">Dataset Type</InputLabel>
            <Select
                labelId="dataset-type"
                value={datasetType.valueOf()}
                label="Dataset Type"
                onChange={onChange}
            >
                <MenuItem value={DatasetType.MLST}>{DatasetType.MLST.valueOf()}</MenuItem>
                <MenuItem value={DatasetType.MLVA}>{DatasetType.MLVA.valueOf()}</MenuItem>
                <MenuItem value={DatasetType.FASTA}>{DatasetType.FASTA.valueOf()}</MenuItem>
                <MenuItem value={DatasetType.ALIGNED}>{DatasetType.ALIGNED.valueOf()}</MenuItem>
                <MenuItem value={DatasetType.SNP}>{DatasetType.SNP.valueOf()}</MenuItem>
                <MenuItem value={DatasetType.SNP_WITHOUT_EXPLICIT_ID}>
                    {DatasetType.SNP_WITHOUT_EXPLICIT_ID.valueOf()}
                </MenuItem>
            </Select>
        </FormControl>
        <Typography variant="caption" align={"justify"} sx={{mb: 4, width: "100%"}}>
            {datasetDescriptions[datasetType]}
        </Typography>
    </>;
}

/**
 * Descriptions for each dataset type.
 */
const datasetDescriptions = {
    [DatasetType.MLST]: "The dataset type MLST is designed to analyze MLST data using its common allelic profiles " +
    "formats.",

    [DatasetType.MLVA]: "The dataset type MLVA is designed to analyze MLVA data using its common allelic profiles " +
    "formats and adds extra distance metrics to the analyses options.",

    [DatasetType.FASTA]: "The dataset type Aligned Sequences (FASTA) is designed to analyze sequence data " +
    "available in FASTA format. The sequences need to be all of the same size. " +
    "Sequences of different sizes will be excluded.",

    [DatasetType.ALIGNED]: "The dataset type Aligned Sequences (without explicit ID) is designed to analyze " +
    "sequence data without having a predefined ID selected. The sequences need to be all of the same size. " +
    "Sequences of different sizes will be excluded.",

    [DatasetType.SNP]: "The dataset type Single Nucleotide Polymorphism (SNP) is designed to analyze SNP data " +
    "based on the presence and absence of a SNP. The sequences need to be all of the same size. " +
    "Sequences of different sizes will be excluded.",

    [DatasetType.SNP_WITHOUT_EXPLICIT_ID]: "The dataset type Single Nucleotide Polymorphism (SNP) " +
    "(without explicit ID) is designed to analyze SNP data based on the presence and absence of a SNP, and no " +
    "explicit ID was attributed to the entire SNP profile. The sequences need to be all of the same size. " +
    "Sequences of different sizes will be excluded."
};
