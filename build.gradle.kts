buildscript {
    repositories {
        maven(url = "https://maven.google.com")
        maven(url = "https://jcenter.bintray.com")
    }
    dependencies {
        classpath("com.android.tools.build:gradle:3.3.0")
        classpath(kotlin("gradle-plugin", version = "1.3.21"))
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
