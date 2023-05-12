import {useNavigate, useParams} from "react-router-dom"
import {useEffect, useState} from "react"
import VisualizationService from "../../../../Services/Visualization/VisualizationService"
import {
    GetIsolateDataRowsOutputModel
} from "../../../../Services/Visualization/models/getIsolateDataProfiles/GetIsolateDataRowsOutputModel";
import {
    GetIsolateDataSchemaOutputModel
} from "../../../../Services/Visualization/models/getIsolateDataSchema/GetIsolateDataSchemaOutputModel";
import {useTreeViewContext} from "../useTreeView";

/**
 * Hook for IsolateData page.
 */
export function useIsolateDataFilter() {
    const {projectId} = useParams<{ projectId: string }>()
    const {isolateDataId} = useTreeViewContext()

    const [isolateDataSchema, setIsolateDataSchema] = useState<GetIsolateDataSchemaOutputModel>()
    const [isolateDataRows, setIsolateDataRows] = useState<GetIsolateDataRowsOutputModel>()
    const [loading, setLoading] = useState<boolean>(true)
    const [error, setError] = useState<string | null>(null)
    const navigate = useNavigate()

    useEffect(() => {
        setLoading(true)
        VisualizationService.getIsolateDataSchema(projectId!, isolateDataId!)
            .then((res) => setIsolateDataSchema(res))
            .catch((err) => setError(err))

        VisualizationService.getIsolateDataRows(projectId!, isolateDataId!)
            .then((res) => setIsolateDataRows(res))
            .catch((err) => setError(err))
            .finally(() => setLoading(false))
    }, [projectId, isolateDataId])

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

        handleClearFilter: () => {
            // TODO: Clear filter
            navigate(-1)
        },
        handleApplyFilter: () => {
            // TODO: Apply filter
            navigate(-1)
        },

        error,
        clearError: () => setError(null)
    }
}