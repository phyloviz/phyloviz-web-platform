import {ProjectModel} from "../../Services/administration/models/ProjectModel";

/**
 * Props for the ProjectCard component.
 *
 * @property project The project to display.
 * @property handleOpenProject Callback for when the user wants to open the project.
 */
interface ProjectCardProps {
    project: ProjectModel;
    handleOpenProject: (projectId: string) => void;
}

/**
 * Component for displaying the details of a project.
 */
export function ProjectCard({project, handleOpenProject}: ProjectCardProps) {
    return null; // TODO: Implement
}