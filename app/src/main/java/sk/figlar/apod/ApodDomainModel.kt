package sk.figlar.apod

data class ApodDomainModel(
    val id: Long,
    val title: String,
    val date: String,
    val copyright: String,
    val explanation: String,
    val url: String,
    val mediaType: String,
)
