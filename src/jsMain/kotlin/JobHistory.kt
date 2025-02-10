import kotlin.js.Date

val xpMandacaru = Job(
    name = "Desenvolvedor de Software",
    companyName = "Mandacaru Store",
    endDate = Date(),
    startDate = Date(year = 2024, month = 10, day = 1),
    urlIcon = "icons/mandacaru.png",
    urlGif = "device/mandacaru.webm",
    urlImage = "device/background.png",
    platforms = listOf(AppPlatforms.ANDROID, AppPlatforms.IOS)
)
val xpAcquacare = Job(
    name = "Desenvolvedor de Software",
    companyName = "AcquaCare",
    endDate = Date(year = 2024, month = 7, day = 31),
    startDate = Date(year = 2024, month = 3, day = 30),
    urlIcon = "icons/apps/acquacare.png",
    urlGif = "device/acquacare.webm",
    urlImage = "device/acquacare.png",
    platforms = listOf(AppPlatforms.ANDROID)
)

val xpNelogica = Job(
    name = "Desenvolvedor de Software",
    companyName = "Nelogica",
    endDate = Date(year = 2024, month = 3, day = 30),
    startDate = Date(year = 2021, month = 10, day = 16),
    urlIcon = "/icons/nelogica.jpg",
    urlImage = "icons/apps/vector.jpg",
    urlGif = "desktop/vector.mov",
    platforms = listOf(AppPlatforms.WINDOWS),
    awards = listOf(
        Award(
            name = "1º Lugar NeloAwards",
            description = "Premiação entregas de maior valor de 2023.",
            date = Date(year = 2023, month = 11, day = 1),
            urlIcon = "/icons/1place.png"
        ),
        Award(
            name = "3º Lugar NeloAwards",
            description = "Premiação entregas de maior valor doe 2022.",
            date = Date(year = 2022, month = 11, day = 1),
            urlIcon = "/icons/3place.png"
        ),
    )
)

val xpKatze = Job(
    name = "Desenvolvedor de Software",
    companyName = "Katze",
    endDate = Date(year = 2021, month = 10, day = 16),
    startDate = Date(year = 2020, month = 0, day = 1),
    urlIcon = "icons/katze.jpg"
)

val xpFiep = Job(
    name = "Desenvolvedor de Software",
    companyName = "Fiep",
    endDate = Date(year = 2019, month = 11, day = 31),
    startDate = Date(year = 2019, month = 5, day = 1),
    awards = listOf(
        Award(
            name = "Financiamento",
            description = "Na apresentação do projeto finalizado, a empresa recebeu R$80K em financiamento",
            date = Date(year = 2019, month = 11, day = 1),
            urlIcon = "/icons/award.png"
        )
    )
)

val xpGcompi = Job(
    name = "Analista de dados",
    companyName = "GComPI",
    endDate = Date(year = 2019, month = 11, day = 31),
    startDate = Date(year = 2018, month = 3, day = 1),
    urlIcon = "icons/gcompi.png",
    awards = listOf(
        Award(
            name = "Publicação de Artigo Científico",
            description = "Publicação de artigo científico",
            date = Date(year = 2019, month = 0, day = 31),
            urlIcon = "/icons/article.png"
        )
    )
)

val jobs = listOf(
    xpMandacaru,
    xpAcquacare,
    xpNelogica,
    xpKatze,
//    xpFiep,
    xpGcompi,
).sortedBy { it.startDate.getTime() }

