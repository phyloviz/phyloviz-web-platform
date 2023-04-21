import {useEffect} from "react"

/**
 * Hook that calls a function after a delay.
 *
 * @param callback the function to call
 * @param delay the delay
 * @param dependencies the dependencies of the hook
 */
export function useTimeout(callback: () => void, delay: number, dependencies: any[]) {
    useEffect(activateTimeout, dependencies)

    /**
     * Activates the timeout.
     */
    function activateTimeout() {
        const timeoutId = setTimeout(callback, delay)

        return () => {
            clearTimeout(timeoutId)
        }
    }
}