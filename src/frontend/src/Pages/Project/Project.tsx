import * as React from "react"
import {Outlet, useOutlet} from "react-router-dom"
import Box from "@mui/material/Box"
import EmptyProject from "./EmptyProject"
import {ProjectContext, useProject} from "./useProject"
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
        handleEditProject,
        workflows,
        loadingWorkflows,
        onWorkflowsUpdate,
        error,
        clearError
    } = useProject()

    // TODO: Fix bug where the outlet is not updated when the project is changed (is this still happening?)

    return (
        <Box sx={{
            display: "flex",
            flexDirection: "row",
            height: '92%',
            width: '100%',
            position: "relative"
        }}>
            <ProjectContext.Provider value={{project, onFileStructureUpdate, onWorkflowsUpdate}}>
                <ProjectStructure project={project} workflows={workflows} loading={loadingFiles || loadingWorkflows}/>
                {
                    outlet && !loadingFiles
                        ? <Outlet/>
                        : <EmptyProject
                            project={project}
                            loading={loadingFiles}
                            handleEditProject={handleEditProject}
                            error={error}
                            clearError={clearError}
                        />
                }
            </ProjectContext.Provider>
        </Box>
    )
}
