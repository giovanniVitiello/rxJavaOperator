package com.example.rxjavaretrofit.objectData

data class HotelList(
    val `data`: List<Data>,
    val paging: Paging,
    val status: Status
) {
    data class Data(
        val awards: List<Award>,
        val bearing: String,
        val business_listings: BusinessListings,
        val distance: String,
        val distance_string: Any,
        val hotel_class: String,
        val hotel_class_attribution: String,
        val is_closed: Boolean,
        val is_long_closed: Boolean,
        val latitude: String,
        val listing_key: String,
        val location_id: String,
        val location_string: String,
        val longitude: String,
        val name: String,
        val num_reviews: String,
        val photo: Photo,
        val preferred_map_engine: String,
        val price: String,
        val price_level: String,
        val ranking: String,
        val ranking_category: String,
        val ranking_denominator: String,
        val ranking_geo: String,
        val ranking_geo_id: String,
        val ranking_position: String,
        val rating: String,
        val raw_ranking: String,
        val special_offers: SpecialOffers,
        val subcategory_type: String,
        val subcategory_type_label: String,
        val timezone: String
    ) {
        data class Award(
            val award_type: String,
            val categories: List<String>,
            val display_name: String,
            val images: Images,
            val year: String
        ) {
            data class Images(
                val large: String,
                val small: String
            )
        }

        data class BusinessListings(
            val desktop_contacts: List<DesktopContact>,
            val mobile_contacts: List<Any>
        ) {
            data class DesktopContact(
                val label: String,
                val type: String,
                val value: String
            )
        }

        data class Photo(
            val caption: String,
            val helpful_votes: String,
            val id: String,
            val images: Images,
            val is_blessed: Boolean,
            val published_date: String,
            val uploaded_date: String,
            val user: Any
        ) {
            data class Images(
                val large: Large,
                val medium: Medium,
                val original: Original,
                val small: Small,
                val thumbnail: Thumbnail
            ) {
                data class Large(
                    val height: String,
                    val url: String,
                    val width: String
                )

                data class Medium(
                    val height: String,
                    val url: String,
                    val width: String
                )

                data class Original(
                    val height: String,
                    val url: String,
                    val width: String
                )

                data class Small(
                    val height: String,
                    val url: String,
                    val width: String
                )

                data class Thumbnail(
                    val height: String,
                    val url: String,
                    val width: String
                )
            }
        }

        data class SpecialOffers(
            val desktop: List<Desktop>,
            val mobile: List<Any>
        ) {
            data class Desktop(
                val headline: String,
                val offerless_click_tracking_url: String,
                val url: String
            )
        }
    }

    data class Paging(
        val results: String,
        val total_results: String
    )

    data class Status(
        val auction_key: String,
        val autobroadened: Boolean,
        val commerce_country_iso_code: String,
        val doubleClickZone: String,
        val pricing: String,
        val primary_geo: String,
        val progress: String,
        val unfiltered_total_size: String
    )
}