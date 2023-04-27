import Typography from "@mui/material/Typography"
import {FormControl, InputLabel, MenuItem, Select, SelectChangeEvent} from "@mui/material"
import * as React from "react"
import {ReactNode} from "react"
import {Tree} from "../../../../Services/Administration/models/projects/getProject/GetProjectOutputModel";


/**
 * Props for the ComputeTreeViewTreeStep component.
 *
 * @property trees the trees of the dataset
 * @property selectedTree the selected tree
 * @property onTreeChange the tree change handler
 */
interface ComputeTreeViewTreeStepProps {
    trees: Tree[]
    selectedTree: string | null
    onTreeChange: (event: SelectChangeEvent, child: ReactNode) => void
}


/**
 * Card for the tree step in the ComputeTreeView page.
 */
export function ComputeTreeViewTreeStep(
    {
        trees,
        selectedTree,
        onTreeChange
    }: ComputeTreeViewTreeStepProps
) {
    return (
        <>
            <Typography variant="caption" align={"justify"} sx={{mb: 1, width: "100%"}}>
                This list contains only the trees of the current dataset:
            </Typography>
            <FormControl sx={{width: "100%", mb: 1}}>
                <InputLabel id="tree">Tree</InputLabel>
                <Select
                    labelId="tree"
                    label="Tree"
                    value={selectedTree ?? ""}
                    onChange={onTreeChange}
                    MenuProps={{PaperProps: {sx: {maxHeight: 150}}}}
                >
                    {trees.map((tree) => (
                        <MenuItem key={tree.treeId}
                                  value={tree.treeId}>{tree.name}</MenuItem>
                    ))}
                </Select>
            </FormControl>

            <Typography display="inline" variant="caption" align={"left"} sx={{width: "100%", whiteSpace: "pre-wrap"}}>
                Select the tree to visualize.
            </Typography>
        </>
    )
}