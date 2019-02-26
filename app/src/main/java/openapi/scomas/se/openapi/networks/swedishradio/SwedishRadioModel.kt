package openapi.scomas.se.openapi.networks.swedishradio

/**
 * Project : OpenAPI
 * Package Name : openapi.scomas.se.openapi.networks.swedishradio
 * Created by svenpettersson on 2019-02-23.
 */
object SwedishRadioModel {
    data class SwedishRadioResult(
            val copyright: String,
            val pagination: Pagination,
            val programs: List<Program>
    )

    data class Pagination(
            val nextpage: String,
            val page: Int,
            val size: Int,
            val totalhits: Int,
            val totalpages: Int
    )

    data class Program(
            val archived: Boolean,
            val channel: Channel,
            val description: String,
            val email: String,
            val hasondemand: Boolean,
            val haspod: Boolean,
            val id: Int,
            val name: String,
            val phone: String,
            val programimage: String,
            val programimagetemplate: String,
            val programimagetemplatewide: String,
            val programimagewide: String,
            val programslug: String,
            val programurl: String,
            val responsibleeditor: String,
            val socialimage: String,
            val socialimagetemplate: String,
            val socialmediaplatforms: List<Socialmediaplatform>
    )

    data class Channel(
            val id: Int,
            val name: String
    )

    data class Socialmediaplatform(
            val platform: String,
            val platformurl: String
    )

}