package no.nav.tiltakspenger.soknad.mockapi.personalia

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import no.nav.tiltakspenger.soknad.mockapi.PdlError

data class SøkerFraPDL(
    val navn: List<Navn>,
    val adressebeskyttelse: List<Adressebeskyttelse>,
    val foedsel: List<Fødsel>,
    val forelderBarnRelasjon: List<ForelderBarnRelasjon>,
    val doedsfall: List<Dødsfall>,
)

data class SøkerFraPDLRespons(
    val hentPerson: SøkerFraPDL?,
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class SøkerRespons(
    val data: SøkerFraPDLRespons? = null,
    val errors: List<PdlError> = emptyList(),
) {
    private fun extractPerson(): SøkerFraPDL? {
        if (this.errors.isNotEmpty()) {
            throw IllegalStateException(this.errors.firstOrNull()?.toString())
        }
        return this.data?.hentPerson
    }

    fun toPerson(): Person {
        val person = extractPerson() ?: throw IllegalStateException("Fant ikke personen")
        val navn = avklarNavn(person.navn)
        val fødsel = avklarFødsel(person.foedsel)
        if (person.doedsfall.isNotEmpty()) {
            throw IllegalStateException("Søker er registrert som død i PDL")
        }
        val adressebeskyttelseGradering = avklarGradering(person.adressebeskyttelse)
        return Person(
            fornavn = navn.fornavn,
            mellomnavn = navn.mellomnavn,
            etternavn = navn.etternavn,
            fødselsdato = fødsel.foedselsdato,
            forelderBarnRelasjon = person.forelderBarnRelasjon,
            adressebeskyttelseGradering = adressebeskyttelseGradering,
            erDød = false,
        )
    }
}
