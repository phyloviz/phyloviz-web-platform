import * as React from "react"
import {Box, IconButtonProps} from "@mui/material"
import Typography from "@mui/material/Typography"
import {styled} from "@mui/material/styles"
import IconButton from "@mui/material/IconButton"
import {
    DistanceMatrix,
    FunctionDistanceMatrixSource
} from "../../../Services/Administration/models/projects/getProject/GetProjectOutputModel"

/**
 * Props for the TreeInfo component.
 *
 * @property treeView the information about the tree view
 */
interface DistanceMatrixInfoProps {
    distanceMatrix: DistanceMatrix
}

/**
 * Information about the tree.
 */
export function DistanceMatrixInfo({distanceMatrix}: DistanceMatrixInfoProps) {
    return <Box>
        <Typography variant="body2" textAlign={"left"} gutterBottom>
            <strong>Name:</strong> {distanceMatrix.name}
        </Typography>
        {
            distanceMatrix.sourceType === "function" ?
                <div>
                    <Typography variant="body2" textAlign={"left"} gutterBottom>
                        <strong>Function:</strong> {
                        (distanceMatrix.source as FunctionDistanceMatrixSource).function
                    }
                    </Typography>
                </div>
                :
                "Unknown source type"
        }
    </Box>
}


interface ExpandMoreProps extends IconButtonProps {
    expand: boolean
}

const ExpandMore = styled((props: ExpandMoreProps) => {
    const {expand, ...other} = props
    return <IconButton {...other} />
})(({theme, expand}) => ({
    transform: !expand ? 'rotate(0deg)' : 'rotate(180deg)',
    marginLeft: 'auto',
    transition: theme.transitions.create('transform', {
        duration: theme.transitions.duration.shortest,
    }),
}))