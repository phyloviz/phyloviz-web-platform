import {FormControl, InputLabel, Select} from "@mui/material";
import * as React from "react";
import {DatasetType} from "../../../Domain/DatasetType";
import Typography from "@mui/material/Typography";
import {FileUploader} from "react-drag-drop-files";

/**
 * Props for the TypingDataStepCard component.
 *
 * @param datasetType the type of the dataset
 * @param onChange the function to be called when the typing data file is changed
 */
interface TypingDataStepCardProps {
    datasetType: DatasetType;
    onChange: (event: React.ChangeEvent<{ value: unknown }>) => void;
}

/**
 * Card for the Typing Data step of the Create Dataset page.
 */
export function TypingDataStepCard({datasetType, onChange}: TypingDataStepCardProps) {
    return (
        <>
            <Typography variant="caption" align={"justify"} sx={{mb: 1, width: "100%"}}>
                Select the typing data file from the project files or upload a new one.
            </Typography>
            <FormControl sx={{width: "100%", mb: 1}}>
                <InputLabel id="typing-key">Typing Data</InputLabel>
                <Select
                    labelId="typing-key"
                    //value={}
                    label="Typing Data"
                >
                </Select>
            </FormControl>
            <Typography variant="body2" align={"center"} sx={{mb: 1, width: "100%"}}>
                Or
            </Typography>
            <FileUploader
                handleChange={onChange}
                name="file"
                required
            />

            <Typography variant="caption" align={"justify"} sx={{mt: 2, mb: 2, width: "100%"}}>
                The {datasetType.valueOf()} type is chosen.
            </Typography>

            <Typography display="inline" variant="caption" align={"left"} sx={{width: "100%", whiteSpace: "pre-wrap"}}>
                {typingDataExamples[datasetType]}
            </Typography>
        </>
    );
}

/**
 * Examples of the typing data for each dataset type.
 */
const typingDataExamples = {
    [DatasetType.MLST]: "The data format for the typing data should be as follows. Load a tab separated " +
    "file with the first row containing column headers (field names). The first column should be a Sequence " +
    "Identifier, such as the ST for MLST data. Other columns should be the different loci used in the typing " +
    "scheme. Example of a MLST dataset for Streptococcus dysgalactiae subspecies equisimilis:\n" +
    "  ST      gki     gtr     murI    mutS    recP    xpt     yqiZ\n" +
    "  1       10      6       6       6       12      13      8\n" +
    "  2       5       4       4       1       2       15      2\n" +
    "  3       5       3       4       1       6       2       1\n" +
    "  4       2       2       4       1       8       7       2\n" +
    "  5       2       2       4       1       12      12      7\n" +
    "  6       1       3       1       1       1       1       4\n" +
    "  7       1       1       1       1       1       1       3\n" +
    "  8       1       1       1       1       1       1       4\n" +
    "This files should not contain missing data. If any allele missing data is found the entire line is discarded.",

    [DatasetType.MLVA]: "The data format for the typing data should be as follows. Load a tab separated file " +
    "with the first row containing column headers (field names). The first column should be a Sequence " +
    "Identifier, an unique ID for the complete allelic profile. Other columns should be the different loci " +
    "used in the typing scheme. For each loci the number of repeats found should be given.  The following example " +
    "represents an MLVA scheme with 7 loci named l1 to l7:\n" +
    "  ST      l1     l2      l3      l4      l5      l6     l7\n" +
    "  1       8      6       6       6       12      13     4\n" +
    "  2       5      4.5     4       2.5     2       15     2\n" +
    "  3       5.5    3       4       1.5     6       8      4                \n" +
    "  (...)\n" +
    "This files should not contain missing data. If any allele missing data is found the entire line is discarded.",

    [DatasetType.FASTA]: "The data format for the typing data should be as follows. Load a text file containing " +
    "only the sequences. All the sequences should have the same length. A sequential ID identified will be " +
    "attributed to each sequence based on the order of the sequences in the file. The Fasta header will be used " +
    "to identify the isolate when you click on it. This format can also be used to analyze SNP data.  An example " +
    "of the file content is the following:\n" +
    "                                              \n" +
    "    >aaa 1\n" +
    "    ATATTTGTATATATGGCCCGAT\n" +
    "    >aaa 2\n" +
    "    ATATTTGATTATATGGCCCGAT\n" +
    "    >aaa 3\n" +
    "    ATATTTATTTATATGGCCCGAT\n" +
    "    >aaa 4\n" +
    "    ATATTAGTTTATATGGCCCGAT\n" +
    "    >aaa 5\n",

    [DatasetType.ALIGNED_SEQUENCES]: "The dataset type Aligned Sequences (without explicit ID) is designed to analyze " +
    "sequence data without having a predefined ID selected. The sequences need to be all of the same size. " +
    "Sequences of different sizes will be excluded.",

    [DatasetType.SNP]: "The dataset type Single Nucleotide Polymorphism (SNP) is designed to analyze SNP data " +
    "based on the presence and absence of a SNP. The sequences need to be all of the same size. " +
    "Sequences of different sizes will be excluded.",

    [DatasetType.SNP_WITHOUT_EXPLICIT_ID]: "The dataset type Single Nucleotide Polymorphism (SNP) (without " +
    "explicit ID) is designed to analyze SNP data based on the presence and absence of a SNP, and no explicit ID " +
    "was attributed to the entire SNP profile. The sequences need to be all of the same size. Sequences of " +
    "different sizes will be excluded."
}