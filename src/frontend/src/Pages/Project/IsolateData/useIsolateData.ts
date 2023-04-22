import {useParams} from "react-router-dom"
import {useEffect, useState} from "react"
import VisualizationService from "../../../Services/Visualization/VisualizationService"
import {
    GetIsolateDataSchemaOutputModel
} from "../../../Services/Visualization/models/getIsolateDataSchema/GetIsolateDataSchemaOutputModel"
import {
    GetIsolateDataRowsOutputModel
} from "../../../Services/Visualization/models/getIsolateDataProfiles/GetIsolateDataRowsOutputModel"

/**
 * Hook for IsolateData page.
 */
export function useIsolateData() {
    const {projectId, typingDataId} = useParams<{
        projectId: string,
        typingDataId: string
    }>()

    const [isolateDataSchema, setIsolateDataSchema] = useState<GetIsolateDataSchemaOutputModel>()
    const [isolateDataRows, setIsolateDataRows] = useState<GetIsolateDataRowsOutputModel>()
    const [loading, setLoading] = useState<boolean>(true)
    const [error, setError] = useState<string | null>(null)

    useEffect(() => {
        setLoading(true)
        VisualizationService.getIsolateDataSchema(projectId!, typingDataId!)
            .then((res) => setIsolateDataSchema(res))
            .catch((err) => setError(err))

        VisualizationService.getIsolateDataRows(projectId!, typingDataId!)
            .then((res) => setIsolateDataRows(res))
            .catch((err) => setError(err))
            .finally(() => setLoading(false))
    }, [projectId, typingDataId])

    return {
        data: {
            columns: isolateDataSchema?.headers.map((header) => (
                {field: header, headerName: header, width: 100}
            )) ?? [],
            rows: isolateDataRows?.rows.map((row) => (
                {
                    id: row.id,
                    ...row.row.reduce((acc, curr, index) => {
                        acc[isolateDataSchema!.headers[index]] = curr
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