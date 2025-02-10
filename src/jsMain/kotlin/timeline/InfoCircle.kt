package timeline

import emotion.css.keyframes
import emotion.react.css
import kotlinx.browser.window
import org.w3c.dom.events.Event
import react.FC
import react.Props
import react.dom.html.ReactHTML
import react.dom.html.ReactHTML.br
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.p
import react.useEffect
import react.useRef
import react.useState
import theme.kotlinColor
import web.cssom.AlignItems
import web.cssom.BackgroundColor
import web.cssom.Display
import web.cssom.FlexDirection
import web.cssom.JustifyContent
import web.cssom.MediaQuery
import web.cssom.NamedColor
import web.cssom.Position
import web.cssom.TextAlign
import web.cssom.TextWrap
import web.cssom.Top
import web.cssom.Transform
import web.cssom.Width
import web.cssom.WillChange
import web.cssom.pct
import web.cssom.px

const val BIG_CIRCLE = 60
const val SMALL_CIRCLE = 35

external interface ifProps : Props {
    var expand: Boolean
}

val scaleIn = keyframes {
    from {
        transform = "scaleX(0)".unsafeCast<Transform>()
    }
    to {
        transform = "scaleX(1)".unsafeCast<Transform>()
    }
}

val scaleOut = keyframes {
    from {
        transform = "scaleX(1)".unsafeCast<Transform>()
    }
    to {
        transform = "scaleX(0)".unsafeCast<Transform>()
    }
}

fun infoCircle(
    circleTop: Top?,
    big: Boolean = true,
    icon: String = "",
    color: String = "",
    title: String = "",
    subTitle: String = "",
    infoOnLeft: Boolean = false,
) = FC {
    val size = if (big) BIG_CIRCLE else SMALL_CIRCLE
    val (expand, setExpand) = useState(true)
    val containerRef = useRef<web.html.HTMLDivElement>(null)

    useEffect(emptyList<Any>()) {
        console.log("useEffect")
        val scrollHandler: (Event) -> Unit = {
            containerRef.current?.let { element ->
                val rect = element.getBoundingClientRect()
                val isVisible = rect.top < window.innerHeight// && rect.bottom > 0
                setExpand(isVisible)
            }
        }
        window.addEventListener("scroll", scrollHandler)
    }

    div {
        ref = containerRef
        css {
            position = Position.absolute
            display = Display.flex
            alignItems = AlignItems.center
            justifyContent = JustifyContent.center
            left = 50.pct
            top = circleTop
            width = size.px
            height = size.px
            padding = 2.px
            borderRadius = 50.pct
            transform = "translateZ(0) translate(-50%, -50%)".unsafeCast<Transform>()
            willChange = "transform".unsafeCast<WillChange>()
            backgroundColor = color.unsafeCast<BackgroundColor>()
            backgroundImage = if (color == "") kotlinColor else null
            media(MediaQuery("(max-width: 450px)")) {
                left = smallDevicesLeft
            }
        }

        div {
            css {
                position = Position.absolute
                if (infoOnLeft) {
                    right = 100.pct
                    flexDirection = FlexDirection.rowReverse
                    transformOrigin = "right center".unsafeCast<web.cssom.TransformOrigin>()
                } else {
                    transformOrigin = "left center".unsafeCast<web.cssom.TransformOrigin>()
                    flexDirection = FlexDirection.row
                    left = 100.pct
                }
                gap = 5.px
                top = 0.pct
                height = 100.pct
                display = Display.flex
                alignItems = AlignItems.center
                justifyContent = JustifyContent.center
                textWrap = TextWrap.nowrap
                willChange = "transform".unsafeCast<WillChange>()
                animation = expand?.let {
                    val timing = " 0.75s ease-in-out 0.1s both"
                    if (expand)
                        "$scaleIn $timing".unsafeCast<web.cssom.Animation>()
                    else
                        "$scaleOut $timing".unsafeCast<web.cssom.Animation>()
                }
                media(MediaQuery("(max-width: 750px)")) {
                    textWrap = TextWrap.wrap
                }
            }
            div {
                css {
                    display = Display.flex
                    left = 0.pct
                    top = 50.pct
                    width = 100.px
                    height = 1.px
                    backgroundColor = NamedColor.whitesmoke
                    alignItems = AlignItems.center
                    justifyContent = JustifyContent.center
                    media(MediaQuery("(max-width: 850px)")) {
                        width = size.px
                    }
                }
            }
            p {
                css {
                    display = Display.flex
                    left = 210.px
                    if (infoOnLeft)
                        textAlign = TextAlign.right
                    width = "auto".unsafeCast<Width>()
//                    overflow = Overflow.hidden
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