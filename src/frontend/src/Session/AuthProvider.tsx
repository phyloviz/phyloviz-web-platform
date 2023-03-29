import {Session, SessionManager} from "./Session";
import * as React from "react";
import {createContext, useState} from "react";

export const SessionManagerContext = createContext<SessionManager>({
    session: null,
    setSession: () => {
    },
    clearSession: () => {
    }
})

const sessionStorageKey = 'session'

/**
 * Provides the session data to the children.
 *
 * @param children the children to render
 */
export function AuthProvider({children}: { children: React.ReactNode }) {
    const [session, setSession] = useState<Session | null>(() => {
        const sessionJson = localStorage.getItem(sessionStorageKey)
        if (!sessionJson)
            return

        return JSON.parse(sessionJson)
    })

    return (
        <SessionManagerContext.Provider value={{
            session,
            setSession: (session: Session) => {
                setSession(session)

                localStorage.setItem(sessionStorageKey, JSON.stringify(session))
            },
            clearSession: () => {
                localStorage.removeItem(sessionStorageKey)

                setSession(null)
            }
        }}>
            {children}
        </SessionManagerContext.Provider>
    )
}