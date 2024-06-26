package no.nav.tiltakspenger.soknad.mockapi.personalia

data class SøkersBarnFraPDL(
    val navn: List<Navn>,
    val adressebeskyttelse: List<Adressebeskyttelse>,
    val foedsel: List<Fødsel>,
    val doedsfall: List<Dødsfall>,
)

data class SøkersBarnFraPDLRespons(
    val hentPerson: SøkersBarnFraPDL?,
)

data class SøkersBarnRespons(
    val data: SøkersBarnFraPDLRespons? = null,
)
