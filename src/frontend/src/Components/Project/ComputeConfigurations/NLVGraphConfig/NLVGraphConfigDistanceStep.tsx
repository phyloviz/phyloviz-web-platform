import * as React from "react"
import Typography from "@mui/material/Typography"
import {Checkbox, FormControl, FormControlLabel, FormGroup, InputLabel, Select, TextField} from "@mui/material"
import Box from "@mui/material/Box"

/**
 * Card for the distance step in the NLVGraphConfig page.
 */
export function NLVGraphConfigDistanceStep() {
    return (
        <>
            <Typography variant="caption" align={"justify"} sx={{mb: 1, width: "100%"}}>
                This list contains only the distances compatible with the current dataset:
            </Typography>
            <FormControl sx={{width: "100%", mb: 1}}>
                <InputLabel id="distance">Distance</InputLabel>
                <Select
                    labelId="distance"
                    //value={}
                    label="Distance"
                >
                </Select>
            </FormControl>
            <Box sx={{
                display: "flex",
                flexDirection: "row",
                alignItems: "center",
                mb: 1, mt: 1
            }}>
                <TextField
                    label="Maximum nLV level"
                    type="number"
                    variant="outlined"
                    //onChange={}
                    value={1}
                    required sx={{width: "75%"}}
                />
                <FormGroup>
                    <FormControlLabel control={<Checkbox defaultChecked/>} label="InnerEdges"/>
                </FormGroup>
            </Box>

            <Typography display="inline" variant="caption" align={"left"} sx={{width: "100%", whiteSpace: "pre-wrap"}}>
                {
                    "This distance builds upon the rules defined in the article by Feil et al to select if a link should be drawn. " +
                    "In order to provide an unique minimum spanning tree(MST) like structure, this option can also expand goeBURST rules to the number of loci used in a typing schema.  " +
                    "Let n be the number of differences, which is always less than the number of loci. The implemented rules are (by order):\n" +
                    "1: #SLVs\n" +
                    "2: #DLVs\n" +
                    "3: #TLVs\n" +
                    "4 to n: #(4 to n)LVs\n" +
                    "n+1: Frequency of an ST\n" +
                    "n+2: STID\n\n" +
                    "The last rule was added in goeBURST to ensure that the represented tree is unique. This rule is that the lower ST id should be selected in case of tie at this level.\n" +
                    "Feil, E., Li, B., Aanensen, D., Hanage, W., & Spratt, B. (2004). eBURST: inferring patterns of evolutionary descent among clusters of related bacterial genotypes from multilocus sequence typing data. Journal of Bacteriology, 186(5), 1518-1530.  Francisco, A. P., Bugalho, M., Ramirez, M., & Carriço, J. A. (2009).\n" +
                    "Global optimal eBURST analysis of multilocus typing data using a graphic matroid approach. BMC Bioinformatics, 10, 152. doi:10.1186/1471-2105-10-152"
                }
            </Typography>
        </>
    )
}