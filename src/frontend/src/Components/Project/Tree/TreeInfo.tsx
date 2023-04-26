import * as React from "react"
import {Box, Collapse, IconButtonProps} from "@mui/material"
import Typography from "@mui/material/Typography"
import List from "@mui/material/List"
import ListItemButton from "@mui/material/ListItemButton"
import ListItemIcon from "@mui/material/ListItemIcon"
import ListItemText from "@mui/material/ListItemText"
import {styled} from "@mui/material/styles"
import IconButton from "@mui/material/IconButton"
import {
    CascadingInfoAlgorithmDistanceMatrixTreeSource,
    CascadingInfoAlgorithmTypingDataTreeSource, CascadingInfoFileTreeSource,
    CascadingInfoTree
} from "../../../Services/Administration/models/projects/getProject/GetProjectOutputModel"
import {DistanceMatrixIcon, TypingDataIcon} from "../../Shared/Icons";
import {DistanceMatrixInfo} from "../DistanceMatrix/DistanceMatrixInfo";

/**
 * Props for the TreeInfo component.
 *
 * @property treeView the information about the tree view
 */
interface TreeInfoProps {
    tree: CascadingInfoTree,
    distanceMatrixInfoExpanded: boolean,
    handleDistanceMatrixInfoExpandClick: () => void
}

/**
 * Information about the tree.
 */
export function TreeInfo({tree, distanceMatrixInfoExpanded, handleDistanceMatrixInfoExpandClick}: TreeInfoProps) {
    return <Box>
        <Typography variant="body2" textAlign={"left"} gutterBottom>
            <strong>Name:</strong> {tree.name}
        </Typography>
        {
            tree.sourceType === "algorithmDistanceMatrix" ?
                <div>
                    <Typography variant="body2" textAlign={"left"} gutterBottom>
                        <strong>Algorithm:</strong> {
                        (tree.source as CascadingInfoAlgorithmDistanceMatrixTreeSource).algorithm
                    }
                    </Typography>
                    <Typography variant="body2" textAlign={"left"} sx={{mb: -1}}>
                        <strong>Parameters:</strong> {
                        (tree.source as CascadingInfoAlgorithmDistanceMatrixTreeSource).parameters
                    }
                    </Typography>
                    <List dense>
                        <ListItemButton onClick={() => handleDistanceMatrixInfoExpandClick()}>
                            <ListItemIcon><DistanceMatrixIcon/></ListItemIcon>
                            <ListItemText primary="Distance Matrix"/>
                        </ListItemButton>
                        <Collapse in={distanceMatrixInfoExpanded} timeout="auto" sx={{pl: 2}} unmountOnExit>
                            <DistanceMatrixInfo distanceMatrix={(tree.source as CascadingInfoAlgorithmDistanceMatrixTreeSource).distanceMatrix}/>
                        </Collapse>
                    </List>
                </div>
                :
                tree.sourceType === "algorithmTypingData" ?
                    <div>
                        <Typography variant="body2" textAlign={"left"} gutterBottom>
                            <strong>Algorithm:</strong> {
                            (tree.source as CascadingInfoAlgorithmTypingDataTreeSource).algorithm
                        }
                        </Typography>
                        <Typography variant="body2" textAlign={"left"} sx={{mb: -1}}>
                            <strong>Parameters:</strong> {
                            (tree.source as CascadingInfoAlgorithmTypingDataTreeSource).parameters
                        }
                        </Typography>
                        <List dense>
                            <ListItemButton>
                                <ListItemIcon><TypingDataIcon/></ListItemIcon>
                                <ListItemText primary="Typing Data"/>
                            </ListItemButton>
                        </List>
                    </div>
                    :
                    <div>
                        <Typography variant="body2" textAlign={"left"} gutterBottom>
                            <strong>File Name:</strong> {
                            (tree.source as CascadingInfoFileTreeSource).fileName
                        }
                        </Typography>
                        <Typography variant="body2" textAlign={"left"} sx={{mb: -1}}>
                            <strong>File Type:</strong> {
                            (tree.source as CascadingInfoFileTreeSource).fileType
                        }
                        </Typography>
                    </div>
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