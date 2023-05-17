import * as React from "react"
import Paper from "@mui/material/Paper"
import Typography from "@mui/material/Typography"
import {Button, Container} from "@mui/material"
import Box from "@mui/material/Box"
import {DataGrid, GridToolbar} from "@mui/x-data-grid"
import LoadingSpinner from "../../../../Components/Shared/LoadingSpinner"
import {ErrorAlert} from "../../../../Components/Shared/ErrorAlert"
import {useIsolateDataFilter} from "./useIsolateDataFilter";
import CancelIcon from "@mui/icons-material/Cancel";
import FinishIcon from "@mui/icons-material/Done";

/**
 * IsolateDataFilter page.
 */
export default function IsolateDataFilter() {
    const {
        data,
        loading,

        handleClearFilter,
        handleApplyFilter,

        error,
        clearError
    } = useIsolateDataFilter()

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
                    Filter by Isolate Data
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
                <Box sx={{
                    display: "flex",
                    flexDirection: "row",
                    justifyContent: "space-between",
                    width: "100%",
                    mt: 2,
                }}>
                    <Button
                        variant="contained"
                        size="small"
                        startIcon={<CancelIcon/>}
                        onClick={handleClearFilter}
                    >
                        Clear Filter
                    </Button>

                    <Button
                        variant="contained"
                        size="small"
                        startIcon={<FinishIcon/>}
                        onClick={handleApplyFilter}
                    >
                        Apply Filter
                    </Button>
                </Box>
            </Paper>
        </Container>
    )
}
