package presentation

import emotion.react.css
import react.FC
import react.dom.html.ReactHTML
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.img
import react_icons.RiTerminalBoxLine
import web.cssom.AlignItems
import web.cssom.Animation
import web.cssom.Background
import web.cssom.Border
import web.cssom.BorderRadius
import web.cssom.Color
import web.cssom.Display
import web.cssom.FlexDirection
import web.cssom.FlexWrap
import web.cssom.FontWeight
import web.cssom.Height
import web.cssom.JustifyContent
import web.cssom.MaxWidth
import web.cssom.MediaQuery
import web.cssom.NamedColor
import web.cssom.ObjectFit
import web.cssom.Overflow
import web.cssom.TextAlign
import web.cssom.VerticalAlign
import web.cssom.WhiteSpace
import web.cssom.Width
import web.cssom.WillChange
import web.cssom.pct
import web.cssom.px
import web.cssom.unaryMinus

val presentation = FC {
    div {
        topBar()
        centralInfo()
        bottomBar()
    }
}

val topBar = FC {
    div {
        css {
            display = Display.flex
            alignItems = "center".unsafeCast<AlignItems>()
            justifyContent = "space-between".unsafeCast<JustifyContent>()
            height = 50.px
            width = "100vw".unsafeCast<Width>()
            background =
                "linear-gradient(90deg, #6A00F4, #6A00F4, #FF8C00)".unsafeCast<Background>() // Degradê
        }
        div {
            css {
                display = Display.flex
                alignItems = "center".unsafeCast<AlignItems>()
                color = NamedColor.white
                marginLeft = 20.px
                fontSize = 20.px
                fontWeight = 600.unsafeCast<FontWeight>()
                gap = 10.px
            }
            RiTerminalBoxLine() {
                css {
                    width = 25.px
                    height = 25.px
                }
            }
            +"Theo Portela - Desenvolvedor de Software"
        }
        div {
            css {
                color = Color("white")
                marginRight = 20.px // Espaço à direita
                fontSize = 18.px
            }
//            +"Menu"
        }
    }
}

val centralInfo = FC {
    ReactHTML.div {
        css {
            display = Display.flex
            alignItems = "center".unsafeCast<AlignItems>()
            justifyContent = JustifyContent.spaceAround
            color = Color("white")
//            gap = 20.px
            height =
                "calc(100vh - 100px)".unsafeCast<Height>()
            width = "100vw".unsafeCast<Width>()
//            flex = "1 1".unsafeCast<Flex>()
            flexWrap = FlexWrap.wrapReverse
                fontSize = 40.px
            media(MediaQuery("(max-width: 850px)")) {
                flexDirection = FlexDirection.columnReverse
                alignItems = AlignItems.center
                justifyContent = JustifyContent.center
                gap = 5.pct
                textAlign = TextAlign.center
                fontSize = 30.px
            }
        }
        div {
            css {
                display = Display.flex
                width = 450.px
                maxWidth = "100%".unsafeCast<MaxWidth>()
            }
            +"Olá, me chamo Theo e desenvolvo soluções a mais de 6 anos!"
        }
//        div {
//            css {
//                display = Display.flex
//            }
            img {
                css {
                    borderRadius = "50%".unsafeCast<BorderRadius>()
                    width = 300.px
                    height = 300.px
                    maxWidth = "95%".unsafeCast<MaxWidth>()
                    objectFit = "cover".unsafeCast<ObjectFit>()
                }
                src =
                    "https://avatars.githubusercontent.com/u/32726948?s=400&u=2446d64bebbc82846c0116ff335f3f51c94eb35a&v=4"
                alt = "Foto do portfólio"
            }
//        }
    }
}

val bottomBar = FC {
    div {
        css {
            display = Display.flex
            alignItems = "center".unsafeCast<AlignItems>()
            justifyContent = "space-between".unsafeCast<JustifyContent>()
            height = 50.px // Altura da barra inferior
            width = "100vw".unsafeCast<Width>() // Largura total
            background =
                "linear-gradient(90deg, #6A00F4, #FF8C00)".unsafeCast<Background>() // Degradê
            bottom = -50.px
            overflow = "hidden".unsafeCast<Overflow>()
        }
        div {
            css {
                whiteSpace = WhiteSpace.nowrap
                willChange = "transform".unsafeCast<WillChange>()
                animation =
                    "scrollMarquee 10s linear infinite".unsafeCast<Animation>() // Animação de scroll"
            }
            repeat(4) {
                skillJava()
                Point()
                skillFirebase()
                Point()
                skillPython()
                Point()
                skillDeplhi()
                Point()
                skillKMP()
                Point()
            }
        }
    }
}

val Point = FC {
    val size = 6.px
    div {
        css {
            display = Display.inlineFlex
            width = size
            height = size
            marginLeft = 10.px
            marginRight = 10.px
            borderRadius = 50.pct
            backgroundColor = NamedColor.white
        }
    }
}

fun showSkill(
    name: String,
    leftIcons: List<String> = emptyList(),
    rightIcons: List<String> = emptyList(),
    highlight: Boolean = false
) = FC {
    div {
        css {
            display = Display.inlineFlex
            border = if (highlight) "1px solid white".unsafeCast<Border>() else null
            paddingInline = 10.px
            paddingTop = 5.px
            paddingBottom = 5.px
            alignItems = "center".unsafeCast<AlignItems>()
            verticalAlign = "middle".unsafeCast<VerticalAlign>()
            borderRadius = 18.px
            textAlign = "center".unsafeCast<TextAlign>()
            gap = 6.px
            fontSize = 20.px
        }

        for (icon in leftIcons) {
            img {
                css {
                    width = ICON_SIZE
                    height = ICON_SIZE
                }
                src = icon
            }
        }
        +name
        for (icon in rightIcons) {
            img {
                css {
                    width = ICON_SIZE
                    height = ICON_SIZE
                }
                src = icon
            }
        }
    }
}