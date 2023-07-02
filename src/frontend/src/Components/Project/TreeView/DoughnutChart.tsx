import {Box} from "@mui/material";
import Typography from "@mui/material/Typography";
import {Doughnut} from "react-chartjs-2";
import * as React from "react";
import {useRef} from "react";
import {ArcElement, Chart as ChartJS, ChartOptions, Legend, Tooltip} from "chart.js";
import {useDoughnutChart} from "../../../Pages/Project/TreeView/Layouts/ForceDirectedLayout/utils/useDoughnutChart";
import {useReactToPrint} from "react-to-print";
import {PhotoCamera} from "@mui/icons-material";
import IconButton from "@mui/material/IconButton";

ChartJS.register(ArcElement, Tooltip, Legend);

const donutChartOptions: ChartOptions = {
    plugins: {
        legend: {
            display: false
        }
    }
};

export interface DoughnutChartData {
    labels: string[],
    datasets: {
        label: string,
        data: number[],
        backgroundColor: string[],
        borderWidth: number
    }[]
}

interface DoughnutChartProps {
    title: string
    doughnutChartData: DoughnutChartData,
}

export function DoughnutChart(
    {
        title,
        doughnutChartData,
    }: DoughnutChartProps
) {
    const {
        chartRef,
        selectedChartData,
        selectedLabelVisibility,
        setSelectedLabelVisibility,
        unselectedLabelsIndices
    } = useDoughnutChart(title, doughnutChartData)

    const toPrintRef = useRef(null);
    const reactToPrintContent = React.useCallback(() => {
        return toPrintRef.current;
    }, [toPrintRef.current]);
    const handlePrint = useReactToPrint({
        content: reactToPrintContent,
        documentTitle: "Doughnut Chart"
    });

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
                width: "300px",
                height: "550px",
                display: "flex",
                flexDirection: "column",
                overflow: "auto",
            }}
        >
            <Box sx={{
                border: 1,
                borderRadius: 3,
                borderColor: "divider",
                overflow: "auto",
                height: "100%",
                width: "100%",
                display: "flex",
                flexDirection: "column",
            }}>
                <div ref={toPrintRef} style={{height:"80%"}}>
                    <Typography sx={{mb: 2}}>{title}</Typography>
                    {
                        doughnutChartData.labels.length === 0
                            ? <Box sx={{
                                height: "100%",
                                display: "flex",
                                justifyContent: "center",
                                alignItems: "center",
                            }}>
                                <Typography sx={{mb: 2}}>{<em>No data.</em>}</Typography>
                            </Box>
                            : <Box sx={{flexGrow: 1, overflow: "auto"}}>
                                <Doughnut
                                    ref={chartRef}
                                    data={selectedChartData ? selectedChartData : doughnutChartData}
                                    options={donutChartOptions}
                                />
                            </Box>
                    }

                    <Box sx={{
                        border: 1,
                        borderColor: "divider",
                        width: "100%",
                        height: "20%",
                        overflow: "auto",
                    }}>
                        {
                            selectedChartData?.labels.map((label, index) => {
                                return (
                                    <Box
                                        key={label}
                                        sx={{
                                            display: "flex",
                                            flexDirection: "row",
                                            alignItems: "center",
                                            //justifyContent: "space-between",
                                            marginLeft: "10px",
                                            "&:hover": {
                                                cursor: "default"
                                            },
                                            textDecoration: selectedLabelVisibility[index] ? "none" : "line-through"
                                        }}
                                        onClick={() => {
                                            if (selectedLabelVisibility[index]) {
                                                setSelectedLabelVisibility({
                                                    ...selectedLabelVisibility,
                                                    [index]: false
                                                })
                                                chartRef.current?.hide(0, index)

                                            } else {
                                                setSelectedLabelVisibility({
                                                    ...selectedLabelVisibility,
                                                    [index]: true
                                                })
                                                chartRef.current?.show(0, index)
                                            }
                                        }}
                                    >
                                                <span style={{
                                                    backgroundColor: selectedChartData.datasets[0].backgroundColor[index],
                                                    width: "40px",
                                                    height: "10px",
                                                    marginRight: "10px",
                                                }}></span>
                                        <Typography>{label}</Typography>
                                        {/*<Typography>{selectedChartData.datasets[0].data[index]
                                                    / selectedChartData.datasets[0].data.reduce((a, b) => a + b, 0) * 100
                                                }<t wrTypography>*/}
                                    </Box>
                                )
                            })
                        }
                    </Box>
                </div>
                <Box sx={{
                    border: 1,
                    borderColor: "divider",
                    width: "100%",
                    height: "20%",
                    overflow: "auto"
                }}>
                    {
                        unselectedLabelsIndices?.map((index, i) => {
                            return (
                                <Box
                                    key={doughnutChartData.labels[index]}
                                    sx={{
                                        display: "flex",
                                        flexDirection: "row",
                                        justifyContent: "space-between",
                                        marginLeft: "10px",
                                        "&:hover": {
                                            cursor: "default"
                                        },
                                    }}
                                    onClick={() => {
                                    }}>
                                    <Typography>{doughnutChartData.labels[index]}</Typography>
                                </Box>
                            )
                        })
                    }
                </Box>
            </Box>
            <IconButton size="small" onClick={handlePrint}>
                <PhotoCamera/>
            </IconButton>
        </Box>
    )
}