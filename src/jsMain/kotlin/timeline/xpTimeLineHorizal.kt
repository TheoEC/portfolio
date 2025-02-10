package timeline

import Job
import emotion.react.css
import react.FC
import react.dom.html.ReactHTML
import theme.kotlinColor
import web.cssom.AlignItems
import web.cssom.Display
import web.cssom.NamedColor
import web.cssom.Position
import web.cssom.pct
import web.cssom.px
import web.cssom.vh
import kotlin.js.Date

fun xpTimelineHorizontal(jobList: List<Job>) = FC {
    val jobs = jobList.sortedBy { it.startDate.getTime() }
    val firstJob = jobs.first()
    val totalDuration = calculateDuration(firstJob.startDate, Date())

    ReactHTML.div {
        css {
            position = Position.relative
            display = Display.flex
            flexDirection = web.cssom.FlexDirection.row
            padding = 10.px
            gap = 4.px
            alignItems = AlignItems.center
            minHeight = 100.vh
        }
        jobs.forEach { job ->
            val jobDuration = calculateDuration(job.startDate, job.endDate)
            val jobPercentage = (jobDuration / totalDuration.toDouble()) * 150
            val jobPosition = calculateDatePercentage(job.startDate) * 1

            val jobHeight = 8.px
            val jobWidth = jobPercentage.pct
            val jobTop = 50.pct
            val jobLeft = jobPosition.pct

            ReactHTML.div {
                css {
                    position = Position.absolute
                    alignItems = AlignItems.center
                    width = jobHeight
                    height = jobHeight
                    borderRadius = 8.px
                    left = jobLeft
                    top = jobTop
                    transform = "translate(-50%, -50%)".unsafeCast<web.cssom.Transform>()
                }
                ReactHTML.div {
                    css {
                        width = jobWidth
                        height = 100.pct
                        backgroundColor = NamedColor.skyblue
                        backgroundImage = kotlinColor
                    }
                }
                ReactHTML.div {
                    css {
                        paddingLeft = 8.px
                        gap = 4.px
                    }
                }
            }
        }
    }
}