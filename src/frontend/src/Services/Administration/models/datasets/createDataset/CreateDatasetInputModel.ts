export interface CreateDatasetInputModel {
    name: string
    description: string
    typingDataId: string
    isolateDataId: string | null
    isolateDataKey: string | null
}