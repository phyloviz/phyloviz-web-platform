import Paper from "@mui/material/Paper";
import Typography from "@mui/material/Typography";
import * as React from "react";

/**
 * Key Features Card for the About Page.
 */
export function KeyFeaturesCard() {
    return <Paper sx={{
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
    </Paper>;
}