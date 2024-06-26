package no.nav.tiltakspenger.soknad.mockapi

import java.time.LocalDate

fun LocalDate.isSameOrBefore(otherDate: LocalDate): Boolean {
    return this.isEqual(otherDate) || this.isBefore(otherDate)
}
