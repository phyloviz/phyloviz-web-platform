import * as React from "react"
import Paper from "@mui/material/Paper"
import Logo from "../../Assets/logo.png"
import Typography from "@mui/material/Typography"
import {Button, Container} from "@mui/material"
import {OpenInNew} from "@mui/icons-material";

/**
 * APIInfo page.
 */
export default function APIInfo() {
    return (
        <Container>
            <Paper sx={{
                p: 4,
                display: "flex",
                flexDirection: "column",
                mt: 4,
                alignItems: "center"
            }}>
                <img src={Logo} alt="Logo" width="10%"/>
                <Typography component="h1" variant="h4">
                    <strong>PHYLOViZ Web Platform - API</strong>
                </Typography>
                <Typography component="h1" variant="h5">
                    A Modular and Web-Based Tool for Phylogenetic Analysis
                </Typography>
                <Typography component="h1" variant="body1" sx={{mt: 4}}>
                    A RESTful API was developed to allow the access to a range of services so that more advanced users
                    can programmatically use some of PHYLOViZ Web Platform features without using the application's
                    graphical interface.
                </Typography>
                <Button
                    variant="contained"
                    startIcon={<OpenInNew/>}
                    onClick={() => window.open("https://github.com/phyloviz/phyloviz-web-platform/wiki/api-documentation")}
                    sx={{mt: 4, width: "30%"}}
                >
                    API Documentation
                </Button>
            </Paper>
        </Container>
    )
}
