package no.nav.tiltakspenger.soknad.mockapi.personalia

import java.time.LocalDate

data class Fødsel(
    val foedselsdato: LocalDate,
    override val folkeregistermetadata: FolkeregisterMetadata,
    override val metadata: EndringsMetadata,
) : Changeable

const val FREG = "FREG"
fun String.isFreg() = this.equals(FREG, ignoreCase = true)

fun avklarFødsel(foedsler: List<Fødsel>): Fødsel {
    val foedslerSortert = foedsler.sortedByDescending { getEndringstidspunktOrNull(it) }
    val foedselFreg = foedslerSortert.find { it.metadata.master.isFreg() }
    return foedselFreg ?: foedslerSortert.firstOrNull()
        ?: throw throw IllegalStateException("Fødsel kunne ikke avklares")
}
