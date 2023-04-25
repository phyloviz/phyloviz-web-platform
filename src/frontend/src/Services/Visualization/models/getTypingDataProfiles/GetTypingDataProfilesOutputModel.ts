export interface GetTypingDataProfilesOutputModel {
    profiles: Profile[]
    totalCount: number
}

export interface Profile {
    id: string
    profile: string[]
}