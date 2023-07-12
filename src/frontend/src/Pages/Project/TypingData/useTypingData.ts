import {useParams} from "react-router-dom"
import VisualizationService from "../../../Services/Visualization/VisualizationService"
import {useEffect, useState} from "react"
import {Problem} from "../../../Services/utils/Problem";

export const MAX_INTEGER = 2147483646

/**
 * Hook for typing data page.
 */
export function useTypingData() {
    const {projectId, typingDataId} = useParams<{ projectId: string, typingDataId: string }>()

    const [typingDataSchema, setTypingDataSchema] = useState<any>()
    const [typingDataProfiles, setTypingDataProfiles] = useState<any>()
    const [loading, setLoading] = useState<boolean>(true)
    const [error, setError] = useState<string | null>(null)

    useEffect(() => {
        setLoading(true)

        const data: any = {}
        Promise.all([
            VisualizationService.getTypingDataSchema(projectId!, typingDataId!)
                .then((res) => data.schema = res)
                .catch((error) => {
                    if (error instanceof Problem && error.title === "Indexing Needed") {
                        setError("Typing Data isn't indexed in the database. Indexing of Typing Data required.")
                    } else {
                        setError("Unknown error during Typing Data fetch")
                    }
                }),
            VisualizationService.getTypingDataProfiles(projectId!, typingDataId!, MAX_INTEGER, 0)
                .then((res) => data.profiles = res)
                .catch((error) => {
                    if (error instanceof Problem && error.title === "Indexing Needed") {
                        setError("Typing Data isn't indexed in the database. Indexing of Typing Data required.")
                    } else {
                        setError("Unknown error during Typing Data fetch")
                    }
                })
        ])
            .then(() => {
                const columns = (data.schema?.loci.map((locus: any) => (
                    {field: locus, headerName: locus, width: 100}
                )) ?? [])

                columns.unshift({field: 'ST', headerName: 'ST', width: 100})

                setTypingDataSchema(columns)

                setTypingDataProfiles(data.profiles.profiles.map((profile: any) => (
                    {
                        id: profile.id,
                        ST: profile.id,
                        ...profile.profile.reduce((acc: { [x: string]: any }, curr: any, index: string | number) => {
                            acc[data.schema.loci[index]] = curr
                            return acc
                        }, {} as Record<string, string>)
                    }
                )))

            })
            .finally(() => setLoading(false))
    }, [projectId, typingDataId])


    return {
        data: {
            columns: typingDataSchema ? typingDataSchema : [],
            rows: typingDataProfiles ? typingDataProfiles : [],
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