package projectsView

import emotion.react.css
import react.FC
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.img
import web.cssom.Display
import web.cssom.Position
import web.cssom.Transform
import web.cssom.pct
import web.cssom.px

val Desktop = FC {
    div {
        css {
            position = Position.absolute
            display = Display.inlineBlock
            alignItems = "center".unsafeCast<web.cssom.AlignItems>()
            top = 0.px
            left = 0.px
            width = 100.pct

//            backgroundColor = NamedColor.white
//            height = 100.pct
        }
        img {
            css {
                width = 100.pct
                aspectRatio = "17/ 9".unsafeCast<web.cssom.AspectRatio>()
            }
            src = "device/laptop.png"
        }
        img {
            css {
                position = Position.absolute
                width = 76.pct
                left = 50.pct
                top = 7.pct
                transform = "translate(-50%)".unsafeCast<Transform>()
                borderRadius = 3.px
                aspectRatio = "16/ 9".unsafeCast<web.cssom.AspectRatio>()
            }
            src = "icons/apps/vector.jpg"
        }
    }
}