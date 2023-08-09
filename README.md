# Catatankeuangan
Projek mobile kelas xii pplg smksa

## Graddle Version
Android Gradle Plugin Version : 8.1.0, Android Gradle Version : 8.0

## Project gradle setting
compileSdk 33, targetSdk 33, JavaVersion.VERSION_17, jvmTarget = '17'

dependencies {

    implementation 'androidx.core:core-ktx:1.9.0'
    implementation 'androidx.appcompat:appcompat:1.6.1'
    implementation 'com.google.android.material:material:1.9.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.1.4'
    testImplementation 'junit:junit:4.13.2'
    androidTestImplementation 'androidx.test.ext:junit:1.1.5'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.5.1'

    // depend room
    implementation "androidx.room:room-runtime:2.5.2"
    annotationProcessor "androidx.room:room-compiler:2.5.2"
    implementation "androidx.room:room-ktx:2.5.2"
    kapt "androidx.room:room-compiler:2.5.2"
    implementation "androidx.room:room-rxjava3:2.5.2"
    implementation "androidx.room:room-guava:2.5.2"
    testImplementation "androidx.room:room-testing:2.5.2"
    implementation "androidx.room:room-paging:2.5.2"

}

# PERLU DIPERHATIKAN
Ketika proses build gradle, lakukan penyamaan versi terlebih dahulu dengan gradle yang ada dikomputer. Lakukan proses downgrade versi dependency dan versi gradle untuk mencegah error.

