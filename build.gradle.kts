plugins {
    id("java")
    id("application")
    id("org.openjfx.javafxplugin") version "0.0.10"
}

javafx {
    modules("javafx.controls", "javafx.fxml")

}

application {
    mainClass.set("org.example.UI.MainWindow")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}
