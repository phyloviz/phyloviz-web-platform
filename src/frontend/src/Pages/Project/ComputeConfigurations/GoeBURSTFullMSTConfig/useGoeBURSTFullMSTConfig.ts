import {useNavigate} from "react-router-dom"
import {useState} from "react";
import {SelectChangeEvent} from "@mui/material";

/**
 * Hook for the GoeBURSTFullMSTConfig page.
 */
export function useGoeBURSTFullMSTConfig() {
    const navigate = useNavigate()

    const [selectedDistance, setSelectedDistance] = useState<string | null>(null)

    return {
        distances: [], // TODO: To be implemented
        selectedDistance,
        handleDistanceChange: (event: SelectChangeEvent) => setSelectedDistance(event.target.value),

        handleCancel: () => navigate(-1),
        handleFinish: () => {
            // TODO:finish
        }
    }
}