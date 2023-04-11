import * as React from "react"
import Paper from "@mui/material/Paper"
import Typography from "@mui/material/Typography"
import {Container} from "@mui/material"
import {DataGrid, GridToolbar} from '@mui/x-data-grid';
import Box from "@mui/material/Box";

/**
 * TypingData page.
 */
export default function TypingData() {
    //const { data, loading } = useTypingData()

    // TODO: This is just dummy data
    const data = {
        columns: [
            {field: 'st', headerName: 'ST', width: 100},
            {field: 'aspA', headerName: 'aspA', width: 100},
            {field: 'ghA', headerName: 'ghA', width: 100},
            {field: 'gltA', headerName: 'gltA', width: 100},
            {field: 'glyA', headerName: 'glyA', width: 100},
            {field: 'pgm', headerName: 'pgm', width: 100},
            {field: 'tkt', headerName: 'tkt', width: 100},
            {field: 'uncA', headerName: 'uncA', width: 100},
        ],
        rows: [
            {id: 1, st: 1, aspA: 2, ghA: 1, gltA: 54, glyA: 3, pgm: 4, tkt: 1, uncA: 5},
            {id: 2, st: 2, aspA: 4, ghA: 7, gltA: 51, glyA: 4, pgm: 1, tkt: 7, uncA: 1},
            {id: 3, st: 3, aspA: 3, ghA: 2, gltA: 5, glyA: 10, pgm: 11, tkt: 11, uncA: 6},
            {id: 4, st: 4, aspA: 10, ghA: 11, gltA: 16, glyA: 7, pgm: 10, tkt: 5, uncA: 7},
            {id: 5, st: 5, aspA: 7, ghA: 2, gltA: 5, glyA: 2, pgm: 10, tkt: 3, uncA: 6},
            {id: 6, st: 6, aspA: 63, ghA: 34, gltA: 27, glyA: 33, pgm: 45, tkt: 5, uncA: 7},
            {id: 7, st: 7, aspA: 8, ghA: 10, gltA: 2, glyA: 2, pgm: 14, tkt: 12, uncA: 6},
            {id: 8, st: 8, aspA: 2, ghA: 1, gltA: 1, glyA: 3, pgm: 2, tkt: 1, uncA: 6},
            {id: 9, st: 9, aspA: 1, ghA: 6, gltA: 22, glyA: 24, pgm: 12, tkt: 7, uncA: 1},
            {id: 10, st: 10, aspA: 2, ghA: 59, gltA: 4, glyA: 38, pgm: 17, tkt: 12, uncA: 5},
        ],
        initialState: {
            rowGrouping: {
                groupBy: ['commodity'],
            }
        }
    }

    const loading = false

    return (
        <Container>
            <Paper sx={{
                p: 4,
                display: "flex",
                flexDirection: "column",
                mt: 4, mb: 4,
                alignItems: "center",
                height: '98%',
            }}>
                <Typography component="h1" variant="h4">
                    Typing Data
                </Typography>

                <Box sx={{height: '100%', width: '100%'}}>
                    <DataGrid
                        rows={data.rows}
                        columns={data.columns}
                        initialState={{ // TODO: Explore the interfaces for DataGrid to customize the grid
                            pagination: {
                                paginationModel: {
                                    pageSize: 10
                                }
                            },
                        }}
                        autoPageSize
                        disableRowSelectionOnClick
                        loading={loading}
                        slots={{toolbar: GridToolbar}}
                        sx={{height: '100%', width: '100%'}}
                    />
                </Box>

                {/*<TableContainer>
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
                </TableContainer>*/}
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