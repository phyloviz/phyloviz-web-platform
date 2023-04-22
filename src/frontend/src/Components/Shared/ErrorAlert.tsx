import Alert from "@mui/material/Alert"
import IconButton from "@mui/material/IconButton"
import {Close} from "@mui/icons-material"
import * as React from "react"

/**
 * Props for the ErrorAlert component.
 *
 * @property error The error message to show.
 * @property clearError The function to clear the error.
 */
interface ErrorAlertProps {
    error: string | null
    clearError: () => void
}

/**
 * Shows an alert with the given error message.
 */
export function ErrorAlert({error, clearError}: ErrorAlertProps) {
    return (<>
        {
            error && <Alert
                action={
                    <IconButton
                        aria-label="close"
                        color="inherit"
                        size="small"
                        onClick={clearError}
                    >
                        <Close fontSize="inherit"/>
                    </IconButton>
                }
                severity="error"
                sx={{mb: 2}}
            >
                {error}
            </Alert>
        }
    </>)
}