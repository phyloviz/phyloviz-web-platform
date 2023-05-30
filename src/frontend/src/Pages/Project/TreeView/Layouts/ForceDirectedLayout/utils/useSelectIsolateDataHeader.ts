import React, {useEffect, useState} from "react";
import interpolate from "color-interpolate";
import {parseRGB, VizLink, VizNode} from "../useForceDirectedLayout";
import {
    Row
} from "../../../../../../Services/Visualization/models/getIsolateDataProfiles/GetIsolateDataRowsOutputModel";
import {TreeViewGraph} from "../cosmos/TreeViewGraph";
import {capitalize} from "@mui/material";
import {BubbleDataPoint, ChartData, Point} from "chart.js";

const colorPallete = ["#ff595e", "#ffca3a", "#8ac926", "#1982c4", "#6a4c93"]

/**
 * Returns the selected header.
 *
 * @param isolateDataRows the rows of isolate data
 * @param graphRef the graph reference
 * @param setDoughnutChartData the doughnut chart data
 * @param setDoughnutChartTitle the doughnut chart title
 */
export function useSelectIsolateDataHeader(
    isolateDataRows: Row[],
    graphRef: React.MutableRefObject<TreeViewGraph<VizNode, VizLink> | undefined>,
    setDoughnutChartData: React.Dispatch<React.SetStateAction<ChartData<"doughnut", (number | [number, number] | Point | BubbleDataPoint | null)[]> | null>>,
    setDoughnutChartTitle: React.Dispatch<React.SetStateAction<string>>
) {
    const [selectedHeader, setSelectedHeader] = useState<string>('')

    useEffect(() => {
        if (graphRef.current === undefined || isolateDataRows.length === 0) {
            return
        }

        if (selectedHeader === '') {
            graphRef.current?.removePieCharts()
            setDoughnutChartTitle(selectedHeader)
            setDoughnutChartData(null)
            return
        }

        const selectedHeaderValues = isolateDataRows.map((row) => row.row[selectedHeader])

        const doughnutChartLabels = Array.from(new Set(selectedHeaderValues))

        const doughnutChartLabelToLabelIndexHashMap = new Map<string, number>()

        for (let i = 0; i < doughnutChartLabels.length; i++) {
            doughnutChartLabelToLabelIndexHashMap.set(doughnutChartLabels[i], i)
        }

        const doughtnutChartColors = doughnutChartLabels.map((label) => {
            return interpolate(colorPallete)(Math.random())
        })

        const doughnutChartData = doughnutChartLabels.map((label) => {
            return selectedHeaderValues.filter((value) => value === label).length
        })

        const sliceData = new Map<string, { label: string, occurrences: number, color: number[] }[]>()

        for (const row of isolateDataRows) {
            const profileId = row.profileId
            parseInt(row.id);
            const nodeSliceData = sliceData.get(profileId)
            const label = row.row[selectedHeader]

            const labelIndex = doughnutChartLabelToLabelIndexHashMap.get(label)!

            if (nodeSliceData) {
                const slice = nodeSliceData.find((slice) => slice.label === label)

                if (slice) {
                    slice.occurrences += 1
                    continue
                }
            }

            let color = doughtnutChartColors[labelIndex]

            if (!color) {
                // throw new Error("Color not found")
                color = "rgb(0,0,0)"
            }
            const newSliceData = {
                label,
                occurrences: 1,
                color: parseRGB(color)
            }

            const newNodeData = nodeSliceData ? [...nodeSliceData, newSliceData] : [newSliceData]

            newNodeData.sort((a, b) => b.occurrences - a.occurrences)

            sliceData.set(profileId, newNodeData)
        }

        graphRef.current.renderPieCharts(sliceData)

        setDoughnutChartTitle(selectedHeader)
        setDoughnutChartData({
            labels: doughnutChartLabels,
            datasets: [
                {
                    label: capitalize(selectedHeader),
                    data: doughnutChartData,
                    backgroundColor: doughtnutChartColors,
                    borderWidth: 1
                }
            ]
        })
    }, [selectedHeader])

    return {
        selectedIsolateHeader: selectedHeader,
        setSelectedIsolateHeader: setSelectedHeader
    }
}

