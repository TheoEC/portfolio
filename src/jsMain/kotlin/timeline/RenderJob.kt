package timeline

import Job
import LINE_HEIGHT
import react.FC
import react.useState
import theme.getGradientColorAt
import web.cssom.pct
import web.cssom.px
import kotlin.js.Date

fun renderJob(job: Job, totalDuration: Double, vertical: Boolean) = FC {
    val jobDuration = calculateDuration(job.startDate, job.endDate)
    val jobPercentage = (jobDuration / totalDuration) * 100
    val jobPosition = calculateDatePercentage(job.startDate) * 1

    val jobHeight = if (vertical) jobPercentage.pct else LINE_HEIGHT.px
    val jobWidth = if (vertical) LINE_HEIGHT.px else jobPercentage.pct

    val jobTop = if (vertical) jobPosition.pct else 50.pct
    val jobLeft = if (vertical) 50.pct else jobPosition.pct

    var middlePoint = 1 - (jobPosition + jobPercentage / 2) / 100
    var color = getGradientColorAt(middlePoint)

    val (isHovered, setHovered) = useState(false)

    infoCircle(
        jobLeft,
        jobTop,
        true,
        job.urlIcon,
        color,
        "${job.name} ",
        "${job.companyName}, ${formatToMonthYear(job.startDate)}",
    )()

    for (award in job.awards) {
        val awardPosition = calculateDatePercentage(award.date)
        middlePoint = 1 - (awardPosition) / 100
        color = getGradientColorAt(middlePoint)
        infoCircle(
            jobLeft,
            awardPosition.pct,
            false,
            award.urlIcon,
            color,
            award.name,
            formatToMonthYear(award.date),
        )()
    }

}

fun formatToMonthYear(date: Date): String {
    val options = js("({ month: 'short', year: 'numeric' })") // Configura o formato desejado
    return date.toLocaleDateString("en-US", options)
}