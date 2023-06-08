import {useParams} from "react-router-dom"
import VisualizationService from "../../../Services/Visualization/VisualizationService"
import {useEffect, useState} from "react"
import {
    GetTypingDataProfilesOutputModel
} from "../../../Services/Visualization/models/getTypingDataProfiles/GetTypingDataProfilesOutputModel"
import {
    GetTypingDataSchemaOutputModel
} from "../../../Services/Visualization/models/getTypingDataSchema/GetTypingDataSchemaOutputModel"
import {Problem} from "../../../Services/utils/Problem";

export const MAX_INTEGER = 2147483646

/**
 * Hook for typing data page.
 */
export function useTypingData() {
    const {projectId, typingDataId} = useParams<{ projectId: string, typingDataId: string }>()

    const [typingDataSchema, setTypingDataSchema] = useState<GetTypingDataSchemaOutputModel>()
    const [typingDataProfiles, setTypingDataProfiles] = useState<GetTypingDataProfilesOutputModel>()
    const [loading, setLoading] = useState<boolean>(true)
    const [error, setError] = useState<string | null>(null)

    useEffect(() => {
        setLoading(true)

        Promise.all([
            VisualizationService.getTypingDataSchema(projectId!, typingDataId!)
                .then((res) => setTypingDataSchema(res))
                .catch((error) => {
                    if (error instanceof Problem && error.title === "Indexing Needed") {
                        setError("Typing Data isn't indexed in the database. Indexing of Typing Data required.")
                    } else {
                        setError("Unknown error during Typing Data fetch")
                    }
                }),
            VisualizationService.getTypingDataProfiles(projectId!, typingDataId!, MAX_INTEGER, 0)
                .then((res) => setTypingDataProfiles(res))
                .catch((error) => {
                    if (error instanceof Problem && error.title === "Indexing Needed") {
                        setError("Typing Data isn't indexed in the database. Indexing of Typing Data required.")
                    } else {
                        setError("Unknown error during Typing Data fetch")
                    }
                })
        ]).finally(() => setLoading(false))
    }, [projectId, typingDataId])

    const columns = (typingDataSchema?.loci.map((locus) => (
        {field: locus, headerName: locus, width: 100}
    )) ?? [])

    if (typingDataSchema) {
        columns.unshift({field: 'ST', headerName: 'ST', width: 100})
    }

    return {
        data: {
            columns,
            rows: typingDataSchema && typingDataProfiles ? typingDataProfiles.profiles.map((profile) => (
                {
                    id: profile.id,
                    ST: profile.id,
                    ...profile.profile.reduce((acc, curr, index) => {
                        acc[typingDataSchema.loci[index]] = curr
                        return acc
                    }, {} as Record<string, string>)
                }
            )) : [],
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