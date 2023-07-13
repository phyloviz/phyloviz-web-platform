import {DistanceMatrix} from "../../../Services/Administration/models/projects/getProject/GetProjectOutputModel";
import {useParams} from "react-router-dom";
import {useProjectContext} from "../useProject";
import * as d3 from "d3";
import {useEffect, useState} from "react";
import VisualizationService from "../../../Services/Visualization/VisualizationService";
import {Problem} from "../../../Services/utils/Problem";
import {MAX_INTEGER} from "../TypingData/useTypingData";

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

    const [error, setError] = useState<string | null>(null)
    const [loading, setLoading] = useState<boolean>(true)

    const distanceMatrixInfo = dataset?.distanceMatrices
        .find(distanceMatrix => distanceMatrix.distanceMatrixId === distanceMatrixId) as DistanceMatrix

    useEffect(() => {
        setLoading(true)

        const data: any = {}
        Promise.all([
                VisualizationService.getTypingDataSchema(projectId, typingDataId!)
                    .then((res) => data.schema = res)
                    .catch((error) => {
                        if (error instanceof Problem && error.title === "Indexing Needed") {
                            setError("Typing Data isn't indexed in the database. Indexing of Typing Data required.")
                        } else {
                            setError("Unknown error during Typing Data fetch")
                        }
                    }),
                VisualizationService.getTypingDataProfiles(projectId, typingDataId!, MAX_INTEGER, 0)
                    .then((res) => data.profiles = res)
                    .catch((error) => {
                        if (error instanceof Problem && error.title === "Indexing Needed") {
                            setError("Typing Data isn't indexed in the database. Indexing of Typing Data required.")
                        } else {
                            setError("Unknown error during Typing Data fetch")
                        }
                    })
            ]
        ).then(() => {
            /**
             * Calculates the distance matrix from the profiles.
             *
             * @returns {ProfilesDistance[]} The distance matrix.
             */
            function calculateDistanceMatrix(): ProfilesDistance[] {
                return data.profiles.profiles.flatMap((profile1: any) => {
                    return data.profiles.profiles.map((profile2: any) => {
                        return {
                            profile1: profile1.id,
                            profile2: profile2.id,
                            distance: profile1.profile.reduce((acc: number, curr: any, index: string | number) => {
                                const locus = data.schema.loci[index]
                                const locusDistance = curr === profile2.profile[index] ? 0 : 1
                                return acc + locusDistance
                            }, 0)
                        }
                    })
                })
            }

            console.log("computing distance matrix")
            const distanceMatrix = calculateDistanceMatrix()
            console.log("distance matrix computed")

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
            const rows = data.profiles.profiles.map((profile: any) => profile.id)
            const columns = data.profiles.profiles.map((profile: any) => profile.id)

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

            // Build color scale
            const maxDistance = Math.max(...distanceMatrix.map((d: ProfilesDistance) => d.distance))
            const myColor = d3.scaleSequential()
                .interpolator(d3.interpolateHslLong("blue", "orange"))// TODO: maybe change colors: see color interpolation
                .domain([0, maxDistance])

            // create a tooltip
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
        })
    }, [distanceMatrixInfo])

    return {
        distanceMatrix: distanceMatrixInfo,
        loading,
        error,
        clearError: () => setError(null)
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