package no.nav.tiltakspenger.soknad.mockapi.personalia

enum class ForelderBarnRelasjonRolle {
    BARN,
    FAR,
    MOR,
    MEDMOR,
}

data class ForelderBarnRelasjon(
    val relatertPersonsIdent: String?,
    val relatertPersonsRolle: ForelderBarnRelasjonRolle,
    override val folkeregistermetadata: FolkeregisterMetadata?,
    override val metadata: EndringsMetadata,
) : Changeable
