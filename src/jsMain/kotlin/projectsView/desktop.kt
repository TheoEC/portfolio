package projectsView

import emotion.react.css
import react.FC
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.img
import react.dom.html.ReactHTML.video
import web.cssom.AspectRatio
import web.cssom.Display
import web.cssom.MediaQuery
import web.cssom.Position
import web.cssom.Transform
import web.cssom.pct
import web.cssom.px

val Desktop = FC {
    div {
        css {
            position = Position.relative
            display = Display.flex
            alignItems = "center".unsafeCast<web.cssom.AlignItems>()
            width = 60.pct
            aspectRatio = "17/ 9".unsafeCast<AspectRatio>()
            media(MediaQuery("(max-width: 750px)")) {
                width = 100.pct
                aspectRatio = "17/ 9".unsafeCast<AspectRatio>()
            }
        }
        img {
            css {
                display = Display.flex
                height = 100.pct
                width = 100.pct
            }
            src = "desktop/frame.png"
        }
        video {
            css {
                position = Position.absolute
                height = 84.pct
                width = 76.pct
                left = 50.pct
                top = 6.pct
                transform = "translate(-50%)".unsafeCast<Transform>()
                borderRadius = 5.px
            }
            autoPlay = true
            loop = true
            muted = true
            src = "desktop/vector.mov"
        }
    }
}