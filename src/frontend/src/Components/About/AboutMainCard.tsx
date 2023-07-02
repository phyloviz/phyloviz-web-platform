import Paper from "@mui/material/Paper"
import Typography from "@mui/material/Typography"
import * as React from "react"
import {PhylovizHeader} from "../Shared/PhylovizHeader"
import Box from "@mui/material/Box"
import Logo from "../../Assets/logo.png"
import {Facebook, GitHub, Twitter} from "@mui/icons-material"

/**
 * Main card of the about page.
 */
export function AboutMainCard() {
    return <Paper sx={{
        p: 4,
        display: "flex",
        flexDirection: "column",
        mt: 4,
        alignItems: "center"
    }}>
        <PhylovizHeader/>
        <Typography component="h1" variant="body1" sx={{mt: 4}}>
            The PHYLOViZ Web Platform allow users to access and perform phylogenetic analyses and
            visualizations from anywhere with an internet connection, without requiring installation of software
            or access to high-performance computing resources. The modular architecture allow for easy integration
            of new analysis methods and tools, making it a flexible and extensible platform for biomedical
            researchers.
        </Typography>
        <Typography component="h1" variant="body1" sx={{mt: 4}}>
            The GitHub repository for the PHYLOViZ Web Platform is available <a
            href={"https://github.com/phyloviz/phyloviz-web-platform"}>here</a>.
            <br/>
            Any known issues or bugs can be reported to our <a
            href={"https://github.com/phyloviz/phyloviz-web-platform/issues"}>
            GitHub Issues page</a>.
        </Typography>
        <Box sx={{
            display: "flex",
            flexDirection: "row",
            justifyContent: "center",
            alignItems: "center",
            mt: 2
        }}>
            <Box sx={{width: "10%"}}>
                <a href={"https://www.phyloviz.net"}>
                    <img src={Logo} alt={"PHYLOViZ"} width="60%"/>
                </a>
            </Box>
            <Box sx={{width: "10%"}}>
                <a href={"https://github.com/phyloviz"}>
                    <GitHub sx={{color: "#000000"}}/>
                </a>
            </Box>
            <Box sx={{width: "10%"}}>
                <a href={"https://twitter.com/phyloviz"}>
                    <Twitter sx={{color: "#1DA1F2"}}/>
                </a>
            </Box>
            <Box sx={{width: "10%"}}>
                <a href={"https://www.facebook.com/PHYLOViZ/"}>
                    <Facebook sx={{color: "#4267B2"}}/>
                </a>
            </Box>
        </Box>
    </Paper>
}