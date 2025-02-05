

package presentation

//import react-icons.fa.FaReact
import emotion.react.css
import react.FC
import react.dom.html.ReactHTML
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.img
import react.dom.html.ReactHTML.span
import web.cssom.AlignItems
import web.cssom.Animation
import web.cssom.Background
import web.cssom.Border
import web.cssom.BorderRadius
import web.cssom.Color
import web.cssom.Display
import web.cssom.FontWeight
import web.cssom.Height
import web.cssom.JustifyContent
import web.cssom.NamedColor
import web.cssom.Overflow
import web.cssom.TextAlign
import web.cssom.VerticalAlign
import web.cssom.WhiteSpace
import web.cssom.Width
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
            RiTerminalBoxLine(){
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
            +"Menu"
        }
    }
}

val centralInfo = FC {
    ReactHTML.div {
        css {
            display = Display.flex
            alignItems = "center".unsafeCast<AlignItems>()
            justifyContent = "center".unsafeCast<JustifyContent>()
            color = Color("white") // Texto em branco para contraste
            gap = 20.px
            height =
                "calc(100vh - 100px)".unsafeCast<Height>() // Ocupa toda a altura da tela
            width = "100vw".unsafeCast<Width>()   // Ocupa toda a largura da tela
        }
        div {
            css {
                fontSize = 40.px
                width = 450.px
            }
            +"Olá, sou Theo, desenvolvo soluções a mais de 4 anos!"
        }
        img {
            css {
                borderRadius = "50%".unsafeCast<BorderRadius>()
            }
            src =
                "https://avatars.githubusercontent.com/u/32726948?s=400&u=2446d64bebbc82846c0116ff335f3f51c94eb35a&v=4"
            width = 400.0
            height = 400.0
            alt = "Foto do portfólio"
        }
    }
}

val bottomBar = FC {
    div {
        css {
            display = Display.flex
            alignItems = "center".unsafeCast<AlignItems>()
            justifyContent = "space-between".unsafeCast<JustifyContent>()
            height = 50.px // Altura da barra inferior
            width = "300vw".unsafeCast<Width>() // Largura total
            background =
                "linear-gradient(90deg, #6A00F4, #FF8C00)".unsafeCast<Background>() // Degradê
//            position = web.cssom.Position.fixed // Fixa no rodapé
            bottom = -50.px // Posicionada na parte inferior
            overflow = "hidden".unsafeCast<Overflow>()

        }
        div {
            skillJava()
            span {
                css { marginInline = 10.px }
                +"•" // Ponto de separação
            }
            skillFirebase()
            span {
                css { marginInline = 10.px }
                +"•" // Ponto de separação
            }
            skillPython()
            span {
                css { marginInline = 10.px }
                +"•" // Ponto de separação
            }
            skillDeplhi()
            span {
                css { marginInline = 10.px }
                +"•" // Ponto de separação
            }
            skillKMP()
        }
    }
}

fun skillBar(skills: List<Any>) = FC {
    div {
        css {
            width = "100vw".unsafeCast<Width>()
            display = Display.inlineFlex
            alignItems = "center".unsafeCast<AlignItems>()
            gap = 10.px // Espaçamento entre os ícones
//                    marginLeft = 20.px
            animation =
                "scroll 20s linear infinite".unsafeCast<Animation>() // Animação de scroll
            whiteSpace =
                "nowrap".unsafeCast<WhiteSpace>() // Evita quebra de linha
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
        + name
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