import {ResourceModel} from "./ResourceModel";

export interface ProjectModel {
    id: string;
    name: string;
    description: string;
    owner: string;
    resources: ResourceModel[];
}