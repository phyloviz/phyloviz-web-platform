import * as React from "react"
import Paper from "@mui/material/Paper"
import Typography from "@mui/material/Typography"
import {Container, Table, TableBody, TableCell, TableContainer, TableHead, TableRow} from "@mui/material"

/**
 * TypingData page.
 */
export default function TypingData() {
    // const { } = useTypingData()

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
                    Typing Data
                </Typography>
                {
                    // TODO: Sorting: https://mui.com/material-ui/react-table/#sorting-amp-selecting
                }
                <TableContainer>
                    <Table sx={{mt: 4}} size="small">
                        <TableHead>
                            <TableRow>
                                {headers.map((header, index) => (
                                    <TableCell key={index}>{header}</TableCell>
                                ))}
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {rows.map((row, index) => (
                                <TableRow key={index} sx={{'&:last-child td, &:last-child th': {border: 0}}}>
                                    {row.map((cell, index) => (
                                        <TableCell key={index}>{cell}</TableCell>
                                    ))}
                                </TableRow>
                            ))}
                        </TableBody>
                    </Table>
                </TableContainer>
            </Paper>
        </Container>
    )
}

// TODO: This is just dummy data
const headers = ["ST", "aspA", "ghA", "gltA", "glyA", "pgm", "tkt", "uncA"]

const rows = [
    [1, 2, 1, 54, 3, 4, 1, 5],
    [2, 4, 7, 51, 4, 1, 7, 1],
    [3, 3, 2, 5, 10, 11, 11, 6],
    [4, 10, 11, 16, 7, 10, 5, 7],
    [5, 7, 2, 5, 2, 10, 3, 6],
    [6, 63, 34, 27, 33, 45, 5, 7],
    [7, 8, 10, 2, 2, 14, 12, 6],
    [8, 2, 1, 1, 3, 2, 1, 6],
    [9, 1, 6, 22, 24, 12, 7, 1],
    [10, 2, 59, 4, 38, 17, 12, 5],
]