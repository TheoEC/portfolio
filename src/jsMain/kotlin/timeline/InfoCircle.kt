package timeline

import emotion.react.css
import react.FC
import react.dom.html.ReactHTML
import react.dom.html.ReactHTML.br
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.p
import theme.kotlinColor
import web.cssom.AlignItems
import web.cssom.BackgroundColor
import web.cssom.Display
import web.cssom.JustifyContent
import web.cssom.Left
import web.cssom.MediaQuery
import web.cssom.NamedColor
import web.cssom.Position
import web.cssom.Top
import web.cssom.Transform
import web.cssom.pct
import web.cssom.px
import web.cssom.vw

const val BIG_CIRCLE = 60
const val SMALL_CIRCLE = 35

fun infoCircle(
    circleLeft: Left?,
    circleTop: Top?,
    big: Boolean = true,
    icon: String = "",
    color: String = "",
    title: String = "",
    subTitle: String = "",
) = FC {
    val size = if (big) BIG_CIRCLE else SMALL_CIRCLE

    div {
        css {
            position = Position.absolute
            display = Display.flex
            alignItems = AlignItems.center
            justifyContent = JustifyContent.center
            left = circleLeft
            top = circleTop
            width = size.px
            height = size.px
            padding = 2.px
            borderRadius = 50.pct
            transform = "translate(-50%, -50%)".unsafeCast<Transform>()
            backgroundColor = color.unsafeCast<BackgroundColor>()
            backgroundImage = if (color == "") kotlinColor else null
        }

        div {
            css {
                position = Position.absolute
                left = 100.pct
                top = 0.pct
//                width = 100.pct
                height = 100.pct
                display = Display.flex
                alignItems = AlignItems.center
                justifyContent = JustifyContent.center
            }
            div {
                css {
//                    position = Position.absolute
                    display = Display.flex
                    left = 0.pct
                    top = 50.pct
                    width = 200.pct
                    height = 1.px
                    backgroundColor = NamedColor.whitesmoke
                    display = Display.flex
                    alignItems = AlignItems.center
                    justifyContent = JustifyContent.center
                    media(MediaQuery("(max-width: 850px)")) {
                        width = size.px
                    }
                }
            }
            p {
                css {
//                    position = Position.absolute
                    display = Display.flex
                    left = 210.pct
//                    top = 50.pct
                    width = 30.vw
//                    transform = "translateY(-50%)".unsafeCast<Transform>()
                }
                +title
                br()
                +subTitle
            }
        }

        if (icon != "") {
            ReactHTML.img {
                css {
                    borderRadius = "50%".unsafeCast<web.cssom.BorderRadius>()
                    width = 100.pct
                }
                src = icon
            }
        }
    }
}