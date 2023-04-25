/**
 * A news item.
 *
 * @property title The title of the news.
 * @property date The date of the news.
 * @property description The description of the news.
 * @property link The link to the news.
 */
export interface News {
    title: string
    date: string
    description: string
    link: string | null
}

/**
 * Hook for the news page.
 */
export function useNews() {
    const news = [
        {
            title: "PHYLOViZ Web Platform is now available",
            date: "2021-09-01",
            description: "The first version of the PHYLOViZ Web Platform is now available. It allows to visualize phylogenetic trees and their associated data in a web browser.",
            link: null,
        },
        {
            title: "Fake new",
            date: "2021-09-01",
            description: "This is a fake news. It is used to test the news page.",
            link: "https://www.youtube.com/watch?v=dQw4w9WgXcQ"
        },
        {
            title: "Other fake new",
            date: "2021-09-01",
            description: "This is another fake news. It is used to test the news page.",
            link: "https://www.youtube.com/watch?v=dQw4w9WgXcQ"
        }
        // Other news
    ]

    return {
        news
    }
}