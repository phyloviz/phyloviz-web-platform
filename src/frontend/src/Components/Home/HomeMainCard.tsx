import Paper from "@mui/material/Paper";
import Typography from "@mui/material/Typography";
import * as React from "react";
import {PhylovizHeader} from "../Shared/PhylovizHeader";

/**
 * Main card of the home page.
 */
export function HomeMainCard() {
    return (
        <Paper sx={{
            p: 4,
            display: "flex",
            flexDirection: "column",
            mt: 4,
            alignItems: "center"
        }}>
            <PhylovizHeader/>
            <Typography component="h1" variant="body1" sx={{mt: 4}}>
                PHYLOViZ Web Platform is a modular and web-based tool for phylogenetic analysis. It is
                a modular tool that unifies both <a href={"https://www.phyloviz.net"}>PHYLOViZ</a> and
                <a href={"https://online.phyloviz.net"}> PHYLOViZ Online</a> into a single and new
                platform.
            </Typography>
        </Paper>
    );
}
