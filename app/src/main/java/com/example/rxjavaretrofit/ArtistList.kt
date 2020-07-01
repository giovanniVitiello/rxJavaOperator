package com.example.rxjavaretrofit

data class ArtistList(
    val artists: List<Artist>
) {
    data class Artist(
        val external_urls: ExternalUrls,
        val followers: Followers,
        val genres: List<String>,
        val href: String,
        val id: String,
        val images: List<Image>,
        val name: String,
        val popularity: Int,
        val type: String,
        val uri: String
    ) {
        data class ExternalUrls(
            val spotify: String
        )

        data class Followers(
            val href: Any,
            val total: Int
        )

        data class Image(
            val height: Int,
            val url: String,
            val width: Int
        )
    }
}