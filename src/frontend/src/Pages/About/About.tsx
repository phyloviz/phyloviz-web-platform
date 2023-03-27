import * as React from "react"
import {AboutMainCard} from "../../Components/About/AboutMainCard";
import {KeyFeaturesCard} from "../../Components/About/KeyFeaturesCard";
import {ContactsCard} from "../../Components/About/ContactsCard";
import {Container} from "@mui/material";

/**
 * About page.
 */
export default function About() {
    return (
        <Container>
            <AboutMainCard/>
            <KeyFeaturesCard/>
            <ContactsCard/>
        </Container>
    );
}
