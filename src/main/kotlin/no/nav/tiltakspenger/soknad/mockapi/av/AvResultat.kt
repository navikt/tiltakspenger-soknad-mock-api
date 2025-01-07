package no.nav.tiltakspenger.soknad.mockapi.av

import com.fasterxml.jackson.annotation.JsonProperty

data class AvSjekkResultat(
    @JsonProperty("Filename") val filnavn: String,
    @JsonProperty("Result") val resultat: Status,
)

enum class Status {
    FOUND,
    OK,
    ERROR,
}

fun mockAVResultat(): List<AvSjekkResultat> = listOf(
    AvSjekkResultat(filnavn = "testfil.pdf", resultat = Status.OK),
)
