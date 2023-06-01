import Paper from "@mui/material/Paper"
import Typography from "@mui/material/Typography"
import * as React from "react"
import {PhylovizHeader} from "../Shared/PhylovizHeader"
import {useSession} from "../../Session/Session";

/**
 * Main card of the home page.
 */
export function HomeMainCard() {
    const session = useSession()

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
            <Typography component="h1" variant="h5" sx={{mt: 4}}>
                Welcome{session ? ", " + session.name : ""}! <img
                src="https://raw.githubusercontent.com/ABSphreak/ABSphreak/master/gifs/Hi.gif"
                width="30"/>
            </Typography>
        </Paper>
    )
}
