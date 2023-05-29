import {useEffect, useState} from "react";
import VisualizationService from "../../../Services/Visualization/VisualizationService";
import {Row} from "../../../Services/Visualization/models/getIsolateDataProfiles/GetIsolateDataRowsOutputModel";

export function useIsolateDataRows(projectId: string, isolateDataId: string | null | undefined) {
    const [isolateDataRows, setIsolateDataRows] = useState<Row[]>([])

    useEffect(() => {
        if (!isolateDataId)
            return

        async function getIsolateDataRows() {
            const isolateData = await VisualizationService.getIsolateDataRows(projectId!, isolateDataId!, 100000, 0) // TODO is pagination needed?
            setIsolateDataRows(isolateData.rows)
        }

        getIsolateDataRows()
    }, [isolateDataId])

    return {
        isolateDataRows
    }
}