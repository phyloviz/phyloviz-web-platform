import * as React from "react";
import Typography from "@mui/material/Typography";
import {Button} from "@mui/material";
import HomeIcon from "@mui/icons-material/Home";
import {useNavigate} from "react-router-dom";
import {WebUiUris} from "../Utils/WebUiUris";
import Box from "@mui/material/Box";
import Logo from "../Assets/logo.png";

/**
 * Page not found component.
 */
export function NotFoundPage() {
    const navigate = useNavigate();

    return (
        <Box sx={{
            display: "flex",
            flexDirection: "row",
            justifyContent: "center",
            alignItems: "center",
            width: '100%'
        }}>
            <Box sx={{
                display: "flex",
                flexDirection: "column",
                alignItems: "center",
                height: '100%',
                width: '50%'
            }}>
                <Typography variant="h1" component="h1" sx={{fontSize: 200, fontWeight: 700}}>
                    404
                </Typography>

                <Typography variant="h2" component="h2" gutterBottom>
                    Page not found
                </Typography>

                <Typography variant="body1" gutterBottom>
                    We couldn't find the page you were looking for.
                </Typography>

                <Button
                    variant="contained"
                    startIcon={<HomeIcon/>}
                    onClick={() => navigate(WebUiUris.HOME)}
                    sx={{width: "50%"}}
                >
                    Go to Home
                </Button>
            </Box>
            <Box
                sx={{
                    display: "flex",
                    flexDirection: "column",
                    justifyContent: "center",
                    alignItems: "center",
                    width: '50%'
                }}
            >
                <img src={Logo} alt="Logo" width="40%"/>
            </Box>
        </Box>
    );
}