import * as React from "react"
import {useEffect, useState} from "react"
import {useNavigate} from "react-router-dom"
import {useLoggedIn, useSession, useSessionManager} from "../../Session/Session"
import {WebApiUris} from "../../Services/WebApiUris"
import {WebUiUris} from "../../Pages/WebUiUris"
import LoginIcon from "@mui/icons-material/Login"
import LogoutIcon from "@mui/icons-material/Logout"
import ProfileIcon from "@mui/icons-material/AccountCircle"

/**
 * Hook for the Dashboard component.
 */
export function useDashboard() {
    const [open, setOpen] = useState(false)
    const navigate = useNavigate()

    const loggedIn = useLoggedIn()
    const session = useSession()
    const sessionManager = useSessionManager()

    useEffect(() => {
        async function checkLoggedIn() {
            const response = await fetch(WebApiUris.getSession, {
                method: 'GET'
            })
            const data = await response.json()

            if (response.status === 200)
                sessionManager.setSession(data)
        }

        checkLoggedIn()
    }, [])

    const authSettings = [
        {
            name: 'Profile',
            icon: <ProfileIcon/>,
            callback: () => navigate(WebUiUris.PROFILE)
        },
        {
            name: 'Logout',
            icon: <LogoutIcon/>,
            callback: async () => {
                sessionManager.clearSession()
                await fetch(WebApiUris.logout, {
                    method: 'POST'
                })
            }
        }
    ]

    const nonAuthSettings = [
        {
            name: 'Login',
            icon: <LoginIcon/>,
            callback: () => window.location.href = WebUiUris.LOGIN
        }
    ]

    const toggleDrawer = () => {
        setOpen(!open)
    }

    return {
        open,
        toggleDrawer,
        loggedIn,
        session,
        authSettings,
        nonAuthSettings,
        navigate
    }
}