plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}

android {
    namespace = "com.example.firstaiapp"
    compileSdk = 36

//    buildConfigField("String", "API_KEY", localProperties['apiKey'])

    defaultConfig {
        applicationId = "com.example.firstaiapp"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

//        def localPropertiesFile = rootProject.file("local.properties")
//        def localProperties = new Properties()
//        localProperties.load(new FileInputStream(localPropertiesFile))
//        buildConfigField ("String", "API_KEY", API_KEY)



        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.generativeai)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation("com.google.ai.client.generativeai:generativeai:0.9.0")
}
