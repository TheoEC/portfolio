package projectsView

import emotion.react.css
import react.FC
import react.dom.html.ReactHTML
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.img
import web.cssom.AspectRatio
import web.cssom.Position
import web.cssom.Transform
import web.cssom.pct

fun ShowDevice(currentIndex: Int) = FC {
    div {
        css {
            position = Position.relative
            height = 80.pct
            aspectRatio = "9 / 20".unsafeCast<AspectRatio>()
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

        ReactHTML.div {
            css {
                position = Position.absolute
                top = 3.pct
                left = 50.pct
                aspectRatio = "9 / 20".unsafeCast<AspectRatio>()
                transform = "translateX(-50%)".unsafeCast<Transform>()
                height = 96.pct
//                    backgroundColor = NamedColor.blue
            }
            Carousel() {
                this.staticImages = projectList.map { it.urlImage }.toTypedArray()
                this.gifImages = projectList.map { it.urlGif }.toTypedArray()
                this.nextApp = currentIndex
//                    this.imageWidth = 200
            }
        }

        ReactHTML.img {
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
