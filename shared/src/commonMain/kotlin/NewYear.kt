import kotlinx.datetime.*

fun daysUntilNewYear(): Int {
    val today = Clock.System.todayIn(TimeZone.currentSystemDefault())
    val closesNewYear = LocalDate(today.year + 1, 1, 1)
    return today.daysUntil(closesNewYear)
}
fun daysPhrasent(): String = "Falta apenas ${daysUntilNewYear()} dias para o fim do ano! \uD83C\uDF86"