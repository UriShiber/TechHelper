// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    dependencies {
        classpath(libs.androidx.navigation.safe.args.gradle.plugin)
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.ksp) apply false
    alias(libs.plugins.androidx.room) apply false
    alias(libs.plugins.gms.services) apply false
    // Add the dependency for the Google services Gradle plugin
//    id("com.google.gms.google-services") version "4.4.2" apply false
}