
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
        xpTimeline()()
        ProjectsViews()
        SpeedInsights()

        ContactButtons()
    }
}



inline fun <reified T> Any?.smartCast(): T? = this as? T