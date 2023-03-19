import * as React from "react"
import Paper from "@mui/material/Paper";
import Logo from "../../Assets/logo.png";
import Typography from "@mui/material/Typography";

/**
 * About page.
 */
export default function About() {
    return (
        <Paper sx={{
            p: 4,
            display: "flex",
            flexDirection: "column",
            marginTop: 4,
            alignItems: "center"
        }}>
            <img src={Logo} alt="Logo" width="10%"/>
            <Typography component="h1" variant="h4">
                <strong>PHYLOViZ Web Platform</strong>
            </Typography>
            <Typography component="h1" variant="h5">
                A Modular and Web-Based Tool for Phylogenetic Analysis
            </Typography>
            {/*Add content*/}
        </Paper>
    );
}
