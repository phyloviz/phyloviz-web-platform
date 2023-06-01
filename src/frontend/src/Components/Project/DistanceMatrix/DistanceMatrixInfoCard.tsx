import {useNavigate, useParams} from "react-router-dom"
import * as React from "react"
import {Box, Button, Collapse, IconButtonProps} from "@mui/material"
import ExpandMoreIcon from "@mui/icons-material/ExpandMore"
import {styled} from "@mui/material/styles"
import IconButton from "@mui/material/IconButton"
import {DistanceMatrix} from "../../../Services/Administration/models/projects/getProject/GetProjectOutputModel";
import {DistanceMatrixInfo} from "./DistanceMatrixInfo";

/**
 * Props for the DistanceMatrixInfoCard component.
 *
 * @property distanceMatrix - the information about the distance matrix
 */
interface DistanceMatrixInfoCardProps {
    distanceMatrix: DistanceMatrix
}

/**
 * Card with information about the distance matrix.
 */
export function DistanceMatrixInfoCard({distanceMatrix}: DistanceMatrixInfoCardProps) {
    const {projectId} = useParams<{ projectId: string }>()
    const navigate = useNavigate()
    const [distanceMatrixInfoExpanded, setDistanceMatrixInfoExpanded] = React.useState(false)
    const handleDistanceMatrixInfoExpandClick = () => setDistanceMatrixInfoExpanded(!distanceMatrixInfoExpanded)

    return (
        <Box sx={{
            position: "absolute",
            top: 0,
            left: 0,
            zIndex: 1,
            backgroundColor: "white",
            borderRadius: 1,
            p: 1,
            m: 1,
            border: 1,
            borderColor: 'divider',
        }}>
            <Button
                onClick={handleDistanceMatrixInfoExpandClick}
                size={"small"}
                startIcon={
                    <ExpandMore color={"inherit"} expand={distanceMatrixInfoExpanded}
                                onClick={handleDistanceMatrixInfoExpandClick}
                                size={"small"}>
                        <ExpandMoreIcon color={"inherit"}/>
                    </ExpandMore>
                }>
                Distance Matrix Info
            </Button>

            <Collapse in={distanceMatrixInfoExpanded} timeout="auto" unmountOnExit>
                <DistanceMatrixInfo distanceMatrix={distanceMatrix}/>
            </Collapse>
        </Box>
    )
}


interface ExpandMoreProps extends IconButtonProps {
    expand: boolean
}

const ExpandMore = styled((props: ExpandMoreProps) => {
    const {expand, ...other} = props
    return <IconButton {...other} component={"span"}/>
})(({theme, expand}) => ({
    transform: !expand ? 'rotate(0deg)' : 'rotate(180deg)',
    marginLeft: 'auto',
    transition: theme.transitions.create('transform', {
        duration: theme.transitions.duration.shortest,
    }),
}))