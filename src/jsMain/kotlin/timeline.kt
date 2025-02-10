
import emotion.react.css
import react.FC
import react.dom.html.ReactHTML
import react.dom.html.ReactHTML.p
import timeline.developmentTime
import timeline.hightlight
import timeline.xpTimelineVertical
import web.cssom.Display
import web.cssom.FlexDirection
import web.cssom.JustifyContent
import web.cssom.TextAlign
import web.cssom.px
import web.cssom.vw
import kotlin.js.Date

fun xpTimeline(jobList: List<Job>, vertical: Boolean = true, scale: Double = 1.2) = FC {
    timelineHeader()
    xpTimelineVertical(jobList, scale)()
}

val timelineHeader = FC {
    val period = developmentTime()

    ReactHTML.div {
        css {
            marginTop = 100.px
            marginBottom = 80.px
            fontSize = 40.px
            width = 100.vw
            display = Display.flex
            flexDirection = FlexDirection.row
            textAlign = TextAlign.center
            justifyContent = JustifyContent.center
        }
        p {
            +"Há"
            hightlight(" ${period.years} anos")()
            if (period.month > 0) {
                if (period.days > 0)
                    +", "
                else
                    +" e "
                hightlight("${period.month} meses")()
            }
            if (period.days > 0) {
                +" e "
                hightlight("${period.days} dias")()
            }
            +" gerando valor!"
        }
    }
}

data class Job(
    val name: String,
    val companyName: String,
    val startDate: Date,
    val endDate: Date,
    val urlIcon: String = "",
    val urlImage: String = "",
    val urlGif: String = "",
    val platforms: List<AppPlatforms> = emptyList(),
    val awards: List<Award> = emptyList()
)

data class Award(
    val name: String,
    val description: String,
    val date: Date,
    val urlIcon: String
)

enum class AppPlatforms {
    ANDROID,
    IOS,
    WEB,
    WINDOWS,
    MACOS,
    LINUX;

    fun isMobile() = this in listOf(ANDROID, IOS)
}

fun isMobile(job: Job) = job.platforms.any { it.isMobile() }

fun Job.isDesktop() = this.platforms.any { it in listOf(AppPlatforms.WINDOWS, AppPlatforms.MACOS) }