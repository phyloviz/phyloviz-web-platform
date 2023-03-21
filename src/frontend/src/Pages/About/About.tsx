import * as React from "react"
import Paper from "@mui/material/Paper";
import Logo from "../../Assets/logo.png";
import Typography from "@mui/material/Typography";

/**
 * About page.
 */
export default function About() {
    return (
        <>
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
                <Typography component="h1" variant="body1" sx={{marginTop: 4}}>
                    The PHYLOViZ Web Platform allow users to access and perform phylogenetic analyses and
                    visualizations from anywhere with an internet connection, without requiring installation of software
                    or
                    access to high-performance computing resources. The modular architecture allow for easy integration
                    of new analysis methods and tools, making it a flexible and extensible platform for biomedical
                    researchers.
                </Typography>
                {/*Add content:
            - link to GitHub Repository
            - link to Documentation
            - link to related tools (PHYLOViZ, PHYLOViZ Online)
            - contact information
            */}
            </Paper>
            <Paper sx={{
                p: 4,
                mt: 4,
                mb: 4,
                display: "flex",
                flexDirection: "column",
                width: "100%"
            }}>
                <Typography component="h1" variant="h5">
                    Key Features
                </Typography>
                <Typography component="h1" variant="body1" align={"justify"} sx={{marginTop: 4}}>
                    <ul>
                        <li><strong>Modular Architecture:</strong> The platform has a modular architecture that unifies
                            both
                            versions of PHYLOViZ and allows for easy integration of new analysis methods and tools.
                        </li>
                        <li><strong>Web-Based:</strong> It is a web-based tool, accessible from anywhere with an
                            internet
                            connection, without requiring installation of software or access to high-performance
                            computing
                            resources;
                        </li>
                        <li><strong>Advanced Data Management:</strong> The platform has advanced data management
                            capabilities, including efficient storage and retrieval of large phylogenetic datasets;
                        </li>
                        <li><strong>Optimization Capabilities:</strong> The platform includes optimization capabilities
                            for
                            inference and visualization tasks, which are performed server-side to avoid limitations of
                            the
                            client-side;
                        </li>
                        <li><strong>Support for Epidemiological and Ancillary data:</strong> The platform integrates
                            epidemiological and ancillary data for isolates under study, enhancing the visualization and
                            exploration of inference results;
                        </li>
                        <li><strong>User-friendly interface:</strong> The platform has a user-friendly interface for
                            performing phylogenetic analyses and visualizations.
                        </li>
                    </ul>
                </Typography>
            </Paper>
            <Paper sx={{
                p: 4,
                mt: 4,
                mb: 4,
                display: "flex",
                flexDirection: "column",
                width: "100%"
            }}>
                <Typography component="h1" variant="h5">
                    Contacts
                </Typography>
                <Typography component="h1" variant="body1" align={"justify"} sx={{marginTop: 4}}>
                    {/*TODO*/}
                </Typography>
            </Paper>
        </>
    );
}
