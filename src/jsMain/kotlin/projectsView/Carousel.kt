@file:JsModule("./device/carousel.jsx")
@file:JsNonModule

package projectsView

import react.FC
import react.Props

external interface CarouselProps : Props {
    var staticImages: Array<String>
    var gifImages: Array<String>
    var nextApp: Int
//    var imageWidth: Int
}

@JsName("default")
external val Carousel: FC<CarouselProps>