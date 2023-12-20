package com.tatq.plugins

import io.ktor.http.HttpStatusCode
import io.ktor.resources.Resource
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import io.ktor.server.routing.routing
import kotlinx.serialization.Serializable

fun Application.configureIndex() {
//    install(ContentNegotiation) {
//        json()
//        gson {
//        }
//    }

    routing {
        route("plexservers") {
            get {
                val plexServers = listOf(
                    PlexServer(
                        1,
                        "PlexServer1",
                        "http://plexserver1.com",
                        "Healthy",
                        listOf(Library("Library1", "/path1"))
                    ),
                    PlexServer(
                        2,
                        "PlexServer2",
                        "http://plexserver2.com",
                        "Online",
                        listOf(Library("Library2", "/path2"))
                    )
                )
                call.respond(plexServers)
            }

            route("{id}") {
                get {
                    val id = call.parameters["id"]?.toIntOrNull()
                    if (id != null) {
                        val plexServer = when (id) {
                            1 -> PlexServer(
                                1,
                                "PlexServer1",
                                "http://plexserver1.com",
                                "Healthy",
                                listOf(Library("Library1", "/path1"))
                            )

                            2 -> PlexServer(
                                2,
                                "PlexServer2",
                                "http://plexserver2.com",
                                "Online",
                                listOf(Library("Library2", "/path2"))
                            )

                            else -> null
                        }
                        if (plexServer != null) {
                            call.respond(plexServer)
                        } else {
                            call.respond(HttpStatusCode.NotFound, "Plex server with ID $id not found")
                        }
                    } else {
                        call.respond(HttpStatusCode.BadRequest, "Invalid Plex server ID")
                    }
                }
            }
        }

        get("subscriptions") {
            val subscriptions = listOf(
                Subscription("Subscription1", "Subject1", "http://subscription1.com", 10, "Synced"),
                Subscription("Subscription2", "Subject2", "http://subscription2.com", 5, "Not Synced")
            )
            call.respond(subscriptions)
        }

        get("videos") {
            val videos = listOf(
                Video("Video1", "Description1", "http://video1.com", "http://thumbnail1.com", "Synced"),
                Video("Video2", "Description2", "http://video2.com", "http://thumbnail2.com", "Not Synced")
            )
            call.respond(videos)
        }
    }
}

@Serializable
@Resource("/plexservers")
data class PlexServer(
    val id: Int,
    val name: String,
    val url: String,
    val healthStatus: String,
    val libraries: List<Library>
)

@Serializable
@Resource("/libraries")
data class Library(val name: String, val path: String)

@Serializable
data class Subscription(
    val title: String,
    val subject: String,
    val externalLink: String,
    val count: Int,
    val syncStatus: String
)

@Serializable
data class Video(
    val title: String,
    val description: String,
    val externalLink: String,
    val thumbnailLink: String,
    val syncStatus: String
)
