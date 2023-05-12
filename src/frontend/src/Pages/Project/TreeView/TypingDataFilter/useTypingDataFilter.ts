import {useNavigate, useParams} from "react-router-dom"
import {useEffect, useState} from "react"
import VisualizationService from "../../../../Services/Visualization/VisualizationService"
import {
    GetTypingDataSchemaOutputModel
} from "../../../../Services/Visualization/models/getTypingDataSchema/GetTypingDataSchemaOutputModel";
import {
    GetTypingDataProfilesOutputModel
} from "../../../../Services/Visualization/models/getTypingDataProfiles/GetTypingDataProfilesOutputModel";
import {useTreeViewContext} from "../useTreeView";

/**
 * Hook for typing data filter page.
 */
export function useTypingDataFilter() {
    const {projectId} = useParams<{ projectId: string }>()
    const {typingDataId} = useTreeViewContext()

    const [typingDataSchema, setTypingDataSchema] = useState<GetTypingDataSchemaOutputModel>()
    const [typingDataProfiles, setTypingDataProfiles] = useState<GetTypingDataProfilesOutputModel>()
    const [loading, setLoading] = useState<boolean>(true)
    const [error, setError] = useState<string | null>(null)
    const navigate = useNavigate()

    useEffect(() => {
        setLoading(true)
        VisualizationService.getTypingDataSchema(projectId!, typingDataId)
            .then((res) => setTypingDataSchema(res))
            .catch((err) => setError(err))

        VisualizationService.getTypingDataProfiles(projectId!, typingDataId)
            .then((res) => setTypingDataProfiles(res))
            .catch((err) => setError(err))
            .finally(() => setLoading(false))
    }, [projectId, typingDataId])

    const columns = (typingDataSchema?.loci.map((locus) => (
        {field: locus, headerName: locus, width: 100}
    )) ?? [])
    columns.unshift({field: 'ST', headerName: 'ST', width: 100})

    return {
        data: {
            columns,
            rows: typingDataProfiles?.profiles.map((profile) => (
                {
                    id: profile.id,
                    ...profile.profile.reduce((acc, curr, index) => {
                        acc[typingDataSchema!.loci[index]] = curr
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