/**
 * Hook for typing data page.
 */
export function useTypingData() {
    // TODO: Add typing data information

    return {
        data: {
            columns: [
                {field: 'st', headerName: 'ST', width: 100},
                {field: 'aspA', headerName: 'aspA', width: 100},
                {field: 'ghA', headerName: 'ghA', width: 100},
                {field: 'gltA', headerName: 'gltA', width: 100},
                {field: 'glyA', headerName: 'glyA', width: 100},
                {field: 'pgm', headerName: 'pgm', width: 100},
                {field: 'tkt', headerName: 'tkt', width: 100},
                {field: 'uncA', headerName: 'uncA', width: 100},
            ],
            rows: [
                {id: 1, st: 1, aspA: 2, ghA: 1, gltA: 54, glyA: 3, pgm: 4, tkt: 1, uncA: 5},
                {id: 2, st: 2, aspA: 4, ghA: 7, gltA: 51, glyA: 4, pgm: 1, tkt: 7, uncA: 1},
                {id: 3, st: 3, aspA: 3, ghA: 2, gltA: 5, glyA: 10, pgm: 11, tkt: 11, uncA: 6},
                {id: 4, st: 4, aspA: 10, ghA: 11, gltA: 16, glyA: 7, pgm: 10, tkt: 5, uncA: 7},
                {id: 5, st: 5, aspA: 7, ghA: 2, gltA: 5, glyA: 2, pgm: 10, tkt: 3, uncA: 6},
                {id: 6, st: 6, aspA: 63, ghA: 34, gltA: 27, glyA: 33, pgm: 45, tkt: 5, uncA: 7},
                {id: 7, st: 7, aspA: 8, ghA: 10, gltA: 2, glyA: 2, pgm: 14, tkt: 12, uncA: 6},
                {id: 8, st: 8, aspA: 2, ghA: 1, gltA: 1, glyA: 3, pgm: 2, tkt: 1, uncA: 6},
                {id: 9, st: 9, aspA: 1, ghA: 6, gltA: 22, glyA: 24, pgm: 12, tkt: 7, uncA: 1},
                {id: 10, st: 10, aspA: 2, ghA: 59, gltA: 4, glyA: 38, pgm: 17, tkt: 12, uncA: 5},
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