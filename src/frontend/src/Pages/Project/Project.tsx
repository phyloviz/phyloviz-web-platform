import * as React from "react"
import {Outlet, useOutlet} from "react-router-dom"
import {FileManager} from "../../Components/Project/FileManager/FileManager"
import Box from "@mui/material/Box"
import EmptyProject from "./EmptyProject"
import {useProject} from "./useProject"

/**
 * Project page.
 */
export default function Project() {
    const outlet = useOutlet()
    const {project, onUpdated} = useProject()

    return (
        <Box sx={{
            display: "flex",
            flexDirection: "row",
            height: '90%',
            width: '100%',
        }}>
            <FileManager project={project}/>
            {
                outlet
                    ? <Outlet context={{project, onUpdated}}/>
                    : <EmptyProject project={project}/>
            }
        </Box>
    )
}
