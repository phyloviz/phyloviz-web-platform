import {useEffect} from "react"

/**
 * Hook that calls a function every interval.
 *
 * @param callback the function to call, if it returns true, the interval is cleared
 * @param delay the delay between calls
 * @param dependencies the dependencies of the hook
 */
export function useInterval(
    callback: () => Promise<boolean> | boolean | void,
    delay: number,
    dependencies?: any[]
) {
    useEffect(activateInterval, dependencies)

    /**
     * Activates the interval.
     */
    function activateInterval() {
        let cancelled = false
        let timeoutId: NodeJS.Timeout | undefined = undefined

        /**
         * Calls the callback and schedules the next call.
         */
        async function tick() {
            const shouldStop = await callback()

            if (!cancelled && !shouldStop)
                timeoutId = setTimeout(tick, delay)
        }

        tick()

        return () => {
            cancelled = true
            if (timeoutId !== undefined)
                clearTimeout(timeoutId)
        }
    }
}