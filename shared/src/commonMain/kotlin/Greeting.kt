import Component.RocketComponent
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.random.Random
import kotlin.time.Duration.Companion.seconds

class Greeting {
    private val rocketComponent = RocketComponent()

    var url = "https://farm5.staticflickr.com/4599/38583829295_581f34dd84_b.jpg"

    fun greet(): Flow<String> = flow{
        emit(if (Random.nextBoolean()) "Hello" else "Hi")
        delay(1.seconds)
        emit("Adivinha o que é isso!")
        delay(1.seconds)
        emit(daysPhrasent())
        emit(rocketComponent.launchPhrase())
    }

}
