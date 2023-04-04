export interface GetIsolateDataRowsOutputModel {
    data: Row[]
    totalCount: number
}

export interface Row {
    id: string
    row: string[]
}