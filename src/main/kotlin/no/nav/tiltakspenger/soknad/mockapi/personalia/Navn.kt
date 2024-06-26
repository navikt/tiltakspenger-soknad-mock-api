package no.nav.tiltakspenger.soknad.mockapi.personalia

data class Navn(
    val fornavn: String,
    val mellomnavn: String? = null,
    val etternavn: String,
    override val metadata: EndringsMetadata,
    override val folkeregistermetadata: FolkeregisterMetadata,
) : Changeable

fun avklarNavn(navn: List<Navn>): Navn {
    if (navn.isEmpty()) throw IllegalStateException("Navn kunne ikke avklares")
    return navn
        .sortedByDescending { getEndringstidspunktOrNull(it) }
        .firstOrNull { !kildeErUdokumentert(it.metadata) }
        ?: throw IllegalStateException("Navn kunne ikke avklares")
}
