import {HomeMainCard} from "../../Components/Home/HomeMainCard"
import Box from "@mui/material/Box"
import {NewProjectCard} from "../../Components/Home/NewProjectCard"
import {OpenProjectCard} from "../../Components/Home/OpenProjectCard"
import * as React from "react"
import {Container} from "@mui/material"

/**
 * Home page.
 */
export function Home() {
    return (
        <Container>
            <HomeMainCard/>
            <Box sx={{
                display: "flex",
                flexDirection: "row",
                mt: 4,
                mb: 4,
                justifyContent: "space-between",
                width: "100%"
            }}>
                <NewProjectCard/>
                <OpenProjectCard/>
            </Box>
        </Container>
    )
}