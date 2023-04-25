import {useNavigate, useParams} from "react-router-dom"
import * as React from "react"
import {Box, Button, Collapse, IconButtonProps, ListItem} from "@mui/material"
import ExpandMoreIcon from "@mui/icons-material/ExpandMore"
import Typography from "@mui/material/Typography"
import List from "@mui/material/List"
import ListItemButton from "@mui/material/ListItemButton"
import ListItemIcon from "@mui/material/ListItemIcon"
import {Description, FilePresent, Forest} from "@mui/icons-material"
import ListItemText from "@mui/material/ListItemText"
import {WebUiUris} from "../../../Pages/WebUiUris"
import {styled} from "@mui/material/styles"
import IconButton from "@mui/material/IconButton"
import {TreeView} from "../../../Services/Administration/models/getProject/GetProjectOutputModel"

/**
 * Props for the TreeViewInfoCard component.
 *
 * @property treeView the information about the tree view
 */
interface TreeViewInfoCardProps {
    treeView: TreeView
}

/**
 * Card with information about the tree view.
 */
export function TreeViewInfoCard({treeView}: TreeViewInfoCardProps) {
    const {projectId} = useParams<{ projectId: string }>()
    const navigate = useNavigate()
    const [expanded, setExpanded] = React.useState(false)
    const handleExpandClick = () => setExpanded(!expanded)

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
                onClick={handleExpandClick}
                size={"small"}
                startIcon={
                    <ExpandMore color={"inherit"} expand={expanded} onClick={handleExpandClick} size={"small"}>
                        <ExpandMoreIcon color={"inherit"}/>
                    </ExpandMore>
                }>
                Tree View Info
            </Button>

            <Collapse in={expanded} timeout="auto" unmountOnExit>
                <Typography variant="body2" textAlign={"left"} gutterBottom>
                    <strong>Tree View:</strong> {treeView.name}
                </Typography>
                <Typography variant="body2" textAlign={"left"} gutterBottom>
                    <strong>Layout:</strong> {treeView.layout}
                </Typography>
                <Typography variant="body2" textAlign={"left"}>
                    <strong>Source:</strong>
                </Typography>
                <List dense>
                    <ListItem disablePadding>
                        <ListItemButton>
                            <ListItemIcon><Forest/></ListItemIcon>
                            <ListItemText primary="Tree"/>
                        </ListItemButton>
                    </ListItem>
                    {
                        treeView.source.typingDataId &&
                        <ListItem disablePadding>
                            <ListItemButton
                                onClick={() => navigate(WebUiUris.typingData(projectId!, treeView.source.typingDataId!))}
                            >
                                <ListItemIcon><Description/></ListItemIcon>
                                <ListItemText primary="Typing Data"/>
                            </ListItemButton>
                        </ListItem>
                    }
                    {
                        treeView.source.isolateDataId &&
                        <ListItem disablePadding>
                            <ListItemButton
                                onClick={() => navigate(WebUiUris.isolateData(projectId!, treeView.source.isolateDataId!))}
                            >
                                <ListItemIcon><FilePresent/></ListItemIcon>
                                <ListItemText primary="Isolate Data"/>
                            </ListItemButton>
                        </ListItem>
                    }
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
    return <IconButton {...other} />
})(({theme, expand}) => ({
    transform: !expand ? 'rotate(0deg)' : 'rotate(180deg)',
    marginLeft: 'auto',
    transition: theme.transitions.create('transform', {
        duration: theme.transitions.duration.shortest,
    }),
}))