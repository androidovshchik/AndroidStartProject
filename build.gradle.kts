buildscript {
    repositories {
        maven(url = "https://maven.google.com")
        maven(url = "https://jcenter.bintray.com")
    }
    dependencies {
        classpath(Plugins.android)
        classpath(Plugins.kotlin)
    }
}

allprojects {
    repositories {
        maven(url = "https://maven.google.com")
        maven(url = "https://jcenter.bintray.com")
        maven(url = "https://jitpack.io")
    }
}

tasks.register("clean", Delete::class) {
    delete("$rootDir/build")
    delete("$rootDir/app/build")
}
