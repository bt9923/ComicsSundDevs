package app.example.comicbook.Data.model

data class ComicsBook(
    val error: String,
    val limit: Int,
    val number_of_page_results: Int,
    val number_of_total_results: Int,
    val offset: Int,
    val results: List<Result>,
    val status_code: Int,
    val version: String
)

data class Result(
    val aliases: Any,
    val api_detail_url: String,
    val cover_date: String,
    val date_added: String,
    val date_last_updated: String,
    val deck: Any,
    val description: String,
    val has_staff_review: Boolean,
    val id: Int,
    val image: Image,
    val issue_number: String,
    val name: String,
    val site_detail_url: String,
    val store_date: String,
    val volume: Volume
)

data class Image(
    val icon_url: String,
    val image_tags: String,
    val medium_url: String,
    val original_url: String,
    val screen_large_url: String,
    val screen_url: String,
    val small_url: String,
    val super_url: String,
    val thumb_url: String,
    val tiny_url: String
)

data class Volume(
    val api_detail_url: String,
    val id: Int,
    val name: String,
    val site_detail_url: String
)
