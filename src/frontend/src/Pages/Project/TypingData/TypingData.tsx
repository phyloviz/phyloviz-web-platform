import * as React from "react"
import Paper from "@mui/material/Paper"
import Typography from "@mui/material/Typography"
import {Container} from "@mui/material"
import {DataGrid, GridToolbar} from '@mui/x-data-grid'
import Box from "@mui/material/Box"
import {useTypingData} from "./useTypingData"
import LoadingSpinner from "../../../Components/Shared/LoadingSpinner"
import Alert from "@mui/material/Alert"

/**
 * TypingData page.
 */
export default function TypingData() {
    const {data, loading, error} = useTypingData()

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
                <Typography component="h1" variant="h4" sx={{mb: 2}}>
                    Typing Data
                </Typography>

                {loading && <LoadingSpinner text={"Loading typing data..."}/>}
                {error && <Alert severity="error">{error}</Alert>}

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
            </Paper>
        </Container>
    )
}
