import {useContext} from "react"
import {SessionManagerContext} from "./AuthProvider"

/**
 * Holds the session data.
 *
 * @property name the username of the user
 * @property picture the URL of the user's profile picture
 * @property email the email of the user
 */
export interface Session {
    readonly name: string
    readonly picture: string
    readonly email: string
}

/**
 * The manager for the session.
 *
 * @property session the session data
 * @property setSession sets the session data
 * @property clearSession clears the session data
 */
export interface SessionManager {
    readonly session: Session | null
    readonly setSession: (session: Session) => void
    readonly clearSession: () => void
}

/**
 * Returns the session data.
 *
 * @return the session data
 */
export function useSession(): Session | null {
    return useContext(SessionManagerContext).session
}

/**
 * Returns the session manager.
 *
 * @return the session manager
 */
export function useSessionManager(): SessionManager {
    return useContext(SessionManagerContext)
}

/**
 * Checks whether the user is logged in or not.
 *
 * @return true if the user is logged in, false otherwise
 */
export function useLoggedIn(): boolean {
    return useSessionManager().session != null
}