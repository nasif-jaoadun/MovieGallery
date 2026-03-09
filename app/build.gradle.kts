import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
//    id("org.jetbrains.kotlin.kapt")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.jnasif.moviegallery"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.jnasif.moviegallery"
        minSdk = 24
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlin {
        compilerOptions {
            optIn.add("kotlin.RequiresOptIn")
            jvmTarget.set(JvmTarget.JVM_17)
        }
        jvmToolchain(17)
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.17.0")
    implementation("androidx.appcompat:appcompat:1.7.1")
    implementation("com.google.android.material:material:1.13.0")
    implementation("androidx.constraintlayout:constraintlayout:2.2.1")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.10.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.10.0")
    implementation("androidx.navigation:navigation-fragment:2.9.7")
    implementation("androidx.preference:preference:1.2.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.3.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.7.0")

    implementation("com.squareup.moshi:moshi-kotlin:1.15.2")

    val retrofit2_version = "3.0.0"
    implementation ("com.squareup.retrofit2:retrofit:$retrofit2_version")
    implementation ("com.squareup.retrofit2:converter-moshi:$retrofit2_version")

    val coroutines_version = "1.10.2"
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_version")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines_version")

    val glide_version = "5.0.5"
    implementation ("com.github.bumptech.glide:glide:$glide_version")

    val room_version = "2.8.4"
    implementation ("androidx.room:room-runtime:$room_version")
    implementation ("androidx.room:room-ktx:$room_version") // For Kotlin extensions and coroutine support
    // If you're using Kotlin Annotation Processing (kapt) instead of KSP
//    kapt ("androidx.room:room-compiler:$room_version")
    // Use ksp for annotation processing if you're using Kotlin Symbol Processing
     ksp ("androidx.room:room-compiler:$room_version")
    // Optional: for testing Room migrations
    // androidTestImplementation ("androidx.room:room-testing:$room_version")
    // Optional: for RxJava support
    // implementation "androidx.room:room-rxjava2:$room_version"
    // implementation "androidx.room:room-rxjava3:$room_version"
}