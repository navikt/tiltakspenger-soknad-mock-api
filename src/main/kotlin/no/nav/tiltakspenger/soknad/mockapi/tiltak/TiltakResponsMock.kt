package no.nav.tiltakspenger.soknad.mockapi.tiltak

import no.nav.tiltakspenger.libs.tiltak.TiltakResponsDTO
import java.time.LocalDate
import java.time.LocalDateTime

fun mockTiltakRespons(): List<TiltakResponsDTO.TiltakDTO> = listOf(
    TiltakResponsDTO.TiltakDTO(
        id = "test-id",
        gjennomforing = TiltakResponsDTO.GjennomføringDTO(
            id = "test",
            arrangørnavn = "Testarrangør AS",
            typeNavn = TiltakResponsDTO.TiltakType.KURS.navn,
            arenaKode = TiltakResponsDTO.TiltakType.KURS,
        ),
        deltakelseFom = LocalDate.now().withDayOfMonth(1),
        deltakelseTom = LocalDate.now().withDayOfMonth(20),
        deltakelseStatus = TiltakResponsDTO.DeltakerStatusDTO.DELTAR,
        deltakelseDagerUke = null,
        deltakelseProsent = null,
        kilde = "test",
        registrertDato = LocalDateTime.now(),
    ),
)
