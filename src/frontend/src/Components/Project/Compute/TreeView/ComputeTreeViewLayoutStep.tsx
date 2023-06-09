import Typography from "@mui/material/Typography"
import {FormControl, InputLabel, MenuItem, Select, SelectChangeEvent} from "@mui/material"
import * as React from "react"
import {ReactNode} from "react"
import {ComputeTreeViewLayout} from "../../../../Pages/Project/Compute/TreeView/useComputeTreeView";


/**
 * Props for the ComputeTreeViewLayoutStep component.
 *
 * @property selectedLayout the selected layout
 * @property onLayoutChange the layout change handler
 */
interface ComputeTreeViewLayoutStepProps {
    selectedLayout: string | null
    onLayoutChange: (event: SelectChangeEvent, child: ReactNode) => void
    triedSubmitting: boolean
}

/**
 * Card for the layout step in the ComputeTreeView page.
 */
export function ComputeTreeViewLayoutStep(
    {
        selectedLayout,
        onLayoutChange,
        triedSubmitting
    }: ComputeTreeViewLayoutStepProps
) {
    return (
        <>
            <Typography variant="caption" align={"justify"} sx={{mb: 1, width: "100%"}}>
                This list contains only the layouts compatible with the current dataset:
            </Typography>
            <FormControl sx={{width: "100%", mb: 1}}>
                <InputLabel id="layout">Layout</InputLabel>
                <Select
                    labelId="layout"
                    label="Layout"
                    value={selectedLayout ?? ""}
                    error={selectedLayout === null && triedSubmitting}
                    onChange={onLayoutChange}
                    MenuProps={{PaperProps: {sx: {maxHeight: 150}}}}
                >
                    {
                        Object.values(ComputeTreeViewLayout).map((layout) => (
                            <MenuItem key={layout} value={layout}>{layout}</MenuItem>
                        ))
                    }
                </Select>
            </FormControl>

            <Typography display="inline" variant="caption" align={"left"} sx={{width: "100%", whiteSpace: "pre-wrap"}}>
                {layoutDescriptions[selectedLayout as ComputeTreeViewLayout]}
            </Typography>
        </>
    )
}

const layoutDescriptions = {
    [ComputeTreeViewLayout.FORCE_DIRECTED]: "Force-directed layout is a physical simulation of a system of particles, which are connected by springs. The algorithm is iterative and the simulation is performed in discrete steps. At each step, the forces acting on each particle are computed and the positions of the particles are updated. The algorithm stops when the system reaches a stable state, i.e. when the forces are balanced and the particles are not moving anymore. The algorithm is based on the Fruchterman-Reingold algorithm.",

    [ComputeTreeViewLayout.RADIAL]: "Radial layout is a tree layout where the nodes are placed on concentric circles. The root node is placed at the center of the first circle. The children of the root node are placed on the second circle, and so on.",

    [ComputeTreeViewLayout.RECTANGULAR]: "Rectangular layout is a tree layout where the nodes are placed on a grid. The root node is placed at the center of the grid. The children of the root node are placed on the first row of the grid, and so on.",
}