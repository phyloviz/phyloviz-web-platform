export interface GetDistanceMatrixOutputModel {
    distances: Distances
    totalCount: number
}

export interface Distances {
    [key: string]: string[]
}