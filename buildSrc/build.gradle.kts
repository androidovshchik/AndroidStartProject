plugins {
    `kotlin-dsl`
}

sourceSets {
    getByName("main").java.srcDirs("src/main/kotlin")
}

repositories {
    maven(url = "https://jcenter.bintray.com")
}