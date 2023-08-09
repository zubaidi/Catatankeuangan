# Catatankeuangan
Projek latihan kelas prmrograman mobile xii pplg SMK Syafi'i Akrom pekalongan.
![image](https://github.com/zubaidi/Catatankeuangan/assets/5498861/ced1982f-5f9e-463c-a9ec-36f462b32183)
![image](https://github.com/zubaidi/Catatankeuangan/assets/5498861/67157227-39dc-49e6-84e1-5cfc4ad87bfb)
![image](https://github.com/zubaidi/Catatankeuangan/assets/5498861/c66739c9-77a6-4b7c-9c23-befcecfbbf15)
![image](https://github.com/zubaidi/Catatankeuangan/assets/5498861/258bceeb-e4c5-4c79-8f44-c591a0c81f39)
![image](https://github.com/zubaidi/Catatankeuangan/assets/5498861/b2283648-942d-4a63-af4a-3440313a9913)
![image](https://github.com/zubaidi/Catatankeuangan/assets/5498861/951a8c21-f90c-4ef9-b526-8976c05c2f78)

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

