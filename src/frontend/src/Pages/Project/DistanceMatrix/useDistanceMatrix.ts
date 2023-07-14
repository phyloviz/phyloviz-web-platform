import {DistanceMatrix} from "../../../Services/Administration/models/projects/getProject/GetProjectOutputModel";
import {useParams} from "react-router-dom";
import {useProjectContext} from "../useProject";
import * as d3 from "d3";
import {useEffect, useState} from "react";
import VisualizationService from "../../../Services/Visualization/VisualizationService";
import {Problem} from "../../../Services/utils/Problem";
import {MAX_INTEGER} from "../TypingData/useTypingData";
import {SelectChangeEvent} from "@mui/material";
import {Profile} from "../../../Services/Visualization/models/getTypingDataProfiles/GetTypingDataProfilesOutputModel";
import {
    GetTypingDataSchemaOutputModel
} from "../../../Services/Visualization/models/getTypingDataSchema/GetTypingDataSchemaOutputModel";
import {
    GetIsolateDataRowsOutputModel
} from "../../../Services/Visualization/models/getIsolateDataProfiles/GetIsolateDataRowsOutputModel";

/**
 * Hook for DistanceMatrix page.
 */
export function useDistanceMatrix() {
    const pathParams = useParams<{ projectId: string, datasetId: string, distanceMatrixId: string }>()
    const projectId = pathParams.projectId!
    const datasetId = pathParams.datasetId!
    const distanceMatrixId = pathParams.distanceMatrixId!

    const {project} = useProjectContext()
    const dataset = project?.datasets.find(dataset => dataset.datasetId === datasetId)
    const typingDataId = dataset?.typingDataId
    const isolateDataId = dataset?.isolateDataId

    const [error, setError] = useState<string | null>(null)
    const [loading, setLoading] = useState<boolean>(true)

    const distanceMatrixInfo = dataset?.distanceMatrices
        .find(distanceMatrix => distanceMatrix.distanceMatrixId === distanceMatrixId) as DistanceMatrix

    const [selectedOrder, setSelectedOrder] = useState<string | null>(null)
    const [distanceMatrix, setDistanceMatrix] = useState<ProfilesDistance[] | null>(null)
    const isolateDataKeys = project?.files.isolateData.find(file => file.isolateDataId === dataset?.isolateDataId)?.keys

    const [isolateDataRows, setIsolateDataRows] = useState<GetIsolateDataRowsOutputModel | null>(null)
    const [profiles, setProfiles] = useState<Profile[] | null>(null)
    const [typingDataSchema, setTypingDataSchema] = useState<GetTypingDataSchemaOutputModel | null>(null)

    useEffect(() => {
        setLoading(true)
        Promise.all([
            VisualizationService.getTypingDataSchema(projectId, typingDataId!)
                .then((res) => setTypingDataSchema(res))
                .catch((error) => {
                    if (error instanceof Problem && error.title === "Indexing Needed") {
                        setError("Typing Data isn't indexed in the database. Indexing of Typing Data required.")
                    } else {
                        setError("Unknown error during Typing Data fetch")
                    }
                }),
            VisualizationService.getTypingDataProfiles(projectId, typingDataId!, MAX_INTEGER, 0)
                .then((res) => setProfiles(res.profiles))
                .catch((error) => {
                    console.log(error)
                    if (error instanceof Problem && error.title === "Indexing Needed") {
                        setError("Typing Data isn't indexed in the database. Indexing of Typing Data required.")
                    } else {
                        setError("Unknown error during Typing Data fetch")
                    }
                }),
            VisualizationService.getIsolateDataRows(projectId, isolateDataId!, MAX_INTEGER, 0)
                .then((res) => setIsolateDataRows(res))
                .catch((error) => {
                    if (error instanceof Problem && error.title === "Indexing Needed") {
                        setError("Isolate Data isn't indexed in the database. Indexing of Isolate Data required.")
                    } else {
                        setError("Unknown error during Isolate Data fetch")
                    }
                })
        ]).finally(() => setLoading(false))
    }, [projectId, typingDataId, isolateDataId])

    useEffect(() => {
        if (!profiles || !typingDataSchema) return

        /**
         * Calculates the distance matrix from the profiles.
         *
         * @returns {ProfilesDistance[]} The distance matrix.
         */
        function calculateDistanceMatrix(): ProfilesDistance[] {
            return profiles!.flatMap((profile1: any) => {
                return profiles!.map((profile2: any) => {
                    return {
                        profile1: profile1.id,
                        profile2: profile2.id,
                        distance: profile1.profile.reduce((acc: number, curr: any, index: string | number) => {
                            const locus = typingDataSchema!.loci[index]
                            const locusDistance = curr === profile2.profile[index] ? 0 : 1
                            return acc + locusDistance
                        }, 0)
                    }
                })
            })
        }

        console.log("computing distance matrix")
        const distanceMatrix = calculateDistanceMatrix()
        setDistanceMatrix(distanceMatrix)
        console.log("distance matrix computed")

    }, [typingDataSchema])

    useEffect(() => {
        if (!profiles || !distanceMatrix) return

        console.log("rendering heatmap")

        const width = 550
        const height = 550
        // Append the svg object to the body of the page
        const svg = d3.select("#heatmap")
            .append("svg")
            .attr("width", width)
            .attr("height", height)
            .append("g")

        // Labels of row and columns
        // Each row and column is a profile, and each cell is a distance between two profiles
        const rows = profiles.map((profile: any) => profile.id)
        const columns = profiles.map((profile: any) => profile.id)

        // Build X scales and axis:
        const x = d3.scaleBand()
            .range([0, width])
            // @ts-ignore
            .domain(rows)
            .padding(0.05);

        // Build Y scales and axis:
        const y = d3.scaleBand()
            .range([0, height])
            // @ts-ignore
            .domain(columns)
            .padding(0.05);

        // ----------------------------------------------------------------------------------------------
        // Color Scale
        // ----------------------------------------------------------------------------------------------

        const maxDistance = Math.max(...distanceMatrix.map((d: ProfilesDistance) => d.distance))
        const myColor = d3.scaleSequential()
            .interpolator(d3.interpolateHslLong("blue", "orange"))
            .domain([0, maxDistance])

        const colorScaleWidth = 40
        const colorScaleHeight = 200
        const colorScale = d3.select("#colorscale")
            .append("svg")
            .attr("width", colorScaleWidth)
            .attr("height", colorScaleHeight + 20)

        const gradient = colorScale.append("defs")
            .append("linearGradient")
            .attr("id", "gradient")
            .attr("x1", "0%")
            .attr("y1", "100%")
            .attr("x2", "0%")
            .attr("y2", "0%")

        for (let i = maxDistance; i >= 0; i--) {
            gradient.append("stop")
                .attr("offset", `${(maxDistance - i) / maxDistance * 100}%`)
                .attr("stop-color", myColor(i))
        }

        colorScale
            .append("rect")
            .attr("width", colorScaleWidth / 2)
            .attr("height", colorScaleHeight)
            .style("fill", "url(#gradient)")
            .attr("transform", "translate(0,5)")

        // add labels to color scale
        const colorAxis = d3.axisRight(d3.scaleLinear()
            .domain([0, maxDistance])
            .range([0, colorScaleHeight]))
            .tickValues(d3.range(0, maxDistance + 1, 1))
            .tickFormat(d3.format("d")) // integer format
        colorScale.append("g")
            .attr("transform", "translate(20,5)")
            .call(colorAxis)

        // ----------------------------------------------------------------------------------------------

        // Create a tooltip
        const tooltip = d3.select("#heatmap")
            .append("div")
            .style("opacity", 0)
            .attr("class", "tooltip")
            .style("position", "absolute")
            .style("background-color", "white")
            .style("border", "solid")
            .style("border-width", "2px")
            .style("border-radius", "5px")
            .style("padding", "5px")

        // Three function that change the tooltip when user hover / move / leave a cell
        const mouseover = function () {
            tooltip
                .style("opacity", 1)
            // @ts-ignore
            d3.select(this)
                .style("stroke", "black")
                .style("opacity", 1)
        }
        // @ts-ignore
        const mousemove = function (event, d) {
            tooltip
                .html("Distance between " + d.profile1 + " and " + d.profile2 + " is " + d.distance)
                .style("left", (event.x) / 2 + "px")
                .style("top", (event.y) / 2 + "px")
        }
        // @ts-ignore
        const mouseleave = function (d) {
            tooltip.style("opacity", 0)
            // @ts-ignore
            d3.select(this)
                .style("stroke", "none")
                .style("opacity", 0.8)
        }

        // add the squares
        svg.selectAll()
            // @ts-ignore
            .data(distanceMatrix, (d) => d.profile1 + ':' + d.profile2)
            .join("rect")
            .attr("class", "cell")
            // @ts-ignore
            .attr("x", (d) => x(d.profile1!))
            // @ts-ignore
            .attr("y", (d) => y(d.profile2!))
            .attr("rx", 4)
            .attr("ry", 4)
            .attr("width", x.bandwidth())
            .attr("height", y.bandwidth())
            // @ts-ignore
            .style("fill", (d) => myColor(d.distance))
            .style("stroke-width", 4)
            .style("stroke", "none")
            .style("opacity", 0.8)
            .on("mouseover", mouseover)
            .on("mousemove", mousemove)
            .on("mouseleave", mouseleave)

        setLoading(false)
    }, [distanceMatrix])

    useEffect(() => {
        if (!profiles) return

        console.log("sorting")
        // @ts-ignore
        const newProfiles = profiles.toSorted((a: Profile, b: Profile) => {
            if (!selectedOrder)
                return 0

            const profile1Row = isolateDataRows?.rows.find((row: any) => row.profileId === a.id)?.row
            const profile2Row = isolateDataRows?.rows.find((row: any) => row.profileId === b.id)?.row
            if (profile1Row === undefined || profile2Row === undefined)
                return 0

            const profile1 = profile1Row[selectedOrder]
            const profile2 = profile2Row[selectedOrder]
            if (profile1 === undefined || profile2 === undefined)
                return 0

            return profile1.localeCompare(profile2)
        })
        setProfiles(newProfiles)
        console.log("sorted")

        // Labels of row and columns
        // Each row and column is a profile, and each cell is a distance between two profiles
        const rows = profiles.map((profile: any) => profile.id)
        const columns = profiles.map((profile: any) => profile.id)

        const width = 550
        const height = 550
        // Build X scales and axis:
        const x = d3.scaleBand()
            .range([0, width])
            // @ts-ignore
            .domain(rows)
            .padding(0.05);

        // Build Y scales and axis:
        const y = d3.scaleBand()
            .range([0, height])
            // @ts-ignore
            .domain(columns)
            .padding(0.05);

        const svg = d3.select("#heatmap")
        const t = svg.transition().duration(1000);

        t.selectAll(".cell")
            // @ts-ignore
            .delay(d => y(d.profile2) * 4)// @ts-ignore
            .attr("x", (d) => x(d.profile1!)!)
            // @ts-ignore
            .attr("y", (d) => y(d.profile2!)!)

        console.log(profiles)
        console.log("done")
    }, [selectedOrder])

    return {
        distanceMatrix: distanceMatrixInfo,
        loading,
        error,
        clearError: () => setError(null),
        selectedOrder,
        onOrderChange: (event: SelectChangeEvent) => setSelectedOrder(event.target.value),
        orderOptions: isolateDataKeys ?? []
    }
}

/**
 * The distance between two profiles.
 *
 * @property profile1 The first profile.
 * @property profile2 The second profile.
 * @property distance The distance between the two profiles.
 */
interface ProfilesDistance {
    profile1: string,
    profile2: string,
    distance: number
}