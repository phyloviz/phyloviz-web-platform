import * as React from "react"
import {useEffect, useState} from "react"
import Paper from "@mui/material/Paper";
import Typography from "@mui/material/Typography";
import {Container} from "@mui/material";
import {ProjectModel} from "../../Services/administration/models/ProjectModel";
import {AdministrationService} from "../../Services/administration/AdministrationService";

interface ProjectCardProps {
    project: ProjectModel;
}

function ProjectCard({project: ProjectModel}: ProjectCardProps) {
    return null; // TODO: Implement
}

/**
 * OpenProject page.
 */
export default function OpenProject() {
    const [projects, setProjects] = useState<ProjectModel[]>([]);

    useEffect(() => {
        AdministrationService.getProjects()
            .then((res) => {
                setProjects(res.projects);
            })
            .catch((err) => {
                console.log(err);
            });
    }, []);

    return (
        <Container>
            <Paper sx={{
                p: 4,
                display: "flex",
                flexDirection: "column",
                marginTop: 4,
                alignItems: "center"
            }}>
                <Typography component="h1" variant="h4">
                    Open Project
                </Typography>
                {
                    projects.map((project, index) => {
                        return (
                            <ProjectCard key={index} project={project}/>
                        );
                    })
                }
            </Paper>
        </Container>
    );
}
