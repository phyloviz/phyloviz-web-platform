export interface GetIsolateDataRowsOutputModel {
    rows: Row[]
    totalCount: number
}

export interface Row {
    id: string
    row: string[]
}