package theme

import kotlinx.browser.document
import org.khronos.webgl.get
import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLCanvasElement

val kotlinColor = "linear-gradient(45deg, #7f52ff, #f26d00)".unsafeCast<web.cssom.BackgroundImage>()

fun getGradientColorAt(percent: Double, alpha: Double = 1.0): String {
    val canvas = document.createElement("canvas") as HTMLCanvasElement
    val context = canvas.getContext("2d") as CanvasRenderingContext2D

    // Definir dimensões do canvas
    canvas.width = 1 // Largura de 1px (não precisa mais)
    canvas.height = 100 // Altura de 100px (ou qualquer escala)

    // Criar o gradiente linear
    val gradient = context.createLinearGradient(0.0, 0.0, 0.0, canvas.height.toDouble())
    gradient.addColorStop(0.0, "#7f52ff") // Cor inicial (vermelho)
    gradient.addColorStop(1.0, "#f26d00") // Cor final (azul)

    // Preencher o canvas com o gradiente
    context.fillStyle = gradient
    context.fillRect(0.0, 0.0, canvas.width.toDouble(), canvas.height.toDouble())

    // Posição da altura desejada (percent é 0.35 para 35%)
    val positionY = (canvas.height * percent).toInt()

    // Obter os dados da cor na posição
    val imageData = context.getImageData(0.0, positionY.toDouble(), 1.0, 1.0).data
    val r = imageData[0]
    val g = imageData[1]
    val b = imageData[2]
    val a = imageData[3]

    console.log("rgba($r, $g, $b, $alpha)")
    return "rgba($r, $g, $b, $alpha)"
}