import * as React from "react"
import Paper from "@mui/material/Paper"
import Typography from "@mui/material/Typography"
import {Container} from "@mui/material"
import Box from "@mui/material/Box"
import {DataGrid, GridToolbar} from "@mui/x-data-grid"
import {useIsolateData} from "./useIsolateData"
import LoadingSpinner from "../../../Components/Shared/LoadingSpinner"
import {ErrorAlert} from "../../../Components/Shared/ErrorAlert"

/**
 * IsolateData page.
 */
export default function IsolateData() {
    const {data, loading, error, clearError} = useIsolateData()

    console.log(data)
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
                    Isolate Data
                </Typography>

                {loading && <LoadingSpinner text={"Loading isolate data..."}/>}
                <ErrorAlert error={error} clearError={clearError}/>
                <Box sx={{height: '100%', width: '100%'}}>
                    <DataGrid // If you want to customize this more, see https://mui.com/x/api/data-grid/data-grid/
                        rows={data.rows}
                        columns={data.columns}
                        autoPageSize
                        disableRowSelectionOnClick
                        loading={loading}
                        density="compact"
                        slots={{toolbar: GridToolbar}}
                        sx={{height: '100%', width: '100%'}}
                    />
                </Box>
            </Paper>
        </Container>
    )
}
