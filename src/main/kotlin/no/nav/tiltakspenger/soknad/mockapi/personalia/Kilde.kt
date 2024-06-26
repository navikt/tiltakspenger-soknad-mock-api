package no.nav.tiltakspenger.soknad.mockapi.personalia

object Kilde {
    const val PDL = "PDL"
    const val BRUKER_SELV = "Bruker selv"
}

fun kildeErUdokumentert(metadata: EndringsMetadata) =
    metadata.master == Kilde.PDL && metadata.endringer.nyeste()?.kilde == Kilde.BRUKER_SELV
