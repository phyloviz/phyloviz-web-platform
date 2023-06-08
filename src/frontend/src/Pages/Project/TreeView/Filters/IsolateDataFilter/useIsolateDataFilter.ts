import {useNavigate, useParams} from "react-router-dom"
import {useEffect, useState} from "react"
import VisualizationService from "../../../../../Services/Visualization/VisualizationService"
import {
    GetIsolateDataRowsOutputModel
} from "../../../../../Services/Visualization/models/getIsolateDataProfiles/GetIsolateDataRowsOutputModel";
import {
    GetIsolateDataKeysOutputModel
} from "../../../../../Services/Visualization/models/getIsolateDataSchema/GetIsolateDataKeysOutputModel";
import {useTreeViewContext} from "../../useTreeView";
import {Problem} from "../../../../../Services/utils/Problem";
import {MAX_INTEGER} from "../../../TypingData/useTypingData";

/**
 * Hook for IsolateData page.
 */
export function useIsolateDataFilter() {
    const {projectId} = useParams<{ projectId: string }>()
    const {isolateDataId} = useTreeViewContext()

    const [isolateDataSchema, setIsolateDataSchema] = useState<GetIsolateDataKeysOutputModel>()
    const [isolateDataRows, setIsolateDataRows] = useState<GetIsolateDataRowsOutputModel>()
    const [loading, setLoading] = useState<boolean>(true)
    const [error, setError] = useState<string | null>(null)
    const navigate = useNavigate()

    useEffect(() => {
        setLoading(true)
        VisualizationService.getIsolateDataKeys(projectId!, isolateDataId!)
            .then((res) => setIsolateDataSchema(res))
            .catch((error) => {
                if (error instanceof Problem && error.title === "Indexing Needed") {
                    setError("Isolate Data isn't indexed in the database. Indexing of Isolate Data required.")
                } else {
                    setError("Unknown error during Isolate Data fetch")
                }
            })

        VisualizationService.getIsolateDataRows(projectId!, isolateDataId!, MAX_INTEGER, 0)
            .then((res) => setIsolateDataRows(res))
            .catch((error) => {
                if (error instanceof Problem && error.title === "Indexing Needed") {
                    setError("Isolate Data isn't indexed in the database. Indexing of Isolate Data required.")
                } else {
                    setError("Unknown error during Isolate Data fetch")
                }
            })
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