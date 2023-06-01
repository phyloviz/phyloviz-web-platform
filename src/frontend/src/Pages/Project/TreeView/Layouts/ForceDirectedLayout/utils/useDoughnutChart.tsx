import * as React from "react";
import {useEffect, useRef} from "react";
import {sort} from "d3-array";
import {Chart} from "chart.js";
import {DoughnutChartData} from "../../../../../../Components/Project/TreeView/DoughnutChart";

export function useDoughnutChart(
    title: string,
    doughnutChartData: DoughnutChartData
) {
    const chartRef = useRef<Chart<'doughnut', any[], any>>(null);

    const [selectedLabelsIndices, setSelectedLabelsIndices] = React.useState<number[] | null | undefined>(undefined);
    const [unselectedLabelsIndices, setUnselectedLabelsIndices] = React.useState<number[] | null | undefined>(undefined);

    const [selectedChartData, setSelectedChartData] = React.useState<DoughnutChartData | null>(null)

    const [selectedLabelVisibility, setSelectedLabelVisibility] = React.useState<boolean[]>([])

    useEffect(() => {
        const data = doughnutChartData.datasets[0].data
        const indices = doughnutChartData.labels.map((label, index) => index)

        const sortedIndices = sort(indices, (a, b) => {
            return data[b] - data[a]
        })

        const selectedIndices = sortedIndices.slice(0, 10)
        const unselectedIndices = sortedIndices.slice(10, sortedIndices.length)

        console.log("selectedIndices:", selectedIndices)
        console.log("unselectedIndices:", unselectedIndices)

        setSelectedLabelsIndices(selectedIndices)
        setUnselectedLabelsIndices(unselectedIndices)

        const chart = chartRef.current

        if (!chart)
            return

        const newData = selectedIndices.map(idx => {
            return {
                label: doughnutChartData.labels[idx],
                data: doughnutChartData.datasets[0].data[idx],
                backgroundColor: doughnutChartData.datasets[0]?.backgroundColor![idx]
            }
        }).concat({
            label: "Other",
            data: unselectedIndices.map(idx => doughnutChartData.datasets[0].data[idx]).reduce((a, b) => a + b, 0),
            backgroundColor: "rgb(128,128,128)"
        })

        setSelectedLabelVisibility(selectedIndices.map(( ) => true).concat(true))

        setSelectedChartData({
            labels: newData.map(d => d.label),
            datasets: [{
                label: title,
                data: newData.map(d => d.data),
                backgroundColor: newData.map(d => d.backgroundColor),
                borderWidth: 1
            }]
        })
    }, [doughnutChartData])

    return {
        chartRef,
        selectedChartData,
        selectedLabelVisibility,
        setSelectedLabelVisibility,
        unselectedLabelsIndices
    }
}