import * as React from "react"
import Paper from "@mui/material/Paper"
import Typography from "@mui/material/Typography"
import {Container} from "@mui/material"
import Box from "@mui/material/Box";
import {DataGrid, GridToolbar} from "@mui/x-data-grid";

/**
 * IsolateData page.
 */
export default function IsolateData() {
    // const { } = useIsolateData()

    // TODO: This is just dummy data
    const data = {
        columns: [
            {field: 'id', headerName: 'ID', width: 70},
            {field: 'isolate', headerName: 'Isolate', width: 130},
            {field: 'aliases', headerName: 'Aliases', width: 130},
            {field: 'country', headerName: 'Country', width: 130},
            {field: 'continent', headerName: 'Continent', width: 130},
            {field: 'region', headerName: 'Region', width: 130},
            {field: 'town_or_city', headerName: 'Town or City', width: 130},
            {field: 'year', headerName: 'Year', width: 130},
            {field: 'month', headerName: 'Month', width: 130},
            {field: 'isolation_date', headerName: 'Isolation Date', width: 130},
        ],
        rows: [
            {id: 1, isolate: 'P09', aliases: '', country: 'Unknown', continent: '', region: '', town_or_city: '', year: '', month: '', isolation_date: ''},
            {id: 2, isolate: 'P12', aliases: '', country: 'Unknown', continent: '', region: '', town_or_city: '', year: '', month: '', isolation_date: ''},
            {id: 3, isolate: 'P18', aliases: 'ATCC43439', country: 'Canada', continent: 'North America', region: '', town_or_city: '', year: '', month: '', isolation_date: ''},
            {id: 4, isolate: 'P22', aliases: 'ATCC43448', country: 'Canada', continent: 'North America', region: '', town_or_city: '', year: '', month: '', isolation_date: ''},
            {id: 5, isolate: 'P26', aliases: 'ATCC43477', country: 'Unknown', continent: '', region: '', town_or_city: '', year: '', month: '', isolation_date: ''},
            {id: 6, isolate: 'P27', aliases: '', country: 'Unknown', continent: '', region: '', town_or_city: '', year: '', month: '', isolation_date: ''},
            {id: 7, isolate: 'P31', aliases: '', country: 'Unknown', continent: '', region: '', town_or_city: '', year: '', month: '', isolation_date: ''},
            {id: 8, isolate: 'P33', aliases: '', country: 'Unknown', continent: '', region: '', town_or_city: '', year: '', month: '', isolation_date: ''},
            {id: 9, isolate: 'P43', aliases: '', country: 'Canada', continent: 'North America', region: 'Vancouver', town_or_city: '', year: '', month: '', isolation_date: ''},
            {id: 10, isolate: 'P55', aliases: '', country: 'Unknown', continent: '', region: '', town_or_city: '', year: '', month: '', isolation_date: ''},
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
                    Isolate Data
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
    ["10", "P55", "", "Unknown", "", "", "", "", "", ""]
]