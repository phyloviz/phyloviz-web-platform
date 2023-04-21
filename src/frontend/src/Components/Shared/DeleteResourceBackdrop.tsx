import {Backdrop, Button, Card, CardActions, CardHeader} from "@mui/material"
import * as React from "react"
import {useState} from "react"
import {ErrorAlert} from "./ErrorAlert"

/**
 * Props for the DeleteResourceBackdrop component.
 *
 * @property open Whether the backdrop is open or not.
 * @property title The title of the backdrop.
 * @property subheader The subheader of the backdrop.
 * @property handleClose Callback for when the user wants to close the backdrop.
 * @property handleDelete Callback for when the user wants to delete the resource.
 * @property error The error message to display.
 * @property clearError Callback for when the user wants to clear the error message.
 */
interface DeleteResourceBackdropProps {
    open: boolean
    title: string
    subheader: string
    handleClose: () => void
    handleDelete: () => void
    error: string | null
    clearError: () => void
}

/**
 * Backdrop for deleting a resource.
 */
export function DeleteResourceBackdrop(
    {
        open,
        title,
        subheader,
        handleClose,
        handleDelete,
        error,
        clearError
    }: DeleteResourceBackdropProps
) {
    return (
        <Backdrop sx={{color: '#fff', zIndex: (theme) => theme.zIndex.drawer + 1}} open={open}>
            <Card sx={{boxShadow: 12}}>
                <CardHeader title={title} subheader={subheader}/>
                <ErrorAlert error={error} clearError={clearError}/>
                <CardActions sx={{justifyContent: "space-between"}}>
                    <Button
                        variant="contained"
                        onClick={handleClose}
                        sx={{maxHeight: "40px"}}
                    >
                        Cancel
                    </Button>
                    <Button
                        variant="contained"
                        onClick={handleDelete}
                        sx={{maxHeight: "40px"}}
                        color={"error"}
                    >
                        Delete
                    </Button>
                </CardActions>
            </Card>
        </Backdrop>
    )
}

/**
 * Hook for using the DeleteResourceBackdrop component.
 */
export function useDeleteResourceBackdrop() {
    const [deleteBackdropOpen, setDeleteBackdropOpen] = useState(false)

    return {
        deleteBackdropOpen,
        handleDeleteBackdropClose: () => setDeleteBackdropOpen(false),
        handleDeleteBackdropOpen: () => setDeleteBackdropOpen(true)
    }
}