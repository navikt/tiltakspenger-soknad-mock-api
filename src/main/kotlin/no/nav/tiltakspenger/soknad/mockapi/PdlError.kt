package no.nav.tiltakspenger.soknad.mockapi

data class PdlError(
    val message: String,
    val locations: List<PdlErrorLocation>,
    val path: List<String>? = null,
    val extensions: PdlErrorExtension,
)

data class PdlErrorLocation(
    val line: Int?,
    val column: Int?,
)

data class PdlErrorExtension(
    val code: String? = null,
    val details: PdlErrorDetails,
    val classification: String,
)
data class PdlErrorDetails(
    val type: String,
    val cause: String? = null,
    val policy: String? = null,
)
