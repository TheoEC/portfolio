package projectsView

import Device
import emotion.react.css
import isDesktop
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
import web.cssom.JustifyContent
import web.cssom.MediaQuery
import web.cssom.NamedColor
import web.cssom.ObjectFit
import web.cssom.Position
import web.cssom.TextAlign
import web.cssom.pct
import web.cssom.px
import web.cssom.vh
import web.cssom.vw
import xpAcquacare
import xpMandacaru
import xpNelogica

//val projectList = jobs.filter { job -> job.platforms.isNotEmpty() }
//val mobileProjects = jobs.filter { job -> job.platforms.any { it.isMobile() } }


val mobileProjects = listOf(xpMandacaru, xpAcquacare)
val projectList = mobileProjects + xpNelogica

val ProjectsViews = FC {
    val (currentIndex, setCurrentIndex) = useState(0)
    val currentProject = projectList[currentIndex]

    val (currentMobileIndex, setCurrentMobileIndex) = useState<Int?>(null)
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
            paddingTop = 30.px
        }
        div {
            css {
                display = Display.inlineFlex
                flexDirection = FlexDirection.column
                alignItems = AlignItems.center
                justifyContent = JustifyContent.spaceAround
                width = 85.pct
                height = 80.pct
                backdropFilter = "blur( 10px )".unsafeCast<BackdropFilter>()
                borderRadius = 25.px
                border = "1px solid rgba( 255, 255, 255, 0.3 )".unsafeCast<Border>()
                boxShadow =
                    "inset 0px 0px 200px 0px rgba( 255, 255, 255, 0.18 )".unsafeCast<BoxShadow>()
                padding = 20.px
                fontSize = 36.px
                media(MediaQuery("(max-width: 750px)")){
                    height = 90.pct
                }
            }
            +"Showcases"
            div {
                css {
                    position = Position.relative
                    display = Display.flex
                    flexDirection = FlexDirection.row
                    justifyContent = JustifyContent.spaceAround
                    alignItems = AlignItems.center
                    marginTop = 10.px
                    width = 100.pct
                    flex = "1 1 auto".unsafeCast<web.cssom.Flex>()
                    fontSize = 14.px
                    media(MediaQuery("(max-width: 750px)")){
                        flexDirection = FlexDirection.columnReverse
                        gap = 20.px
                    }
                }
                div {
                    css {
                        position = Position.relative
                        display = Display.grid
                        gridTemplateColumns =
                            "1fr 1fr".unsafeCast<web.cssom.GridTemplateColumns>()
                        gap = 20.pct
                        media(MediaQuery("(max-width: 750px)")){
                            gap = 5.pct
                        }
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
                if (currentProject.isDesktop()) {
                    Desktop()
                } else
                    Device {
                        this.currentIndex = currentMobileIndex ?: 1
                    }
            }
        }
    }
}
