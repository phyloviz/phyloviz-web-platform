/**
 * Hook for IsolateData page.
 */
export function useIsolateData() {
    // TODO: Implement useIsolateData

    return {
        data: {
            columns: [
                {field: 'id', headerName: 'ID', width: 70},
                {field: 'isolate', headerName: 'Isolate', width: 130},
                {field: 'aliases', headerName: 'Aliases', width: 130},
                {field: 'country', headerName: 'Country', width: 130},
                {field: 'continent', headerName: 'Continent', width: 130},
                {field: 'region', headerName: 'Region', width: 130},
                {field: 'town_or_city', headerName: 'Town or City', width: 130},
                {field: 'year', headerName: 'Year', width: 130},
                {field: 'month', headerName: 'Month', width: 130},
                {field: 'isolation_date', headerName: 'Isolation Date', width: 130},
            ],
            rows: [
                {
                    id: 1,
                    isolate: 'P09',
                    aliases: '',
                    country: 'Unknown',
                    continent: '',
                    region: '',
                    town_or_city: '',
                    year: '',
                    month: '',
                    isolation_date: ''
                },
                {
                    id: 2,
                    isolate: 'P12',
                    aliases: '',
                    country: 'Unknown',
                    continent: '',
                    region: '',
                    town_or_city: '',
                    year: '',
                    month: '',
                    isolation_date: ''
                },
                {
                    id: 3,
                    isolate: 'P18',
                    aliases: 'ATCC43439',
                    country: 'Canada',
                    continent: 'North America',
                    region: '',
                    town_or_city: '',
                    year: '',
                    month: '',
                    isolation_date: ''
                },
                {
                    id: 4,
                    isolate: 'P22',
                    aliases: 'ATCC43448',
                    country: 'Canada',
                    continent: 'North America',
                    region: '',
                    town_or_city: '',
                    year: '',
                    month: '',
                    isolation_date: ''
                },
                {
                    id: 5,
                    isolate: 'P26',
                    aliases: 'ATCC43477',
                    country: 'Unknown',
                    continent: '',
                    region: '',
                    town_or_city: '',
                    year: '',
                    month: '',
                    isolation_date: ''
                },
                {
                    id: 6,
                    isolate: 'P27',
                    aliases: '',
                    country: 'Unknown',
                    continent: '',
                    region: '',
                    town_or_city: '',
                    year: '',
                    month: '',
                    isolation_date: ''
                },
                {
                    id: 7,
                    isolate: 'P31',
                    aliases: '',
                    country: 'Unknown',
                    continent: '',
                    region: '',
                    town_or_city: '',
                    year: '',
                    month: '',
                    isolation_date: ''
                },
                {
                    id: 8,
                    isolate: 'P33',
                    aliases: '',
                    country: 'Unknown',
                    continent: '',
                    region: '',
                    town_or_city: '',
                    year: '',
                    month: '',
                    isolation_date: ''
                },
                {
                    id: 9,
                    isolate: 'P43',
                    aliases: '',
                    country: 'Canada',
                    continent: 'North America',
                    region: 'Vancouver',
                    town_or_city: '',
                    year: '',
                    month: '',
                    isolation_date: ''
                },
                {
                    id: 10,
                    isolate: 'P55',
                    aliases: '',
                    country: 'Unknown',
                    continent: '',
                    region: '',
                    town_or_city: '',
                    year: '',
                    month: '',
                    isolation_date: ''
                },
            ],
            initialState: {
                rowGrouping: {
                    groupBy: ['commodity'],
                }
            }
        },
        loading: false
    }
}