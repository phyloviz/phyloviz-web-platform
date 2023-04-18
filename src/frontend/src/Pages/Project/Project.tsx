import * as React from "react"
import {Outlet, useOutlet} from "react-router-dom"
import Box from "@mui/material/Box"
import EmptyProject from "./EmptyProject"
import {useProject} from "./useProject"
import {ProjectStructure} from "../../Components/Project/ProjectStructure/ProjectStructure"

/**
 * Project page.
 */
export default function Project() {
    const outlet = useOutlet()
    const {
        project,
        loadingFiles,
        onFileStructureUpdate,
        workflows,
        loadingWorkflows,
        onWorkflowsUpdate,
        error
    } = useProject()

    return (
        <Box sx={{
            display: "flex",
            flexDirection: "row",
            height: '90%',
            width: '100%',
        }}>
            <ProjectStructure project={project} workflows={workflows} loading={loadingFiles || loadingWorkflows}/>
            {
                outlet && !loadingFiles && !error
                    ? <Outlet context={{project, onFileStructureUpdate, onWorkflowsUpdate}}/>
                    : <EmptyProject project={project} loading={loadingFiles} error={error}/>
            }
        </Box>
    )
}
