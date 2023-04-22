import {useParams} from "react-router-dom"
import {useEffect, useState} from "react"
import {Dataset} from "../../../Services/administration/models/getProject/GetProjectOutputModel"
import AdministrationService from "../../../Services/administration/AdministrationService"

/**
 * Hook for the DatasetDetails page.
 */
export function useDatasetDetails() {
    const {projectId, datasetId} = useParams<{ projectId: string, datasetId: string }>()
    const [dataset, setDataset] = useState<Dataset>()
    const [loading, setLoading] = useState<boolean>(true)
    const [error, setError] = useState<string | null>(null)

    useEffect(() => {
        if (projectId === undefined)
            throw new Error("Project id is undefined")
        if (datasetId === undefined)
            throw new Error("Dataset id is undefined")

        setLoading(true)
        AdministrationService.getDataset(projectId, datasetId)
            .then((res) => {
                setDataset(res)
                setLoading(false)
            })
            .catch((err) => {
                setError(err)
                setLoading(false)
            })
    }, [projectId, datasetId])

    return {
        dataset,
        loading,
        error,
        clearError: () => setError(null)
    }
}