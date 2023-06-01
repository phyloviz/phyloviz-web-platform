import Paper from "@mui/material/Paper"
import Typography from "@mui/material/Typography"
import Box from "@mui/material/Box"
import IselLogo from "../../Assets/isel.png"
import InescLogo from "../../Assets/inesc.png"
import IstLogo from "../../Assets/ist.png"
import ImmLogo from "../../Assets/imm.png"
import * as React from "react"

/**
 * Contacts Card for the About Page.
 */
export function ContactsCard() {
    return <Paper sx={{
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
        <Typography component="h1" variant="body1" sx={{mt: 4}}>
            André Jesus (1) - <a href={"mailto:andre.jesus.pilar@gmail.com"}>andre.jesus.pilar@gmail.com</a>
            <br/>
            André Páscoa (1) - <a href={"mailto:andre@pascoa.org"}>andre@pascoa.org</a>
            <br/>
            Nyckollas Brandão (1) - <a href={"mailto:nyckbrandao1236@gmail.com"}>nyckbrandao1236@gmail.com</a>
            <br/>
            Cátia Vaz (1,2) - <a href={"mailto:cvaz@cc.isel.ipl.pt"}>cvaz@cc.isel.ipl.pt</a>
            <br/>
            Alexandre P. Francisco (2,3) - <a href={"mailto:aplf@ist.utl.pt"}>aplf@ist.utl.pt</a>
            <br/>
            Mário Ramirez (4) - <a href={"mailto:ramirez@medicina.ulisboa.pt"}>ramirez@medicina.ulisboa.pt</a>
        </Typography>
        <Box sx={{
            mt: 2,
            display: "flex",
            flexDirection: "row",
            justifyContent: "space-between"
        }}>
            <Box sx={{
                display: "flex",
                flexDirection: "column",
                alignItems: "center",
                justifyContent: "space-between",
                width: "25%"
            }}>
                <img src={IselLogo} alt="ISEL Logo" width="60%"/>
                <Typography component="h1" variant="body2">
                    (1) Instituto Superior de Engenharia de Lisboa, Instituto Politécnico de Lisboa, Lisboa,
                    Portugal
                </Typography>
            </Box>
            <Box sx={{
                display: "flex",
                flexDirection: "column",
                alignItems: "center",
                justifyContent: "space-between",
                width: "25%"
            }}>
                <img src={InescLogo} alt="INESC-ID Logo" width="60%"/>
                <Typography component="h1" variant="body2">
                    (2) INESC-ID, R. Alves Redol 9, 1000-029 Lisboa, Portugal
                </Typography>
            </Box>
            <Box sx={{
                display: "flex",
                flexDirection: "column",
                alignItems: "center",
                justifyContent: "space-between",
                width: "25%"
            }}>
                <img src={IstLogo} alt="IST Logo" width="60%"/>
                <Typography component="h1" variant="body2">
                    (3) Instituto Superior Técnico, Universidade de Lisboa, Lisboa, Portugal
                </Typography>
            </Box>
            <Box sx={{
                display: "flex",
                flexDirection: "column",
                alignItems: "center",
                justifyContent: "space-between",
                width: "25%"
            }}>
                <img src={ImmLogo} alt="IMM Logo" width="80%"/>
                <Typography component="h1" variant="body2">
                    (4) Instituto de Medicina Molecular João Lobo Antunes, Faculdade de Medicina, Universidade de
                    Lisboa, Lisboa, Portugal
                </Typography>
            </Box>
        </Box>
    </Paper>
}