package no.nav.tiltakspenger.soknad.mockapi

import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.jackson.jackson
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.application.install
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.routing
import mu.KotlinLogging
import no.nav.tiltakspenger.soknad.mockapi.av.mockAVResultat
import no.nav.tiltakspenger.soknad.mockapi.joark.mockJoarkRespons
import no.nav.tiltakspenger.soknad.mockapi.personalia.mockSøkerRespons
import no.nav.tiltakspenger.soknad.mockapi.tiltak.mockTiltakRespons

fun main(args: Array<String>) {
    System.setProperty("logback.configurationFile", "egenLogback.xml")
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.mockApi() {
    val log = KotlinLogging.logger {}
    log.info { "starting server" }

    installJacksonFeature()

    routing {
        get("/tokenx/tiltak") {
            val tiltakMock = mockTiltakRespons()
            call.respond(tiltakMock)
        }
        post("/personalia") {
            val personaliaMock = mockSøkerRespons()
            call.respond(personaliaMock)
        }
        post("/rest/journalpostapi/v1/journalpost") {
            call.respond(status = HttpStatusCode.Created, message = mockJoarkRespons())
        }
        post("/av") {
            call.respond(mockAVResultat())
        }
        get("/unleash") {
            call.respond(status = HttpStatusCode.OK, message = "OK")
        }
        post("/unleash") {
            call.respond(status = HttpStatusCode.OK, message = "OK")
        }
    }
}

internal fun Application.installJacksonFeature() {
    install(ContentNegotiation) {
        jackson {
            configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
            registerModule(JavaTimeModule())
            registerModule(KotlinModule.Builder().build())
        }
    }
}
