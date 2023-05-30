import {useEffect, useState} from "react";
import VisualizationService from "../../../../../../Services/Visualization/VisualizationService";

/**
 * This hook is used to get the headers of the isolate data.
 *
 * @param projectId The project id.
 * @param isolateDataId The isolate data id.
 */
export function useIsolateDataHeaders(projectId: string, isolateDataId: string | null | undefined) {
    const [isolateDataHeaders, setIsolateDataHeaders] = useState<string[]>([])

    useEffect(() => {
        if (isolateDataId) {
            VisualizationService.getIsolateDataRows(projectId, isolateDataId, 1, 0)
                .then(response => {
                    if (response && response.rows.length > 0) {
                        VisualizationService.getIsolateDataKeys(projectId, isolateDataId)
                            .then(response => {
                                setIsolateDataHeaders(response.keys)
                            })
                    }
                })
                .catch(() => {
                    return undefined
                })
        }
    }, [isolateDataId])

    return {
        isolateDataHeaders
    }
}