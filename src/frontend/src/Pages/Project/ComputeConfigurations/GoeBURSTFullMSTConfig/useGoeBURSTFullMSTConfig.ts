import {useNavigate} from "react-router-dom"

/**
 * Hook for the GoeBURSTFullMSTConfig page.
 */
export function useGoeBURSTFullMSTConfig() {
    const navigate = useNavigate()

    return {
        handleCancel: () => navigate(-1),
        handleFinish: () => {
            // TODO:finish
        }
    }
}