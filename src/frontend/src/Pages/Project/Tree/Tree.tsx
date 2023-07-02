import * as React from "react"
import Paper from "@mui/material/Paper"
import Typography from "@mui/material/Typography"
import {Container} from "@mui/material"
import {useTree} from "./useTree";
import {TreeInfoCard} from "../../../Components/Project/Tree/TreeInfoCard";
import Box from "@mui/material/Box";

/**
 * Tree page.
 */
export default function Tree() {
    const {
        tree,
        treeData
    } = useTree()

    return (
        <Box sx={{position: "relative", width: "90%"}}>
            <TreeInfoCard tree={tree}/>
            <Container>
                <Paper sx={{
                    p: 4,
                    display: "flex",
                    flexDirection: "column",
                    mt: 4,
                    alignItems: "center"
                }}>
                    <Typography component="h1" variant="h4">
                        Tree
                    </Typography>
                    {treeData && <Typography component="p" variant="body1" sx={{
                        backgroundColor: "#F0F0F0",
                        borderRadius: "5px",
                        whiteSpace: "pre-line",
                        textAlign: 'left',
                        m: 1
                    }}>
                        {treeData}
                    </Typography>
                    }
                </Paper>
            </Container>
        </Box>
    )
}
