import {HomeMainCard} from "../../Components/Home/HomeMainCard"
import Box from "@mui/material/Box"
import {NewProjectCard} from "../../Components/Home/NewProjectCard"
import {OpenProjectCard} from "../../Components/Home/OpenProjectCard"
import * as React from "react"
import {Container} from "@mui/material"
import {useLoggedIn} from "../../Session/Session";
import {GetStartedCard} from "../../Components/Home/GetStartedCard";
import {SignUpCard} from "../../Components/Home/SignUpCard"

/**
 * Home page.
 */
export function Home() {
    const loggedIn = useLoggedIn()

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
                {loggedIn ? <NewProjectCard/> : <GetStartedCard/>}
                {loggedIn ? <OpenProjectCard/> : <SignUpCard/>}
            </Box>
        </Container>
    )
}