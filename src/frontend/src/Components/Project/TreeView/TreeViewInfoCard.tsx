import {useNavigate, useParams} from "react-router-dom"
import * as React from "react"
import {Box, Button, Collapse, IconButtonProps, ListItem} from "@mui/material"
import ExpandMoreIcon from "@mui/icons-material/ExpandMore"
import Typography from "@mui/material/Typography"
import List from "@mui/material/List"
import ListItemButton from "@mui/material/ListItemButton"
import ListItemIcon from "@mui/material/ListItemIcon"
import ListItemText from "@mui/material/ListItemText"
import {styled} from "@mui/material/styles"
import IconButton from "@mui/material/IconButton"
import {CascadingInfoTreeView} from "../../../Services/Administration/models/projects/getProject/GetProjectOutputModel"
import {TreeIcon} from "../../Shared/Icons";
import {TreeInfo} from "../Tree/TreeInfo";

/**
 * Props for the TreeViewInfoCard component.
 *
 * @property treeView the information about the tree view
 */
interface TreeViewInfoCardProps {
    treeView: CascadingInfoTreeView
}

/**
 * Card with information about the tree view.
 */
export function TreeViewInfoCard({treeView}: TreeViewInfoCardProps) {
    const projectId = (useParams<{ projectId: string }>().projectId)!
    const navigate = useNavigate()
    const [treeViewInfoExpanded, setTreeViewInfoExpanded] = React.useState(false)
    const handleTreeViewInfoExpandClick = () => setTreeViewInfoExpanded(!treeViewInfoExpanded)
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
                onClick={handleTreeViewInfoExpandClick}
                size={"small"}
                startIcon={
                    <ExpandMore color={"inherit"} expand={treeViewInfoExpanded} size={"small"}>
                        <ExpandMoreIcon color={"inherit"}/>
                    </ExpandMore>
                }>
                Tree View Info
            </Button>

            <Collapse in={treeViewInfoExpanded} timeout="auto" unmountOnExit>
                <Typography variant="body2" textAlign={"left"} gutterBottom>
                    <strong>Name:</strong> {treeView.name}
                </Typography>
                <Typography variant="body2" textAlign={"left"} gutterBottom>
                    <strong>Layout:</strong> {treeView.layout}
                </Typography>
                <Typography variant="body2" textAlign={"left"}>
                    <strong>Source:</strong>
                </Typography>
                <List dense>
                    <ListItem disablePadding sx={{
                        display: "flex",
                        flexDirection: "column",
                        width: "100%"
                    }}>
                        <ListItemButton onClick={() => handleTreeInfoExpandClick()} sx={{
                            width: "100%"
                        }}>
                            <ListItemIcon><TreeIcon/></ListItemIcon>
                            <ListItemText primary="Tree"/>
                        </ListItemButton>
                        <Collapse in={treeInfoExpanded} timeout="auto" sx={{pl: 2}} unmountOnExit>
                            <TreeInfo tree={treeView.source.tree}
                                      distanceMatrixInfoExpanded={distanceMatrixInfoExpanded}
                                      handleDistanceMatrixInfoExpandClick={handleDistanceMatrixInfoExpandClick}/>
                        </Collapse>
                    </ListItem>
                </List>
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
