import * as React from "react"
import {Button, Container, FormControl, InputLabel, MenuItem, Select, Typography} from "@mui/material"
// @ts-ignore
import HeatMap from "react-heatmap-grid"
import Box from "@mui/material/Box"
// @ts-ignore
import {Colorscale} from 'react-colorscales'
import {useDistanceMatrix} from "./useDistanceMatrix"
import {DistanceMatrixInfoCard} from "../../../Components/Project/DistanceMatrix/DistanceMatrixInfoCard"
import LoadingSpinner from "../../../Components/Shared/LoadingSpinner";
import {ErrorAlert} from "../../../Components/Shared/ErrorAlert";
import {Download, Print} from "@mui/icons-material"

/**
 * DistanceMatrix page.
 */
export default function DistanceMatrix() {
    const {
        loading,
        error,
        clearError,
        distanceMatrix
    } = useDistanceMatrix()

    // @ts-ignore
    return (
        <Box sx={{position: "relative", width: "90%"}}>
            <DistanceMatrixInfoCard distanceMatrix={distanceMatrix}/>
            <Container>
                {loading &&
                    <div style={{
                        width: "100%",
                        height: "100%",
                        position: "absolute",
                        display: "flex",
                        justifyContent: "center",
                        alignItems: "center"
                    }}>
                        <div style={{transform: "scale(1.5)"}}>
                            <LoadingSpinner text={"Loading Distance Matrix"}/>
                        </div>
                    </div>
                }
                <ErrorAlert error={error} clearError={clearError}/>
                <Box sx={{
                    width: "100%",
                    height: "100%",
                    mt: 10,
                    display: "flex",
                    flexDirection: "row",
                    justifyContent: "center",
                    alignItems: "space-between"
                }}>
                    <Box sx={{width: "50%", height: "100%"}}>
                        <div id="heatmap"></div>
                    </Box>
                    <Box sx={{
                        width: "50%",
                        height: "100%",
                        display: "flex",
                        flexDirection: "column",
                        justifyContent: "center",
                        alignItems: "center"
                    }}>
                        <Typography component="h1" variant="h5">
                            Options
                        </Typography>
                        <FormControl sx={{width: "75%", mb: 1, mt: 2}}>
                            <InputLabel id="order">Order</InputLabel>
                            <Select
                                labelId="order"
                                label="Order"
                                value=""
                                onChange={() => {
                                }}
                                MenuProps={{PaperProps: {sx: {maxHeight: 150}}}}
                            >
                                <MenuItem value={"st"}>ST</MenuItem>
                                <MenuItem value={"name"}>Name</MenuItem>
                                <MenuItem value={"location"}>Location</MenuItem>
                                <MenuItem value={"strain"}>Strain</MenuItem>
                                <MenuItem value={"collection"}>Collection</MenuItem>
                                <MenuItem value={"emm-type"}>EMM Type</MenuItem>
                                <MenuItem value={"group-carbohydrate"}>Group Carbohydrate</MenuItem>
                            </Select>
                        </FormControl>
                        <Box sx={{
                            width: "100%",
                            height: "25%",
                            mt: 4,
                            display: "flex",
                            flexDirection: "row",
                            justifyContent: "center",
                            alignItems: "space-between"
                        }}>
                            <Box sx={{
                                width: "40%",
                                display: "flex",
                                flexDirection: "column",
                                justifyContent: "center",
                                alignItems: "center",
                                mr: 2
                            }}>
                                <div id="colorscale"></div>
                            </Box>
                            <Box sx={{
                                width: "50%",
                                height: "100%",
                                display: "flex",
                                flexDirection: "column",
                                justifyContent: "center",
                                alignItems: "center"
                            }}>
                                <Button
                                    variant="contained"
                                    sx={{mt: 2, width: "100%"}}
                                    onClick={() => {
                                        // TODO: To be implemented
                                    }}
                                    startIcon={<Download/>}
                                >Export</Button>
                                <Button
                                    variant="contained"
                                    sx={{mt: 2, width: "100%"}}
                                    onClick={() => {
                                        // TODO: To be implemented
                                    }}
                                    startIcon={<Print/>}
                                >Print</Button>
                            </Box>
                        </Box>
                    </Box>
                </Box>
            </Container>
        </Box>
    )
}
