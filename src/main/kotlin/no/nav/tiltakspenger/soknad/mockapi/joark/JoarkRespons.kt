package no.nav.tiltakspenger.soknad.mockapi.joark

data class JoarkRespons(
    val journalpostId: String?,
)

fun mockJoarkRespons(): JoarkRespons = JoarkRespons(
    journalpostId = "123456",
)
