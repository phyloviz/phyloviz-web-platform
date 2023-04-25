import * as React from "react"
import Paper from "@mui/material/Paper"
import Typography from "@mui/material/Typography"
import {Container} from "@mui/material"
import {useLoggedIn, useSession} from "../../Session/Session"
import Avatar from "@mui/material/Avatar"

/**
 * Profile page.
 */
export default function Profile() {
    const session = useSession()
    const loggedIn = useLoggedIn()

    return (
        <Container>
            <Paper sx={{
                p: 4,
                display: "flex",
                flexDirection: "column",
                mt: 4,
                alignItems: "center"
            }}>
                <Typography component="h1" variant="h4">
                    Account
                </Typography>
                <Avatar
                    src={loggedIn && session?.picture ? session.picture : ""}
                    sx={{height: 64, mb: 2, mt: 4, width: 64}}
                />
                <Typography color="textPrimary" gutterBottom variant="h5">
                    {session?.name}
                </Typography>
                <Typography color="textSecondary" variant="body1">
                    {session?.email}
                </Typography>
                {/* Add other profile information if needed */}
            </Paper>
        </Container>
    )
}
