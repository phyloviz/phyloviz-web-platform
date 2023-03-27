import Paper from "@mui/material/Paper";
import Typography from "@mui/material/Typography";
import {Button} from "@mui/material";
import UploadIcon from "@mui/icons-material/Upload";
import * as React from "react";
import {useNavigate} from "react-router-dom";
import {WebUiUris} from "../../../../Utils/navigation/WebUiUris";

/**
 * Card for the upload files feature.
 */
export function UploadFilesCard() {
    const navigate = useNavigate();

    return <Paper sx={{
        p: 4,
        display: "flex",
        flexDirection: "column",
        alignItems: "center",
        width: "48%",
        justifyContent: "space-between",
        boxShadow: 12
    }}>
        <Typography component="h1" variant="h5">
            <strong>Upload Files</strong>
        </Typography>
        <Typography component="h1" variant="body1" sx={{mt: 2}}>
            Upload your files.<br/>
            You can upload the typing data and isolate data separately, and then
            create a dataset with them.
        </Typography>
        <Button variant="contained"
                startIcon={<UploadIcon/>}
                onClick={() => navigate(WebUiUris.UPLOAD_FILES)}
                sx={{
                    marginTop: 4,
                    width: "75%"
                }}
        >
            Upload Files
        </Button>
    </Paper>;
}
