import Paper from "@mui/material/Paper";
import Logo from "../../Assets/logo.png";
import Typography from "@mui/material/Typography";
import * as React from "react";

/**
 * Main card of the home page.
 */
export function MainCard() {
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
            <Typography component="h1" variant="body1" sx={{marginTop: 4}}>
                PHYLOViZ Web Platform is a modular and web-based tool for phylogenetic analysis. It is
                a modular tool that unifies both <a href={"https://www.phyloviz.net"}>PHYLOViZ</a> and
                <a href={"https://online.phyloviz.net"}> PHYLOViZ Online</a> into a single and new
                platform.
            </Typography>
        </Paper>
    );
}
