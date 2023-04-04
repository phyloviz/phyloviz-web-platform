import Logo from "../../Assets/logo.png"
import Typography from "@mui/material/Typography"
import * as React from "react"

/**
 * Header with the logo and the title of the PHYLOViZ Web Platform.
 */
export function PhylovizHeader() {
    return <>
        <img src={Logo} alt="Logo" width="10%"/>
        <Typography component="h1" variant="h4">
            <strong>PHYLOViZ Web Platform</strong>
        </Typography>
        <Typography component="h1" variant="h5">
            A Modular and Web-Based Tool for Phylogenetic Analysis
        </Typography>
    </>
}