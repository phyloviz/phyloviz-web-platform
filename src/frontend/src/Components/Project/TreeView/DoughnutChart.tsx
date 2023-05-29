import {Box} from "@mui/material";
import Typography from "@mui/material/Typography";
import {Doughnut} from "react-chartjs-2";
import * as React from "react";
import {BubbleDataPoint, ChartData, ChartOptions, Point} from "chart.js";

const donutChartOptions: ChartOptions = {
    plugins: {
        legend: {
            position: 'bottom'
        }
    }
};

interface DoughnutChartProps {
    title: string
    doughnutChartData: ChartData<"doughnut", (number | [number, number] | Point | BubbleDataPoint | null)[], unknown>
}

export function DoughnutChart({title, doughnutChartData}: DoughnutChartProps) {

    return (
        <Box
            sx={{
                position: "absolute",
                right: 0,
                top: "20%",
                zIndex: 1,
                backgroundColor: "white",
                borderRadius: 3,
                p: 1,
                mr: "10px",
                border: 1,
                borderColor: 'divider',
                maxHeight: "300px",
                overflow: "auto",
            }}
        >
            <Typography sx={{mb: 2}}>{title}</Typography>
            <Doughnut data={doughnutChartData!} options={donutChartOptions}/>
        </Box>
    )
}