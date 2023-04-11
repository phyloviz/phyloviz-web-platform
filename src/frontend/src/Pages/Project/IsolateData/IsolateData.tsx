import * as React from "react"
import Paper from "@mui/material/Paper"
import Typography from "@mui/material/Typography"
import {Container, Table, TableBody, TableCell, TableContainer, TableHead, TableRow} from "@mui/material"

/**
 * IsolateData page.
 */
export default function IsolateData() {
    // const { } = useIsolateData()

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
                    Isolate Data
                </Typography>
                {
                    // TODO: Sorting: https://mui.com/material-ui/react-table/#sorting-amp-selecting
                    // Or use DataGrid like in the typing data page
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
const headers = ["id", "isolate", "aliases", "country", "continent", "region", "town_or_city", "year", "month", "isolation_date"]

const rows = [
    ["1", "P09", "", "Unknown", "", "", "", "", "", ""],
    ["2", "P12", "", "Unknown", "", "", "", "", "", ""],
    ["3", "P18", "ATCC43439", "Canada", "North America", "", "", "", "", ""],
    ["4", "P22", "ATCC43448", "Canada", "North America", "", "", "", "", ""],
    ["5", "P26", "ATCC43477", "Unknown", "", "", "", "", "", ""],
    ["6", "P27", "", "Unknown", "", "", "", "", "", ""],
    ["7", "P31", "", "Unknown", "", "", "", "", "", ""],
    ["8", "P33", "", "Unknown", "", "", "", "", "", ""],
    ["9", "P43", "", "Canada", "North America", "Vancouver", "", "", "", ""],
    ["10", "P55", "", "Unknown", "", "", "", "", "", ""],
]