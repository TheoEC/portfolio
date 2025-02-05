@file:JsModule("@tsamantanis/react-glassmorphism")
@file:JsNonModule


import react.FC
import react.PropsWithChildren

external interface CustomCardProps : PropsWithChildren {
    var effectColor: String
    var color: String
    var blur: Number
    var borderRadius: Number
}

@JsName("CustomCard")
external val CustomCard: FC<CustomCardProps>