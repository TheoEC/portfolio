package timeline

import Job
import emotion.react.css
import react.FC
import react.dom.html.ReactHTML.div
import theme.kotlinColor
import web.cssom.AlignItems
import web.cssom.Display
import web.cssom.MediaQuery
import web.cssom.NamedColor
import web.cssom.Position
import web.cssom.Transform
import web.cssom.pct
import web.cssom.px
import web.cssom.vh
import kotlin.js.Date

val smallDevicesLeft = 35.pct

fun xpTimelineVertical(jobList: List<Job>, scale: Double = 1.0) = FC {
    val jobs = jobList.sortedBy { it.startDate.getTime() }
    val totalDuration = calculateDuration(jobs.first().startDate, Date())

    div {
        css {
            position = Position.relative
            display = Display.flex
            flexDirection = web.cssom.FlexDirection.column
            gap = 4.px
            alignItems = AlignItems.center
            minHeight = (100 * scale).vh
        }
        for (year in jobs.first().startDate.getFullYear()..Date().getFullYear())
            drawDate(Date(year = year, month = 0, day = 1))()
        div {
            css {
                position = Position.absolute
                width = 8.px
                height = 100.pct
                backgroundColor = NamedColor.skyblue
                backgroundImage = kotlinColor
                media(MediaQuery("(max-width: 450px)")){
                    left = smallDevicesLeft
                    transform = "translateX(-50%)".unsafeCast<Transform>()
                }
            }
        }
        jobs.forEach { job ->
            renderJob(job, totalDuration)()
        }


    }
}