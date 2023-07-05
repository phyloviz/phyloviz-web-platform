import Paper from "@mui/material/Paper"
import Typography from "@mui/material/Typography"
import {Button, Menu, MenuItem} from "@mui/material"
import * as React from "react"
import {useNavigate, useParams} from "react-router-dom"
import {TreesIcon} from "../../Shared/Icons";
import {computeTreeOptions} from "../ProjectStructure/Datasets/Dataset/Trees/useTreesTreeItem"

/**
 * Card for the compute trees feature.
 */
export function ComputeTreesCard() {
    const navigate = useNavigate()
    const {projectId, datasetId} = useParams<{ projectId: string, datasetId: string }>()

    const [anchorEl, setAnchorEl] = React.useState<null | HTMLElement>(null)
    const open = Boolean(anchorEl)
    const handleClose = () => setAnchorEl(null)

    return <Paper sx={{
        p: 4,
        display: "flex",
        flexDirection: "column",
        alignItems: "center",
        width: "30%",
        justifyContent: "space-between",
        boxShadow: 12
    }}>
        <Typography component="h1" variant="h5">
            <strong>Compute Trees</strong>
        </Typography>
        <Typography component="h1" variant="body1" sx={{mt: 2}}>
            Compute a tree with your data.
            You can analyze the result with the visualization feature.
        </Typography>
        <Button
            variant="contained"
            startIcon={<TreesIcon/>}
            onClick={(event) => setAnchorEl(event.currentTarget)}
            sx={{mt: 4, width: "100%"}}
        >
            Compute Trees
        </Button>
        <Menu
            anchorEl={anchorEl}
            open={open}
            onClose={handleClose}
        >
            {computeTreeOptions(projectId!, datasetId!).map((option) => (
                <MenuItem
                    key={option.label}
                    onClick={() => {
                        navigate(option.url)
                        handleClose()
                    }}
                >
                    {option.label}
                </MenuItem>
            ))}
        </Menu>
    </Paper>
}