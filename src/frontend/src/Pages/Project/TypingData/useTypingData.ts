import {useParams} from "react-router-dom"
import VisualizationService from "../../../Services/Visualization/VisualizationService"
import {useEffect, useState} from "react"
import {
    GetTypingDataProfilesOutputModel
} from "../../../Services/Visualization/models/getTypingDataProfiles/GetTypingDataProfilesOutputModel"
import {
    GetTypingDataSchemaOutputModel
} from "../../../Services/Visualization/models/getTypingDataSchema/GetTypingDataSchemaOutputModel"

/**
 * Hook for typing data page.
 */
export function useTypingData() {
    const {projectId, typingDataId} = useParams<{
        projectId: string,
        typingDataId: string
    }>()

    const [typingDataSchema, setTypingDataSchema] = useState<GetTypingDataSchemaOutputModel>()
    const [typingDataProfiles, setTypingDataProfiles] = useState<GetTypingDataProfilesOutputModel>()
    const [loading, setLoading] = useState<boolean>(true)
    const [error, setError] = useState<string | null>(null)

    useEffect(() => {
        setLoading(true)
        VisualizationService.getTypingDataSchema(projectId!, typingDataId!)
            .then((res) => setTypingDataSchema(res))
            .catch((err) => setError(err))

        VisualizationService.getTypingDataProfiles(projectId!, typingDataId!)
            .then((res) => setTypingDataProfiles(res))
            .catch((err) => setError(err))
            .finally(() => setLoading(false))
    }, [projectId, typingDataId])

    return {
        data: {
            columns: typingDataSchema?.loci.map((locus) => (
                {field: locus, headerName: locus, width: 100}
            )) ?? [],
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
        error,
        clearError: () => setError(null)
    }
}