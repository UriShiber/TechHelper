plugins {
    kotlin("android")
    alias(libs.plugins.android.application)
    alias(libs.plugins.androidx.navigation.safe.args)
}

android {
    namespace = "com.sr.techhelper"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.sr.techhelper"
        minSdk = 29
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        viewBinding = true
    }

}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.gridlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.annotation)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.firebase.ui.auth)
    implementation(libs.androidx.room.runtime) // Room runtime

    // You can add LiveData or RxJava dependencies here if required
    implementation(libs.androidx.room.runtime)
    implementation(platform(libs.firebase.bom))
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson.converter)
    implementation(libs.firebase.firestore)
    implementation(libs.firebase.firestore.ktx)
    implementation("com.github.bumptech.glide:glide:4.15.1") // Glide library
    annotationProcessor("com.github.bumptech.glide:compiler:4.15.1") // Glide compiler
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}