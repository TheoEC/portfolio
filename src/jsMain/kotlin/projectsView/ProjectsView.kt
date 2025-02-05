package projectsView

import AppPlatforms
import Job
import emotion.react.css
import jobs
import react.FC
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.img
import react.useState
import web.cssom.AlignItems
import web.cssom.BackdropFilter
import web.cssom.Border
import web.cssom.BoxShadow
import web.cssom.Cursor
import web.cssom.Display
import web.cssom.FlexDirection
import web.cssom.FlexWrap
import web.cssom.JustifyContent
import web.cssom.MediaQuery
import web.cssom.NamedColor
import web.cssom.ObjectFit
import web.cssom.Position
import web.cssom.TextAlign
import web.cssom.Transform
import web.cssom.pct
import web.cssom.px
import web.cssom.vh
import web.cssom.vw

data class Project(
    val urlGif: String,
    val urlImage: String,
)

val acquacare = Project(
    urlGif = "device/acquacare.webm",
    urlImage = "device/acquacare.png"
)

val notes = Project(
    urlGif = "device/gif2.gif",
    urlImage = "device/background.png"
)

val projectList = jobs.filter { job -> job.platforms.isNotEmpty() }
val mobileProjects = jobs.filter { job -> job.platforms.any { it.isMobile() } }

val currentMobileProject: Job = mobileProjects.first()


val ProjectsViews = FC {
    val (currentIndex, setCurrentIndex) = useState(1)
    val (currentMobileIndex, setCurrentMobileIndex) = useState(1)
    val currentProject = projectList[currentIndex]

    val mobileIndex = mobileProjects.indexOf(currentProject)
    if (mobileIndex != -1 && mobileIndex != currentMobileIndex)
        setCurrentMobileIndex(mobileProjects.indexOf(currentProject))

    div {
        css {
            display = Display.flex
            flexDirection = FlexDirection.row
            alignItems = AlignItems.center
            justifyContent = JustifyContent.center
            width = 100.vw
            height = 100.vh
        }
        div {
            css {
                display = Display.inlineFlex
                flexDirection = FlexDirection.row
                alignItems = AlignItems.center
                justifyContent = JustifyContent.spaceAround
                width = 85.pct
                height = 85.pct
                backdropFilter = "blur( 10px )".unsafeCast<BackdropFilter>()
                borderRadius = 25.px
                border = "1px solid rgba( 255, 255, 255, 0.3 )".unsafeCast<Border>()
                boxShadow =
                    "inset 0px 0px 200px 0px rgba( 255, 255, 255, 0.18 )".unsafeCast<BoxShadow>()
                flexWrap = FlexWrap.wrapReverse
                media(MediaQuery("(max-width: 650px)")) {
                    flexDirection = FlexDirection.columnReverse
                }
            }

            div {
                css {
                    position = Position.relative
                    display = Display.grid
                    gridTemplateColumns = "1fr 1fr".unsafeCast<web.cssom.GridTemplateColumns>()
                    gap = 10.pct
                }
                for (project in projectList) {
                    val isCurrent = projectList[currentIndex] == project
                    div {
                        css {
                            display = Display.flex
                            flexDirection = FlexDirection.column
                            alignItems = AlignItems.center
                            gap = 5.px
                            color =
                                if (projectList[currentIndex] == project) NamedColor.white else NamedColor.gray
                            textAlign = TextAlign.center
                        }
                        button {
                            css {
                                width = 70.px              // 4px maior que a imagem
                                height = 70.px             // 4px maior que a imagem
                                padding = 5.px
                                border = "none".unsafeCast<Border>()
                                backgroundColor = NamedColor.transparent
                                borderRadius = 50.pct
                                cursor = "pointer".unsafeCast<Cursor>()
                                display = Display.flex
                                alignItems = AlignItems.center
                                justifyContent = JustifyContent.center
                                boxShadow = if (isCurrent)
                                    "inset 0px 0px 10px 0px rgba( 255, 255, 255, 0.7 )".unsafeCast<BoxShadow>()
                                else
                                    null
                                // Efeito de hover
                                hover {
                                    if (!isCurrent) {
                                        boxShadow =
                                            "inset 0px 0px 10px 0px rgba( 255, 255, 255, 0.5 )".unsafeCast<BoxShadow>()
                                    }
                                }
                            }
                            onClick = {
                                setCurrentIndex(projectList.indexOf(project))
                            }
                            img {
                                src = project.urlIcon
                                css {
                                    width = 100.pct
                                    height = 100.pct
                                    borderRadius = 50.pct
                                    objectFit = ObjectFit.cover
                                }
                            }
                        }
                        +project.companyName
                    }
                }
            }

            if (currentProject.platforms.any {
                    it in listOf(
                        AppPlatforms.WINDOWS,
                        AppPlatforms.MACOS
                    )
                }) {
                console.log("entrou errado")
                div {
                    css {
                        display = Display.inlineBlock
                        position = Position.absolute
                        width = 40.pct
                        left = 75.pct
                        top = 25.pct
                        transform = "translate(-50%, 50%)".unsafeCast<Transform>()
                    }
                    Desktop()
                }
            } else
                div {
                    css {
                        position = Position.relative
//                        left = 75.pct
//                        transform = "translateX(-50%)".unsafeCast<Transform>()
                        height = 80.pct
                        aspectRatio = "9 / 20".unsafeCast<web.cssom.AspectRatio>()
                        media(MediaQuery("(max-width: 650px)")) {
                            height = 65.pct
                        }
                    }

                    img {
                        css {
                            position = Position.absolute
                            top = 3.pct
                            left = 50.pct
                            transform = "translateX(-50%)".unsafeCast<Transform>()
                            height = 96.pct
                        }
                        src = "device/background.png"
                    }

                    div {
                        css {
                            position = Position.absolute
                            top = 3.pct
                            left = 50.pct
                            aspectRatio = "9 / 20".unsafeCast<web.cssom.AspectRatio>()
                            transform = "translateX(-50%)".unsafeCast<Transform>()
                            height = 96.pct
                        }

                        Carousel {
                            this.staticImages = mobileProjects.map { it.urlImage }.toTypedArray()
                            this.gifImages = mobileProjects.map { it.urlGif }.toTypedArray()
                            this.nextApp = currentMobileIndex
                        }
                    }

                    img {
                        css {
                            position = Position.absolute
                            left = 50.pct
                            transform = "translateX(-50%)".unsafeCast<Transform>()
                            height = 100.pct
                        }
                        src = "pixel6.png"
                    }
                }
        }
    }
}