package timeline

import Job
import emotion.react.css
import react.FC
import react.dom.html.ReactHTML.div
import react.useState
import theme.getGradientColorAt
import web.cssom.Display
import web.cssom.Flex
import web.cssom.NamedColor
import web.cssom.pct
import kotlin.js.Date

fun renderJob(job: Job, totalDuration: Double) = FC {
    val jobDuration = calculateDuration(job.startDate, job.endDate)
    val jobPercentage = (jobDuration / totalDuration) * 100
    val jobPosition = calculateDatePercentage(job.startDate) * 1

    val jobTop = jobPosition.pct

    var middlePoint = 1 - (jobPosition + jobPercentage / 2) / 100
    var color = getGradientColorAt(middlePoint)

    val (isHovered, setHovered) = useState(false)



    infoCircle(
        jobTop,
        true,
        job.urlIcon,
        color,
        "${job.name} ",
        "${job.companyName}, ${formatToMonthYear(job.startDate)}",
        infoOnLeft = true,
    )()

    for (award in job.awards) {
        val awardPosition = calculateDatePercentage(award.date)
        middlePoint = 1 - (awardPosition) / 100
        color = getGradientColorAt(middlePoint)
        infoCircle(
            awardPosition.pct,
            false,
            award.urlIcon,
            color,
            award.name,
            formatToMonthYear(award.date),
            infoOnLeft = false,
        )()
    }

    div {
        css {
//            position = Position.absolute
//            left = 0.pct
//            top = jobPosition.pct
//            height = jobPercentage.pct
//            width = 100.pct
            display = Display.flex
            backgroundColor = NamedColor.red
            flex = "1 1".unsafeCast<Flex>()
        }
    }
}

fun formatToMonthYear(date: Date): String {
    val options = js("({ month: 'short', year: 'numeric' })") // Configura o formato desejado
    return date.toLocaleDateString("en-US", options)
}