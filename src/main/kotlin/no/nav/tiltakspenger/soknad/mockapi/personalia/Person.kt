package no.nav.tiltakspenger.soknad.mockapi.personalia

import no.nav.tiltakspenger.soknad.mockapi.isSameOrBefore
import java.time.LocalDate

data class Person(
    val fornavn: String,
    val mellomnavn: String?,
    val etternavn: String,
    val fødselsdato: LocalDate? = null,
    val erDød: Boolean,
    val forelderBarnRelasjon: List<ForelderBarnRelasjon>? = emptyList(),
    val adressebeskyttelseGradering: AdressebeskyttelseGradering,
) {
    fun toPersonDTO(barn: List<Person> = emptyList()): PersonDTO {
        val levendeBarn = barn.filterNot { it.erDød }
        return PersonDTO(
            fornavn = fornavn,
            mellomnavn = mellomnavn,
            etternavn = etternavn,
            barn = levendeBarn.map {
                if (it.adressebeskyttelseGradering === AdressebeskyttelseGradering.UGRADERT) {
                    BarnDTO(
                        fødselsdato = it.fødselsdato!!,
                        fornavn = it.fornavn,
                        mellomnavn = it.mellomnavn,
                        etternavn = it.etternavn,
                    )
                } else {
                    BarnDTO(fødselsdato = it.fødselsdato!!)
                }
            },
            harFylt18År = fødselsdato?.isSameOrBefore(LocalDate.now().minusYears(18)) ?: throw IllegalStateException("Søker mangler fødselsdato"),
        )
    }

    fun barnsIdenter(): List<String> {
        return (forelderBarnRelasjon ?: emptyList())
            .filter { it.relatertPersonsRolle == ForelderBarnRelasjonRolle.BARN }
            .mapNotNull { it.relatertPersonsIdent }
            .distinct()
    }

    fun erUnder16ÅrPåDato(dato: LocalDate): Boolean {
        val datoMinus16år = dato.minusYears(16)
        return this.fødselsdato?.let {
            return it.isAfter(datoMinus16år)
        } ?: throw IllegalStateException("Barn mangler fødselsdato")
    }
}

data class BarnDTO(
    val fødselsdato: LocalDate,
    val fornavn: String? = null,
    val mellomnavn: String? = null,
    val etternavn: String? = null,
)

data class PersonDTO(
    val fornavn: String,
    val mellomnavn: String?,
    val etternavn: String,
    val barn: List<BarnDTO> = emptyList(),
    val harFylt18År: Boolean?,
)
