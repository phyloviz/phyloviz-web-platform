import * as React from "react"
import Paper from "@mui/material/Paper"
import Logo from "../../Assets/logo.png"
import Typography from "@mui/material/Typography"
import {Container} from "@mui/material"

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
                <Typography component="h1" variant="body1" sx={{mt: 4}}>
                    The API documentation is available <a
                    href="https://github.com/bodybuilders-team/phyloviz-web-platform/wiki/api-documentation">here</a>.
                </Typography>
            </Paper>
        </Container>
    )
}
