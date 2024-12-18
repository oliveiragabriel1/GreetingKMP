package Component

import Models.RocketLaunch
import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import io.ktor.client.request.*
import io.ktor.client.call.*
import io.ktor.client.statement.readBytes
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class RocketComponent {
    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }

    private suspend fun getDateOfLastSuccessfulLaunch(): String {
        val rockets: List<RocketLaunch> = httpClient.get("https://api.spacexdata.com/v4/launches").body()
        val lastSuccessfulLaunch = rockets.last { it.launchSuccess == true }
        val date = Instant.parse(lastSuccessfulLaunch.launchDateUTC)
            .toLocalDateTime(TimeZone.currentSystemDefault())

        return "${date.month} ${date.dayOfMonth}, ${date.year}"
    }

    suspend fun launchPhrase(): String =
        try {
            "O ultimo lancamento foi no dia ${getDateOfLastSuccessfulLaunch()}"
        } catch (e: Exception) {
            "Erro ao obter a data do ultimo lancamento: ${e.message}"
        }

}

