package timeline

import Job
import emotion.react.css
import kotlinx.browser.window
import org.w3c.dom.events.Event
import react.FC
import react.dom.html.ReactHTML.div
import react.useEffect
import react.useRef
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
    val containerRef = useRef<web.html.HTMLDivElement>(null)

    useEffect(emptyList<Any>()) {
        console.log("useEffect")
        val scrollHandler: (Event) -> Unit = {
            containerRef.current?.let { element ->
                val rect = element.getBoundingClientRect()
                val isVisible = rect.top < window.innerHeight// && rect.bottom > 0
                setHovered(isVisible)
            }
        }
        window.addEventListener("scroll", scrollHandler)
        // Retorne uma lambda de cleanup:
//        return@useEffect window.removeEventListener("scroll", scrollHandler)
    }

    infoCircle(
        jobTop,
        true,
        job.urlIcon,
        color,
        "${job.name} ",
        "${job.companyName}, ${formatToMonthYear(job.startDate)}",
        infoOnLeft = true,
        expand = isHovered
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
            expand = isHovered
        )()
    }

    div {
        ref = containerRef
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