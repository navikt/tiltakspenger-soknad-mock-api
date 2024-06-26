package no.nav.tiltakspenger.soknad.mockapi.tiltak

import no.nav.tiltakspenger.libs.tiltak.TiltakResponsDTO
import java.time.LocalDateTime

fun mockTiltakRespons(): List<TiltakResponsDTO.TiltakDTO> = listOf(
    TiltakResponsDTO.TiltakDTO(
        id = "test-id",
        gjennomforing = TiltakResponsDTO.GjennomføringDTO(
            id = "test",
            arrangørnavn = "test",
            typeNavn = "test",
            arenaKode = TiltakResponsDTO.TiltakType.TESTING,
        ),
        deltakelseFom = null,
        deltakelseTom = null,
        deltakelseStatus = TiltakResponsDTO.DeltakerStatusDTO.DELTAR,
        deltakelseDagerUke = null,
        deltakelseProsent = null,
        kilde = "test",
        registrertDato = LocalDateTime.now(),
    ),
)
