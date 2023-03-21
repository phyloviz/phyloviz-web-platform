import Paper from "@mui/material/Paper";
import Typography from "@mui/material/Typography";
import {Button} from "@mui/material";
import UploadIcon from "@mui/icons-material/Upload";
import * as React from "react";
import {useNavigate} from "react-router-dom";
import {Uris} from "../../Utils/navigation/Uris";

/**
 * Card for the load dataset feature in the home page.
 */
export function LoadDatasetCard() {
    const navigate = useNavigate();

    return <Paper sx={{
        p: 4,
        display: "flex",
        flexDirection: "column",
        alignItems: "center",
        width: "32%",
        justifyContent: "space-between"
    }}>
        <Typography component="h1" variant="h5">
            <strong>Load Dataset</strong>
        </Typography>
        <Typography component="h1" variant="body1" align={"justify"}>
            Load your dataset and start your analysis in a few seconds.<br/>
            Do not require authentication, but you will lose your data when you close the
            browser.
        </Typography>
        <Button variant="contained"
                startIcon={<UploadIcon/>}
                onClick={() => navigate(Uris.LOAD_DATASET)}
                sx={{
                    marginTop: 4,
                    width: "75%"
                }}
        >
            Load Dataset
        </Button>
    </Paper>;
}