import {useEffect, useState} from "react";
import interpolate from "color-interpolate";
import {capitalize} from "@mui/material";
import {DoughnutChartData} from "../../../../../../Components/Project/TreeView/DoughnutChart";
import {
    Row
} from "../../../../../../Services/Visualization/models/getIsolateDataProfiles/GetIsolateDataRowsOutputModel";
import {TreeViewGraph} from "../cosmos/TreeViewGraph";
import {parseRGB, VizLink, VizNode} from "../useForceDirectedLayout";

const colorPallete = ["#ff595e", "#ffca3a", "#8ac926", "#1982c4", "#6a4c93"]

export interface SliceData {
    label: string
    occurrences: number
    color: number[]
}

export function useSelectIsolateDataHeader(
    isolateDataRows: Row[] | undefined,
    loadingIsolateDataRows: boolean,
    graphRef: React.MutableRefObject<TreeViewGraph<VizNode, VizLink> | undefined>,
    setDoughnutChartData: (data: DoughnutChartData | null) => void,
    setDoughnutChartTitle: (title: string) => void
) {
    const [selectedHeader, setSelectedHeader] = useState<string>('')

    useEffect(() => {
        if (graphRef.current === undefined || loadingIsolateDataRows || !isolateDataRows) {
            return
        }

        if (selectedHeader === '') {
            graphRef.current?.removePieCharts()
            setDoughnutChartTitle('')
            setDoughnutChartData(null)
            return
        }

        const selectedHeaderValues = isolateDataRows.map((row) => row.row[selectedHeader]).filter((value) => value !== undefined)

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

        const sliceData = new Map<string, SliceData[]>()

        for (const row of isolateDataRows) {
            const profileId = row.profileId
            parseInt(row.id);
            const nodeSliceData = sliceData.get(profileId)
            const label = row.row[selectedHeader]

            if (nodeSliceData) {
                const slice = nodeSliceData.find((slice) => slice.label === label)

                if (slice) {
                    slice.occurrences += 1
                    continue
                }
            }

            const labelIndex = doughnutChartLabelToLabelIndexHashMap.get(label)!
            let color = doughtnutChartColors[labelIndex]

            if (!color) {
                continue
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