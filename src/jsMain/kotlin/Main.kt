import emotion.react.css
import presentation.presentation
import projectsView.ProjectsViews
import react.FC
import react.create
import react.dom.client.createRoot
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h1
import react.dom.html.ReactHTML.style
import web.cssom.Color
import web.cssom.Display
import web.cssom.FlexDirection
import web.cssom.px
import web.dom.document
import kotlin.js.Date

fun main() {
    val root = document.getElementById("root") ?: error("No root element found")
    createRoot(root).render(App.create())
}

val App = FC {
    style {
        +"body { margin: 0; padding: 0; background-color: black; color: white; font-family: Arial, sans-serif; }"
        +"@keyframes scroll { 0% { transform: translateX(0%); } 100% { transform: translateX(-200%); } }"
        +"@keyframes scrollMarquee { 0% { transform: translateX(0%); } 100% { transform: translateX(-50%); } }"
    }
    div {
        css {
            display = Display.flex
            flexDirection = FlexDirection.column
            "*" {
                margin = 0.px
                padding = 0.px
            }
            backgroundColor = Color("black")
            width = "100vw".unsafeCast<web.cssom.Width>()   // Largura total
        }
        h1 {
            css {
                color = Color("white") // Texto em branco para contraste
            }
            +" "
        }
        presentation()
        xpTimeline(jobs)()
        ProjectsViews()
        SpeedInsights()
    }
}

val jobs = listOf(
    Job(
        name = "Desenvolvedor de Software",
        companyName = "Mandacaru Store",
        endDate = Date(),
        startDate = Date(year = 2024, month = 10, day = 1),
        urlIcon = "icons/mandacaru.png",
        urlGif = "device/mandacaru.webm",
        urlImage = "device/background.png",
        platforms = listOf(AppPlatforms.ANDROID, AppPlatforms.IOS)
    ),
    Job(
        name = "Desenvolvedor de Software",
        companyName = "AcquaCare",
        endDate = Date(year = 2024, month = 7, day = 31),
        startDate = Date(year = 2024, month = 3, day = 30),
        urlIcon = "icons/apps/acquacare.png",
        urlGif = "device/acquacare.webm",
        urlImage = "device/acquacare.png",
        platforms = listOf(AppPlatforms.ANDROID)
    ),
    Job(
        name = "Desenvolvedor de Software",
        companyName = "Nelogica",
        endDate = Date(year = 2024, month = 3, day = 30),
        startDate = Date(year = 2021, month = 10, day = 16),
        urlIcon = "/icons/nelogica.jpg",
        urlImage = "icons/apps/vector.jpg",
        urlGif = "desktop/vector.mov",
        platforms = listOf(AppPlatforms.WINDOWS),
        awards = listOf(
            Award(
                name = "1º Lugar NeloAwards",
                description = "Premiação entregas de maior valor de 2023.",
                date = Date(year = 2023, month = 11, day = 1),
                urlIcon = "/icons/1place.png"
            ),
            Award(
                name = "3º Lugar NeloAwards",
                description = "Premiação entregas de maior valor doe 2022.",
                date = Date(year = 2022, month = 11, day = 1),
                urlIcon = "/icons/3place.png"
            ),
        )
    ),
    Job(
        name = "Desenvolvedor de Software",
        companyName = "Katze",
        endDate = Date(year = 2021, month = 10, day = 16),
        startDate = Date(year = 2020, month = 0, day = 1),
        urlIcon = "icons/katze.jpg"
    ),
//    Job(
//        name = "Desenvolvedor de Software",
//        companyName = "Fiep",
//        endDate = Date(year = 2019, month = 11, day = 31),
//        startDate = Date(year = 2019, month = 5, day = 1),
//        awards = listOf(
//            Award(
//                name = "Financiamento",
//                description = "Na apresentação do projeto finalizado, a empresa recebeu R$80K em financiamento",
//                date = Date(year = 2019, month = 11, day = 1),
//                urlIcon = "/icons/award.png"
//            )
//        )
//    ),
    Job(
        name = "Analista de dados",
        companyName = "GComPI",
        endDate = Date(year = 2019, month = 11, day = 31),
        startDate = Date(year = 2018, month = 3, day = 1),
        urlIcon = "icons/gcompi.png",
        awards = listOf(
            Award(
                name = "Publicação de artigo científico",
                description = "Publicação de artigo científico",
                date = Date(year = 2019, month = 0, day = 31),
                urlIcon = "/icons/article.png"
            )
        )
    ),
).sortedBy { it.startDate.getTime() }

inline fun <reified T> Any?.smartCast(): T? = this as? T