import {useEffect, useRef} from "react"

/**
 * Hook that calls a function every interval.
 *
 * @param callback the function to call, if it returns true, the interval is cleared
 * @param delay the delay between calls
 */
export function useInterval(
    callback: () => Promise<void>,
    delay: number
) {
    const savedCallback = useRef<() => Promise<void>>();

    useEffect(() => {
        savedCallback.current = callback;
    }, [callback]);

    useEffect(() => {
        function tick() {
            savedCallback.current!();
        }

        let id = setInterval(tick, delay);
        return () => clearInterval(id);
    }, []);
}