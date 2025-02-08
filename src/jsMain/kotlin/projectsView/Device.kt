
import emotion.react.css
import projectsView.Carousel
import projectsView.mobileProjects
import react.FC
import react.Props
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.img
import web.cssom.MediaQuery
import web.cssom.Position
import web.cssom.Transform
import web.cssom.pct

external interface MediaProps : Props {
    var currentIndex: Int
}

val Device = FC<MediaProps> { props ->
    div {
        css {
            position = Position.relative
            height = 80.pct
            aspectRatio = "9 / 20".unsafeCast<web.cssom.AspectRatio>()
            media(MediaQuery("(max-width: 650px)")) {
                height = 65.pct
            }
        }

        img {
            css {
                position = Position.absolute
                top = 3.pct
                left = 50.pct
                transform = "translateX(-50%)".unsafeCast<Transform>()
                height = 96.pct
            }
            src = "device/background.png"
        }

        div {
            css {
                position = Position.absolute
                top = 3.pct
                left = 50.pct
                aspectRatio = "9 / 20".unsafeCast<web.cssom.AspectRatio>()
                transform = "translateX(-50%)".unsafeCast<Transform>()
                height = 96.pct
            }

            Carousel {
                this.staticImages = mobileProjects.map { it.urlImage }.toTypedArray()
                this.gifImages = mobileProjects.map { it.urlGif }.toTypedArray()
                this.nextApp = props.currentIndex
            }
        }

        img {
            css {
                position = Position.absolute
                left = 50.pct
                transform = "translateX(-50%)".unsafeCast<Transform>()
                height = 100.pct
            }
            src = "pixel6.png"
        }
    }
}