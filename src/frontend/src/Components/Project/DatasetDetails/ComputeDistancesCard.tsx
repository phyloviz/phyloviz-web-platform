import Paper from "@mui/material/Paper"
import Typography from "@mui/material/Typography"
import {Button, Menu, MenuItem} from "@mui/material"
import * as React from "react"
import {useNavigate, useParams} from "react-router-dom"
import {WebUiUris} from "../../../Pages/WebUiUris"
import {TableView} from "@mui/icons-material"

/**
 * Card for the compute distances feature.
 */
export function ComputeDistancesCard() {
    const navigate = useNavigate()
    const {projectId, datasetId} = useParams<{ projectId: string, datasetId: string }>()

    const [anchorEl, setAnchorEl] = React.useState<null | HTMLElement>(null)
    const open = Boolean(anchorEl)
    const handleClose = () => setAnchorEl(null)

    const computeDistanceMatrixOptions = [
        {
            label: "Hamming Distance",
            url: WebUiUris.computeHammingDistance(projectId!, datasetId!)
        }
    ]

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
            <strong>Compute Distances</strong>
        </Typography>
        <Typography component="h1" variant="body1" sx={{mt: 2}}>
            Compute a distance matrix with your data.
            You can analyze the result with a heatmap or you can use it for computing a tree.
        </Typography>
        <Button
            variant="contained"
            startIcon={<TableView/>}
            onClick={(event) => setAnchorEl(event.currentTarget)}
            sx={{mt: 4, width: "100%"}}
        >
            Compute Distances
        </Button>
        <Menu
            anchorEl={anchorEl}
            open={open}
            onClose={handleClose}
        >
            {computeDistanceMatrixOptions.map((option) => (
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