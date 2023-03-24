import {HomeMainCard} from "../../Components/Home/HomeMainCard";
import Box from "@mui/material/Box";
import {NewProjectCard} from "../../Components/Home/NewProjectCard";
import {OpenProjectCard} from "../../Components/Home/OpenProjectCard";
import * as React from "react";

/**
 * Home page.
 */
export function Home() {
    return (
        <>
            <HomeMainCard/>
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
                {/*<LoadDatasetCard/>*/}
                <NewProjectCard/>
                <OpenProjectCard/>
            </Box>
        </>
    );
}