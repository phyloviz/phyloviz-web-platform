import * as React from "react"
import Paper from "@mui/material/Paper"
import Typography from "@mui/material/Typography"
import {Container} from "@mui/material"
import Box from "@mui/material/Box";
import {useNews} from "./useNews";
import {NewsCard} from "../../Components/News/NewsCard";

/**
 * News page.
 */
export default function News() {
    const {news} = useNews()

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
                    News
                </Typography>
                <Typography component="h1" variant="body1" sx={{mt: 4}}>
                    Latest news regarding PHYLOViZ Web Platform and related publications.
                </Typography>
                <Box sx={{
                    display: "grid",
                    gridTemplateColumns: "repeat(4, 1fr)",
                    gap: 2,
                    mt: 4,
                    width: "100%"
                }}>
                    {
                        news.map((n => <NewsCard key={n.title} {...n}/>))
                    }
                </Box>
            </Paper>
        </Container>
    )
}
