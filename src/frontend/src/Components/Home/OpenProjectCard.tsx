import Paper from "@mui/material/Paper";
import Typography from "@mui/material/Typography";
import {Button} from "@mui/material";
import OpenProjectIcon from "@mui/icons-material/FolderOpen";
import * as React from "react";
import {useNavigate} from "react-router-dom";
import {Uris} from "../../Utils/navigation/Uris";

/**
 * Card for the open project feature in the home page.
 */
export function OpenProjectCard() {
    const navigate = useNavigate();

    return (
        <Paper sx={{
            p: 4,
            display: "flex",
            flexDirection: "column",
            alignItems: "center",
            width: "32%",
            justifyContent: "space-between"
        }}>
            <Typography component="h1" variant="h5">
                <strong>Open Project</strong>
            </Typography>
            <Typography component="h1" variant="body1" align={"justify"}>
                Open a project that you have created before.<br/>
                The project can be in the cloud or you can upload it from your computer.
            </Typography>
            <Button variant="contained"
                    startIcon={<OpenProjectIcon/>}
                    onClick={() => navigate(Uris.OPEN_PROJECT)}
                    sx={{
                        marginTop: 4,
                        width: "75%"
                    }}
            >
                Open Project
            </Button>
        </Paper>
    );
}