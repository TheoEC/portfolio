import csstype.PropertiesBuilder
import emotion.css.keyframes
import emotion.react.css
import kotlinx.browser.window
import react.FC
import react.Props
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.div
import react.useState
import react_icons.AiOutlineMail
import react_icons.RiWhatsappLine
import web.cssom.AlignItems
import web.cssom.AspectRatio
import web.cssom.BackgroundColor
import web.cssom.Border
import web.cssom.Cursor
import web.cssom.Display
import web.cssom.JustifyContent
import web.cssom.NamedColor
import web.cssom.Position
import web.cssom.TextSizeAdjust
import web.cssom.Transform
import web.cssom.pct
import web.cssom.px

val spaceBetween = 10.px

fun moveIn(position: Int) = keyframes {
    from {
        transform = "translateY(0%)".unsafeCast<Transform>()
    }
    to {
        transform = "translateY(calc(${100 * position}% + ${spaceBetween}))".unsafeCast<Transform>()
    }
}

fun moveOut(position: Int) = keyframes {
    from {
        transform = "translateY(calc(${100 * position}% + ${spaceBetween}))".unsafeCast<Transform>()
    }
    to {
        transform = "translateY(0%)".unsafeCast<Transform>()
    }
}

val timingIn = "0.5s ease-in-out 0.5s both"
val timingOut = "0.5s ease-in-out both"

val buttonSize = 60

external interface BtnProps : Props {
    var expand: Boolean
    var position: Int
}

val ContactButtons = FC {
    val (expand, setExpand) = useState(false)
    div {
        css {
            position = Position.fixed
            width = buttonSize.px
            height = "auto".unsafeCast<web.cssom.Height>()
//            height = 120.px
            aspectRatio = 1.unsafeCast<AspectRatio>()
            right = 10.px
            bottom = 55.px
            opacity = 0.5.unsafeCast<web.cssom.Opacity>()
            hover {
                opacity = 1.unsafeCast<web.cssom.Opacity>()
            }
        }
        onMouseEnter = { setExpand(true) }
        onMouseLeave = { setExpand(false) }

//        EmailButton {
//            this.expand = expand
//            this.position = 2
//        }
        WhatsAppButton {
            this.expand = expand
            this.position = 1
        }
        LinkedInButton()
    }
}


fun PropertiesBuilder.commonButtonStyle(): Unit {
    position = Position.relative
    display = Display.flex
    width = buttonSize.px
    aspectRatio = 1.unsafeCast<AspectRatio>()
    borderRadius = 50.pct
    border = "1px solid white".unsafeCast<Border>()
    bottom = 0.px
    right = 0.px
    textSizeAdjust = "teste".unsafeCast<TextSizeAdjust>()
    fontSize = 30.px
    color = NamedColor.white
    cursor = "pointer".unsafeCast<Cursor>()
    alignItems = AlignItems.center
    justifyContent = JustifyContent.center
}


// EmailButton: usa o estilo comum, com background e animação específicos
val EmailButton = FC<BtnProps> { props ->
    button {
        css {
            commonButtonStyle()
            backgroundColor = NamedColor.black
            animation = if (props.expand)
                "${moveOut(props.position)} $timingOut".unsafeCast<web.cssom.Animation>()
            else
                "${moveIn(props.position)} $timingIn".unsafeCast<web.cssom.Animation>()
        }
        AiOutlineMail {
            css {
                width = 25.px
                height = 25.px
            }
        }
    }
}

// WhatsAppButton: semelhante ao EmailButton, com seu background e ícone
val WhatsAppButton = FC<BtnProps> { props ->
    button {
        css {
            commonButtonStyle()
            marginBottom = spaceBetween
            backgroundColor = NamedColor.green
            animation = if (props.expand)
                "${moveOut(props.position)} $timingOut".unsafeCast<web.cssom.Animation>()
            else
                "${moveIn(props.position)} $timingIn".unsafeCast<web.cssom.Animation>()
        }
        onClick = {
            val newTab = window.open("https://wa.me/+5583987448250", "_blank")
            console.log("foi")
            newTab?.focus()
        }
        RiWhatsappLine {
            css {
                width = 30.px
                height = 30.px
            }
        }
    }
}

// LinkedInButton: não anima e usa o estilo comum com background específico
val LinkedInButton = FC {
    button {
        css {
            commonButtonStyle()
            backgroundColor = "rgb(10, 102, 194, 1)".unsafeCast<BackgroundColor>()
        }

        onClick = {
            val newTab = window.open("https://www.linkedin.com/in/theo-portela/", "_blank")
            newTab?.focus()
        }
        +"In"
    }
}