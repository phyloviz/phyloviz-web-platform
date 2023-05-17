import {useParams} from "react-router-dom"
import {useEffect, useState} from "react"
import VisualizationService from "../../../Services/Visualization/VisualizationService"
import {
    GetIsolateDataKeysOutputModel
} from "../../../Services/Visualization/models/getIsolateDataSchema/GetIsolateDataKeysOutputModel"
import {
    GetIsolateDataRowsOutputModel
} from "../../../Services/Visualization/models/getIsolateDataProfiles/GetIsolateDataRowsOutputModel"

/**
 * Hook for IsolateData page.
 */
export function useIsolateData() {
    const {projectId, isolateDataId} = useParams<{ projectId: string, isolateDataId: string }>()

    const [isolateDataSchema, setIsolateDataSchema] = useState<GetIsolateDataKeysOutputModel>()
    const [isolateDataRows, setIsolateDataRows] = useState<GetIsolateDataRowsOutputModel>()
    const [loading, setLoading] = useState<boolean>(true)
    const [error, setError] = useState<string | null>(null)

    useEffect(() => {
        setLoading(true)
        VisualizationService.getIsolateDataKeys(projectId!, isolateDataId!)
            .then((res) => setIsolateDataSchema(res))
            .catch((err) => setError(err))

        VisualizationService.getIsolateDataRows(projectId!, isolateDataId!)
            .then((res) => setIsolateDataRows(res))
            .catch((err) => setError(err))
            .finally(() => setLoading(false))
    }, [projectId, isolateDataId])

    return {
        data: {
            columns: isolateDataSchema?.keys.map((header) => (
                {field: header, headerName: header, width: 100}
            )) ?? [],
            rows: isolateDataRows?.rows.map((row) => (
                {
                    id: row.id,
                    ...Object.entries(row.row).reduce((acc, curr) => {
                        acc[curr[0]] = curr[1]
                        return acc
                    }, {} as Record<string, string>)
                }
            )) ?? [],
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