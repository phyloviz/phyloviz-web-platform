import Paper from "@mui/material/Paper"
import Typography from "@mui/material/Typography"
import * as React from "react"
import {PhylovizHeader} from "../Shared/PhylovizHeader"

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
                PHYLOViZ Web Platform is a web platform that allows users to access and perform phylogenetic analyses
                and visualizations from anywhere with an internet connection, without requiring installation of software
                or access to high-performance computing resources.
            </Typography>
        </Paper>
    )
}
