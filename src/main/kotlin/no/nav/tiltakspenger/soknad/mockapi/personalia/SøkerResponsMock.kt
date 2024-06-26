package no.nav.tiltakspenger.soknad.mockapi.personalia

import java.time.LocalDate
import java.time.LocalDateTime

fun mockSøkerRespons(): SøkerRespons = SøkerRespons(
    data = SøkerFraPDLRespons(
        hentPerson = SøkerFraPDL(
            navn = listOf(
                Navn(
                    fornavn = "Test",
                    mellomnavn = "Test",
                    etternavn = "Test",
                    folkeregistermetadata = FolkeregisterMetadata(
                        kilde = "Test",
                        aarsak = "test",
                        ajourholdstidspunkt = LocalDateTime.now(),
                        gyldighetstidspunkt = LocalDateTime.now(),
                        opphoerstidspunkt = LocalDateTime.now(),
                        sekvens = 0,
                    ),
                    metadata = EndringsMetadata(
                        master = "test",
                        endringer = emptyList(),
                    ),
                ),

            ),
            forelderBarnRelasjon = emptyList(),
            foedsel = listOf(
                Fødsel(
                    foedselsdato = LocalDate.now().minusYears(19),
                    folkeregistermetadata = FolkeregisterMetadata(
                        aarsak = null,
                        ajourholdstidspunkt = null,
                        gyldighetstidspunkt = null,
                        kilde = null,
                        opphoerstidspunkt = null,
                        sekvens = null,
                    ),
                    metadata = EndringsMetadata(endringer = listOf(), master = ""),

                ),
            ),
            adressebeskyttelse = listOf(
                Adressebeskyttelse(
                    gradering = AdressebeskyttelseGradering.UGRADERT,
                    folkeregistermetadata = null,
                    metadata = EndringsMetadata(endringer = listOf(), master = "test"),
                ),
            ),
            doedsfall = emptyList(),
        ),
    ),
)
