package no.nav.tiltakspenger.soknad.mockapi.personalia

enum class AdressebeskyttelseGradering {
    STRENGT_FORTROLIG_UTLAND,
    STRENGT_FORTROLIG,
    FORTROLIG,
    UGRADERT,
}

data class Adressebeskyttelse(
    val gradering: AdressebeskyttelseGradering,
    override val folkeregistermetadata: FolkeregisterMetadata? = null,
    override val metadata: EndringsMetadata,
) : Changeable

fun avklarGradering(gradering: List<Adressebeskyttelse>): AdressebeskyttelseGradering {
    return if (gradering.isEmpty()) {
        AdressebeskyttelseGradering.UGRADERT
    } else if (gradering.size == 1) {
        gradering.firstOrNull { !kildeErUdokumentert(it.metadata) }?.gradering
            ?: throw IllegalStateException("Adressebeskyttelse kunne ikke avklares")
    } else {
        gradering
            .sortedByDescending { getEndringstidspunktOrNull(it) }
            .firstOrNull { it.gradering != AdressebeskyttelseGradering.UGRADERT && !kildeErUdokumentert(it.metadata) }?.gradering
            ?: throw IllegalStateException("Adressebeskyttelse kunne ikke avklares")
    }
}
