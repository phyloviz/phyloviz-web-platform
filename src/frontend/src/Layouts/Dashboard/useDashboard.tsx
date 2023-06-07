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

            if (response.status === 200) {
                const data = await response.json()
                sessionManager.setSession(data)
            } else
                sessionManager.clearSession()
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

                const form = document.createElement("form");

                //Move the submit function to another variable
                //so that it doesn't get overwritten.
                form._submit_function_ = form.submit;

                form.setAttribute("method", "post");
                form.setAttribute("action", WebApiUris.logout);

                document.body.appendChild(form);
                form._submit_function_(); //Call the renamed function.
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