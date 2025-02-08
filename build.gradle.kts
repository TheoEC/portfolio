import org.jetbrains.kotlin.gradle.dsl.KotlinJsCompile

plugins {
    kotlin("multiplatform") version "2.1.10"
    kotlin("plugin.serialization") version "2.1.10"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/kotlin-wrappers/")
}

kotlin {
    jvmToolchain(11)
    js {
        useCommonJs()
        browser {
            commonWebpackConfig {
                cssSupport {
                    enabled.set(true)
                }
                optimization.apply {

                }
            }
        }
        binaries.executable()
    }
    sourceSets {
        val jsMain by getting {
            dependencies {
                //React, React DOM + Wrappers (chapter 3)
                implementation(kotlinWrappers.react)
                implementation(kotlinWrappers.reactDom)
                implementation(kotlinWrappers.reactRouterDom)

                implementation(kotlinWrappers.emotion)
                implementation(kotlinWrappers.mui.material)
                implementation(kotlinWrappers.mui.iconsMaterial)
                implementation(kotlinWrappers.muix.datePickers)

                implementation(npm("react-icons", "5.4.0"))
                implementation(npm("@vercel/speed-insights", "1.1.0"))


//                implementation("org.jetbrains.kotlinx:kotlinx-js:0.5.0")


                //Kotlin React Emotion (CSS) (chapter 3)

                //Video Player (chapter 7)
//                implementation(npm("react-player", "2.12.0"))
//
//                //Share Buttons (chapter 7)
//                implementation(npm("react-share", "4.4.1"))

                //Coroutines & serialization (chapter 8)
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")
            }
        }
    }
}

// Heroku Deployment (chapter 9)
tasks.register("stage") {
    dependsOn("build")
}

tasks.named("build") {
    dependsOn(tasks.named("jsBrowserProductionWebpack"))
}

tasks.withType<KotlinJsCompile>().configureEach {
    compilerOptions {
        target.set("es2015")
    }
}
tasks.wrapper {
    gradleVersion = "8.12"
}