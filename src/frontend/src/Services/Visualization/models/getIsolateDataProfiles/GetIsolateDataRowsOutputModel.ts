export interface GetIsolateDataRowsOutputModel {
    rows: Row[]
    totalCount: number
}

export interface Row {
    id: string
    profileId: string
    row: {[key: string]: string}
}