export interface GetTypingDataProfilesOutputModel {
    data: Profile[]
    totalCount: number
}

export interface Profile {
    id: string
    profile: string[]
}