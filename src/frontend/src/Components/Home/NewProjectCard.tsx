import Paper from "@mui/material/Paper";
import Typography from "@mui/material/Typography";
import {Button} from "@mui/material";
import NewProjectIcon from "@mui/icons-material/CreateNewFolder";
import * as React from "react";
import {useNavigate} from "react-router-dom";
import {Uris} from "../../Utils/navigation/Uris";

export function NewProjectCard() {
    const navigate = useNavigate();

    return <Paper
        sx={{
            p: 4,
            display: "flex",
            flexDirection: "column",
            alignItems: "center",
            width: "32%",
            justifyContent: "space-between"
        }}
    >
        <Typography component="h1" variant="h5">
            <strong>New Project</strong>
        </Typography>
        <Typography component="h1" variant="body1" align={"justify"}>
            Create a new project and save your data in the cloud.<br/>
            You will need to create an account to use this feature.
        </Typography>
        <Button variant="contained"
                startIcon={<NewProjectIcon/>}
                onClick={() => navigate(Uris.NEW_PROJECT)}
                sx={{
                    marginTop: 4,
                    width: "75%"
                }}
        >
            New Project
        </Button>
    </Paper>;
}