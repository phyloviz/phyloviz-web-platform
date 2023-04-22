import {Button, Card, CardActions, CardContent, CardHeader} from "@mui/material"
import Typography from "@mui/material/Typography"
import {Info} from "@mui/icons-material"
import * as React from "react"
import {News} from "../../Pages/News/useNews"

/**
 * Card for a news item.
 *
 * @param news the news item.
 */
export function NewsCard(news: News) {
    return (
        <Card sx={{width: "100%"}} variant={"outlined"}>
            <CardHeader title={news.title} subheader={news.date}/>
            <CardContent>
                <Typography variant="body2" color="text.secondary" textAlign={"justify"}>
                    {news.description}
                </Typography>
            </CardContent>
            {
                news.link && (
                    <CardActions>
                        <Button size={"small"} href={news.link} startIcon={<Info/>}>
                            Read more
                        </Button>
                    </CardActions>
                )
            }
        </Card>
    )
}
