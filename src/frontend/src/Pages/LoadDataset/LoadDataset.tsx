import * as React from "react"
import {useState} from "react"
import Paper from "@mui/material/Paper";
import Typography from "@mui/material/Typography";
import {FormControl, InputLabel, MenuItem, Select, TextField} from "@mui/material";
import Box from "@mui/material/Box";

enum DatasetType {
    MLST = "Multi-Locus Sequence Typing (MLST)",
    MLVA = "Multi-Locus Variable Number Tandem Repeat Analysis (MLVA)",
    FASTA = "Aligned Sequences (FASTA)",
    SNP = "Single Nucleotide Polymorphism (SNP)",
}

/**
 * LoadDataset page.
 */
export default function LoadDataset() {
    const [datasetType, setDatasetType] = useState(DatasetType.MLST);

    return (
        <Paper sx={{
            p: 4,
            display: "flex",
            flexDirection: "column",
            marginTop: 4,
            alignItems: "center"
        }}>
            <Typography component="h1" variant="h4">
                Load Dataset
            </Typography>
            <Box sx={{
                width: "100%",
                display: "flex",
                flexDirection: "column",
                justifyContent: "left",
            }}>
                <TextField label="Dataset Name" variant="standard" required sx={{
                    mb: 4,
                    width: '50%'
                }}/>

                <FormControl sx={{width: '50%'}}>
                    <InputLabel id="dataset-type">Dataset Type</InputLabel>
                    <Select
                        labelId="dataset-type"
                        value={datasetType.valueOf()}
                        label="Dataset Type"
                        onChange={(event) => {
                            setDatasetType(event.target.value as DatasetType);
                        }}
                    >
                        <MenuItem value={DatasetType.MLST}>{DatasetType.MLST.valueOf()}</MenuItem>
                        <MenuItem value={DatasetType.MLVA}>{DatasetType.MLVA.valueOf()}</MenuItem>
                        <MenuItem value={DatasetType.FASTA}>{DatasetType.FASTA.valueOf()}</MenuItem>
                        <MenuItem value={DatasetType.SNP}>{DatasetType.SNP.valueOf()}</MenuItem>
                    </Select>
                </FormControl>
            </Box>
        </Paper>
    );
}
