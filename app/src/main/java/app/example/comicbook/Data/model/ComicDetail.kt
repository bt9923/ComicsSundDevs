package app.example.comicbook.Data.model

data class ComicDetail(
    val error: String,
    val limit: Int,
    val number_of_page_results: Int,
    val number_of_total_results: Int,
    val offset: Int,
    val results: Results,
    val status_code: Int,
    val version: String
)

data class Results(
    val aliases: Any,
    val api_detail_url: String,
    val character_credits: List<CharacterCredit>,
    val character_died_in: List<Any>,
    val concept_credits: List<ConceptCredit>,
    val cover_date: String,
    val date_added: String,
    val date_last_updated: String,
    val deck: Any,
    val description: String,
    val first_appearance_characters: Any,
    val first_appearance_concepts: Any,
    val first_appearance_locations: Any,
    val first_appearance_objects: Any,
    val first_appearance_storyarcs: Any,
    val first_appearance_teams: Any,
    val has_staff_review: Boolean,
    val id: Int,
    val image: Image,
    val issue_number: String,
    val location_credits: List<LocationCredit>,
    val name: String,
    val object_credits: List<Any>,
    val person_credits: List<PersonCredit>,
    val site_detail_url: String,
    val store_date: Any,
    val story_arc_credits: List<Any>,
    val team_credits: List<TeamCredit>,
    val team_disbanded_in: List<Any>,
    val volume: Volume
)

data class CharacterCredit(
    val api_detail_url: String,
    val id: Int,
    val name: String,
    val site_detail_url: String
)

data class ConceptCredit(
    val api_detail_url: String,
    val id: Int,
    val name: String,
    val site_detail_url: String
)

data class LocationCredit(
    val api_detail_url: String,
    val id: Int,
    val name: String,
    val site_detail_url: String
)

data class PersonCredit(
    val api_detail_url: String,
    val id: Int,
    val name: String,
    val role: String,
    val site_detail_url: String
)

data class TeamCredit(
    val api_detail_url: String,
    val id: Int,
    val name: String,
    val site_detail_url: String
)
