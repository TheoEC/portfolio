package presentation

import web.cssom.px

val ICON_SIZE = 25.px

val KMP_ICONS = listOf(
    "/icons/android.svg",
    "/icons/apple.svg",
    "/icons/windows.svg",
    "/icons/web2.png"
)

val skillJava = showSkill(
    name = "Java",
    leftIcons = listOf("icons/java.png")
)

val skillFirebase = showSkill(
    "Firebase",
    listOf("icons/firebase.svg")
)

val skillPython = showSkill(
    name = "Python",
    leftIcons = listOf("icons/python.png")
)

val skillDeplhi = showSkill(
    name = "Delphi",
    leftIcons =  listOf("icons/delphi.svg")
)

val skillKMP = showSkill(
    name = "Kotlin Multiplataforma",
    leftIcons = listOf("/icons/kotlin.png"),
    rightIcons = KMP_ICONS,
    highlight = true
)
