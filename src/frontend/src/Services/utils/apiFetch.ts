import {Problem, problemMediaType} from "./Problem"

/**
 * Fetches a resource from the web API.
 *
 * @param input the URL to fetch
 * @param method the HTTP method to use
 * @param body the body of the request
 * @param headers the headers of the request
 *
 * @returns the response body
 */
async function apiFetch<T>(
    input: RequestInfo | URL,
    method?: string,
    body?: BodyInit,
    headers?: HeadersInit,
): Promise<T> {
    const [err, res] = await to<Response>(fetch(input, {
        method,
        headers: headers ? headers : {
            'Content-Type': 'application/json',
        },
        body
    }))

    if (err)
        throw new NetworkError(err.message)

    if (!res.ok) {
        if (res?.headers.get('Content-Type') !== problemMediaType)
            throw new UnexpectedResponseError(`Unexpected response type: ${res.headers.get('Content-Type')}`)

        throw new Problem(await res.json())
    }
    if (res?.headers.get('Content-Type') !== 'application/json')
        throw new UnexpectedResponseError(`Unexpected response type: ${res.headers.get('Content-Type')}`)


    return await res.json()
}

/**
 * Returns a promise of an array of two elements where the first element
 * is the error and the second element is the result.
 *
 * @param promise the promise to "convert"
 * @returns the resulting promise of an array
 */
function to<T, E = Error>(promise: Promise<T>): Promise<[E, null] | [null, T]> {
    return promise
        .then<[null, T]>(data => [null, data])
        .catch<[E, null]>(err => [err, null])
}

/**
 * An error that occurs if there is a network error.
 */
export class NetworkError extends Error {
    constructor(message: string) {
        super(message)
    }
}

/**
 * An error that occurs if the response is not a Siren entity.
 */
export class UnexpectedResponseError extends Error {
    constructor(message: string) {
        super(message)
    }
}

/**
 * Sends a GET request to the web API.
 *
 * @param input the URL to fetch
 * @returns the response body
 */
export async function get<T>(input: RequestInfo | URL): Promise<T> {
    return await apiFetch<T>(input, 'GET')
}

/**
 * Sends a POST request to the web API.
 *
 * @param input the URL to fetch
 * @param headers the headers of the request
 * @param body the body of the request
 */
export async function post<T>(input: RequestInfo | URL, body: BodyInit, headers?: HeadersInit): Promise<T> {
    return await apiFetch<T>(input, 'POST', body, headers)
}

/**
 * Sends a DELETE request to the web API.
 *
 * @param input the URL to fetch
 * @param body the body of the request
 */
export async function del<T>(input: RequestInfo | URL, body?: BodyInit): Promise<T> {
    return await apiFetch<T>(input, 'DELETE', body)
}