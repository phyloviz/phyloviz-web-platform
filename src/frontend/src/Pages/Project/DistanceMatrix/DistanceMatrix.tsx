import * as React from "react"
import Paper from "@mui/material/Paper"
import Typography from "@mui/material/Typography"
import {Button, Container, FormControl, InputLabel, MenuItem, Select} from "@mui/material"
// @ts-ignore
import HeatMap from "react-heatmap-grid"
import Box from "@mui/material/Box";
// @ts-ignore
import {Colorscale} from 'react-colorscales';
import {Download, Print} from "@mui/icons-material";
import {useDistanceMatrix} from "./useDistanceMatrix";

/**
 * DistanceMatrix page.
 */
export default function DistanceMatrix() {
    const {
        data,
        cellStyle
    } = useDistanceMatrix()

    // @ts-ignore
    return (
        <Container>
            <Paper sx={{
                p: 4,
                display: "flex",
                flexDirection: "column",
                mt: 4,
                width: "100%",
                alignItems: "center"
            }}>
                <Typography component="h1" variant="h4">
                    Distance Matrix
                </Typography>
                {
                    // TODO: Add distance matrix information
                }
                <Box sx={{
                    width: "100%",
                    height: "100%",
                    mt: 4,
                    display: "flex",
                    flexDirection: "row",
                    justifyContent: "center",
                    alignItems: "space-between"
                }}>
                    <Box sx={{width: "50%", height: "100%"}}>
                        <HeatMap
                            xLabels={["", "", "", "", "", "", "", "", "", "", "", "", "", ""]}
                            yLabels={["", "", "", "", "", "", "", "", "", "", "", "", "", ""]}
                            data={data}
                            squares
                            cellStyle={cellStyle}
                            cellRender={(value: number) => value && <div>{value}</div>}
                        />
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
                            Matrix Options
                        </Typography>
                        <FormControl sx={{width: "100%", mb: 1, mt: 2}}>
                            <InputLabel id="order">Order</InputLabel>
                            <Select
                                labelId="order"
                                label="Order"
                                //TODO: value={}
                                //TODO: onChange={}
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
                                width: "50%",
                                height: "200px",
                                display: "flex",
                                flexDirection: "column",
                                justifyContent: "center",
                                alignItems: "center",
                                backgroundColor: "grey.100",
                                mr: 2
                            }}>
                                <Typography component="h1" variant="h6">
                                    Color Scale
                                </Typography>
                                {/*TODO: Add color scale*/}
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
            </Paper>
        </Container>
    )
}
