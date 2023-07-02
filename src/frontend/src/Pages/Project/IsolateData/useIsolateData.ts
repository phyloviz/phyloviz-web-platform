import {useParams} from "react-router-dom"
import {useEffect, useState} from "react"
import VisualizationService from "../../../Services/Visualization/VisualizationService"
import {Problem} from "../../../Services/utils/Problem";
import {MAX_INTEGER} from "../TypingData/useTypingData";
import {useProjectContext} from "../useProject";
import {Row} from "../../../Services/Visualization/models/getIsolateDataProfiles/GetIsolateDataRowsOutputModel";

/**
 * Hook for IsolateData page.
 */
export function useIsolateData() {
    const {projectId, isolateDataId} = useParams<{ projectId: string, isolateDataId: string }>()

    const project = useProjectContext().project!

    const dataset = project.datasets.find((dataset) => dataset.isolateDataId === isolateDataId)!

    const [loading, setLoading] = useState<boolean>(true)
    const [error, setError] = useState<string | null>(null)
    const [isolateDataColumns, setIsolateDataColumns] = useState<any[]>([])
    const [isolateDataRows, setIsolateDataRows] = useState<any[]>([])

    useEffect(() => {
        setLoading(true)
        const data: any = {}

        Promise.all([
            VisualizationService.getIsolateDataKeys(projectId!, isolateDataId!)
                .then((res) => data.keys = res)
                .catch((error) => {
                    if (error instanceof Problem && error.title === "Indexing Needed") {
                        setError("Isolate Data isn't indexed in the database. Indexing of Isolate Data required.")
                    } else {
                        setError("Unknown error during Isolate Data fetch")
                    }
                }),
            VisualizationService.getIsolateDataRows(projectId!, isolateDataId!, MAX_INTEGER, 0)
                .then((res) => data.rows = res)
                .catch((error) => {
                    if (error instanceof Problem && error.title === "Indexing Needed") {
                        setError("Isolate Data isn't indexed in the database. Indexing of Isolate Data required.")
                    } else {
                        setError("Unknown error during Isolate Data fetch")
                    }
                })
                .finally(() => setLoading(false))
        ]).then(() => {

            setIsolateDataColumns(data.keys?.keys.map((header: any) => (
                {field: header, headerName: header, width: 100}
            )) ?? [])

            setIsolateDataRows(data.rows?.rows.map((row:Row) => {
                row.row[dataset.isolateDataKey!] = row.profileId

                return (
                    {
                        id: row.id,
                        ...Object.entries(row.row).reduce((acc, curr) => {
                            acc[curr[0]] = <string>curr[1]
                            return acc
                        }, {} as Record<string, string>)
                    }
                )
            }) ?? [])

        })


    }, [projectId, isolateDataId])

    return {
        data: {
            columns: isolateDataColumns,
            rows:   isolateDataRows,
            initialState: {
                rowGrouping: {
                    groupBy: ['commodity'],
                }
            }
        },
        loading,
        error,
        clearError: () => setError(null)
    }
}