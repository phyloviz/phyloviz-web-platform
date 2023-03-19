import {MainCard} from "../../Components/Home/MainCard";
import Box from "@mui/material/Box";
import {LoadDatasetCard} from "../../Components/Home/LoadDatasetCard";
import {NewProjectCard} from "../../Components/Home/NewProjectCard";
import {OpenProjectCard} from "../../Components/Home/OpenProjectCard";
import * as React from "react";

/**
 * Home page.
 */
export function Home() {
    return (
        <>
            <MainCard/>
            <Box
                sx={{
                    display: "flex",
                    flexDirection: "row",
                    marginTop: 4,
                    marginBottom: 4,
                    justifyContent: "space-between",
                    width: "100%"
                }}
            >
                <LoadDatasetCard/>
                <NewProjectCard/>
                <OpenProjectCard/>
            </Box>
        </>
    );
}