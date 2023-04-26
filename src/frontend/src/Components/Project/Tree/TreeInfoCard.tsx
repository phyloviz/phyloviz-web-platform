import {useNavigate, useParams} from "react-router-dom"
import * as React from "react"
import {Box, Button, Collapse, IconButtonProps} from "@mui/material"
import ExpandMoreIcon from "@mui/icons-material/ExpandMore"
import {styled} from "@mui/material/styles"
import IconButton from "@mui/material/IconButton"
import {CascadingInfoTree} from "../../../Services/Administration/models/projects/getProject/GetProjectOutputModel"
import {TreeInfo} from "../Tree/TreeInfo";

/**
 * Props for the TreeInfoCard component.
 *
 * @property tree - the information about the tree
 */
interface TreeInfoCardProps {
    tree: CascadingInfoTree
}

/**
 * Card with information about the tree.
 */
export function TreeInfoCard({tree}: TreeInfoCardProps) {
    const {projectId} = useParams<{ projectId: string }>()
    const navigate = useNavigate()
    const [treeInfoExpanded, setTreeInfoExpanded] = React.useState(false)
    const handleTreeInfoExpandClick = () => setTreeInfoExpanded(!treeInfoExpanded)
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
                onClick={handleTreeInfoExpandClick}
                size={"small"}
                startIcon={
                    <ExpandMore color={"inherit"} expand={treeInfoExpanded} onClick={handleTreeInfoExpandClick}
                                size={"small"}>
                        <ExpandMoreIcon color={"inherit"}/>
                    </ExpandMore>
                }>
                Tree Info
            </Button>

            <Collapse in={treeInfoExpanded} timeout="auto" unmountOnExit>
                <TreeInfo tree={tree}
                          distanceMatrixInfoExpanded={distanceMatrixInfoExpanded}
                          handleDistanceMatrixInfoExpandClick={handleDistanceMatrixInfoExpandClick}/>
            </Collapse>
        </Box>
    )
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