import {useNavigate} from "react-router-dom"
import * as React from "react";
import {useState} from "react";
import {SelectChangeEvent} from "@mui/material";

/**
 * Hook for the NLVGraphConfig page.
 */
export function useNLVGraphConfig() {
    const navigate = useNavigate()

    const [selectedDistance, setSelectedDistance] = useState<string | null>(null)
    const [currentMaxNLVLevel, setCurrentMaxNLVLevel] = useState<number>(1)
    const [innerEdges, setInnerEdges] = useState<boolean>(false)

    return {
        distances: [], // TODO: To be implemented
        selectedDistance,
        handleDistanceChange: (event: SelectChangeEvent) => setSelectedDistance(event.target.value),

        currentMaxNLVLevel,
        handleMaxNLVLevelChange: (event: React.ChangeEvent<HTMLInputElement>) => setCurrentMaxNLVLevel(parseInt(event.target.value)),

        innerEdges,
        handleInnerEdgesChange: (event: React.ChangeEvent<HTMLInputElement>) => setInnerEdges(event.target.checked),

        handleCancel: () => navigate(-1),
        handleFinish: () => {
            // TODO:finish
        }
    }
}