
package timeline

import emotion.react.css
import jobs
import react.FC
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.span
import web.cssom.Color
import web.cssom.NamedColor
import web.cssom.Opacity
import web.cssom.Position
import web.cssom.pct
import web.cssom.px
import kotlin.js.Date

fun drawDate(date: Date) = FC {
    val yy = if (date.getTime() >= jobs.first().startDate.getTime())
        calculateDatePercentage(date).pct
    else
        0.pct
    div {
        css {
            position = Position.absolute
//            display = Display.flex
            width = 100.pct
            top = yy
        }
        div {
            css {
                width = 100.pct
                height = 1.px
                backgroundColor = NamedColor.lightgray
                opacity = 0.15.unsafeCast<Opacity>()
                fontSize = 35.px
                fontWeight = 700.unsafeCast<web.cssom.FontWeight>()
                color = NamedColor.white
            }
            +"${date.getFullYear()}"
        }
    }
}

// Função para calcular a duração em dias entre duas datas
fun calculateDuration(startDate: Date, endDate: Date): Double {
    val start = startDate.getTime()
    val end = endDate.getTime()
    val result = (end - start) / (1000 * 60 * 60 * 24)

//    console.log("Start: $start | End: $end | Result: $result")
    return result * 100
}

fun calculateDatePercentage(
    targetDate: Date,
    startDate: Date = jobs.first().startDate,
    endDate: Date = Date(),
): Double {
    val start = startDate.getTime()
    val end = endDate.getTime()
    val target = targetDate.getTime()

    if (target < start || target > end) {
        throw IllegalArgumentException("A data alvo está fora do intervalo. $startDate - $targetDate - $endDate")
    }

    val totalDuration = end - start
    val elapsedDuration = target - start

    val result = (elapsedDuration / totalDuration) * 100

//    console.log("Start: $start | End: $end | Result: $result")
    return result
}

fun developmentTime(): Period {
    val startDate = jobs.first().startDate
    val endDate = Date()

    var startYear = startDate.getFullYear()
    var startMonth = startDate.getMonth()
    var startDay = startDate.getDate()

    var endYear = endDate.getFullYear()
    var endMonth = endDate.getMonth()
    var endDay = endDate.getDate()

    // Calcular anos
    var years = endYear - startYear
    if (endMonth < startMonth || (endMonth == startMonth && endDay < startDay)) {
        years-- // Ajustar se o aniversário ainda não foi atingido neste ano
    }

    // Calcular meses
    var months = endMonth - startMonth
    if (endDay < startDay) {
        months-- // Ajustar se o mês não foi completado
    }
    if (months < 0) {
        months += 12 // Ajustar para meses negativos
    }

    // Calcular dias
    var days = endDay - startDay
    if (days < 0) {
        // Encontrar o último dia do mês anterior
        val prevMonth = (endMonth - 1 + 12) % 12
        val prevMonthYear = if (prevMonth > endMonth) endYear - 1 else endYear
        val daysInPrevMonth = Date(prevMonthYear, prevMonth + 1, 0).getDate() // Último dia do mês anterior
        days += daysInPrevMonth
    }

    return Period(years, months, days)
}

fun hightlight(text: String) = FC {
    span {
        css{
            color = Color("#f26d00")
        }
        + text
    }
}

data class Period(
    val years: Int,
    val month: Int,
    val days: Int
)