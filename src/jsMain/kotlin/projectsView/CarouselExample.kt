package projectsView

import emotion.react.css
import react.FC
import react.Props
import react.dom.html.ReactHTML
import react.useState
import web.cssom.AlignItems
import web.cssom.Display
import web.cssom.FlexDirection
import web.cssom.JustifyContent
import web.cssom.Position
import web.cssom.pct
import web.cssom.px

val carouselShow = FC<Props> {
    // Lista de imagens
    // Estado para controlar o índice da imagem central (ou à esquerda)
    val (currentIndex, setCurrentIndex) = useState(0)

    ReactHTML.div {
        css {
            position = Position.relative
            flexDirection = FlexDirection.column
            height = 1000.px
        }
        ReactHTML.div {
            css {
//                position = Position.relative
                height = 80.pct
                aspectRatio = "9 / 20".unsafeCast<web.cssom.AspectRatio>()
            }
            Carousel() {
                this.staticImages = projectList.map { it.urlImage }.toTypedArray()
                this.gifImages = projectList.map { it.urlGif }.toTypedArray()
                this.nextApp = currentIndex
            }
        }

        // Botões para alterar a imagem
        ReactHTML.div {
            css {
                display = Display.inlineFlex
                flexDirection = FlexDirection.row
                alignItems = AlignItems.center
                justifyContent = JustifyContent.center
            }
            ReactHTML.button {
                +"Anterior"
                onClick = { if (currentIndex > 0) setCurrentIndex(currentIndex - 1) }
            }
            ReactHTML.button {
                +"Próximo"
                onClick =
                    { if (currentIndex < projectList.size - 1) setCurrentIndex(currentIndex + 1) }
            }
        }
    }
}
