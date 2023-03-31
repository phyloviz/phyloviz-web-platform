import {useNavigate} from "react-router-dom";

/**
 * Hook for the NLVGraphConfig page.
 */
export function useNLVGraphConfig() {
    const navigate = useNavigate();

    return {
        handleCancel: () => navigate(-1),
        handleFinish: () => {
            // TODO:finish
        }
    }
}