# Kotlin Persian Date Time Library

This library help you to work with Persian/Gregorian date and time in easy way

## Installation

1. Add Jitpack in your root build.gradle at the end of repositories:

```kotlin
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```

2. Add the dependency

```kotlin
dependencies {
    implementation 'com.github.Mohsen-code:kotlin-persian-date-time:-SNAPSHOT'
}
```

## Usage

example 1:

```kotlin
import mohsen.coder.PersianDate

// convert persian date to gregorian date
//accepted string date format: "1400.11.15" - "1400/11/15" - 
// "1400-11-15" - "1400,11,15" - "1400/11.15" - "1400_11,15" - ... 
val persianDate = PersianDate("1400.11.15")
val dateDTO = persianDate.toGregorian()

println(dateDTO.year)
println(dateDTO.month)
println(dateDTO.dayOfMonth)


// convert gregorian date to persian date
// accepted string date format: 
// the same as convert persian date to gregorian date
val persianDate = PersianDate("2022/2/4")
val dateDTO = persianDate.toJalali()

println(dateDTO.year)
println(dateDTO.month)
println(dateDTO.dayOfMonth)
```

example 2:

```kotlin
import mohsen.coder.PersianDate


var persianDate = PersianDate(1400, 11, 15)
var dateDTO = persianDate.toGregorian()

println(dateDTO.year)
println(dateDTO.month)
println(dateDTO.dayOfMonth)

// =====================================

persianDate = PersianDate(2022, 2, 4)
dateDTO = persianDate.toJalali()

println(dateDTO.year)
println(dateDTO.month)
println(dateDTO.dayOfMonth)
```

example 3:

```kotlin
import mohsen.coder.PersianDate


// timestamp must be in millisecond
val persianDate = PersianDate(1643980435000)
val gregorianDateDTO = persianDate.toGregorian()
val jalaliDateDTO = persianDate.toJalali()

println(gregorianDateDTO.year)
println(gregorianDateDTO.month)
println(gregorianDateDTO.dayOfMonth)

println(jalaliDateDTO.year)
println(jalaliDateDTO.month)
println(jalaliDateDTO.dayOfMonth)

```

example 4:

```kotlin
import mohsen.coder.PersianDate
import mohsen.coder.EnglishMonths


val persianDate = PersianDate(2022, EnglishMonths.February, 4)
val dateDTO = persianDate.toJalali()

println(dateDTO.year)
println(dateDTO.month)
println(dateDTO.dayOfMonth)
```

example 5:

```kotlin
import mohsen.coder.PersianDate
import mohsen.coder.PersianMonths


val persianDate = PersianDate(1400, PersianMonths.Bahman, 15)
val dateDTO = persianDate.toGregorian()

println(dateDTO.year)
println(dateDTO.month)
println(dateDTO.dayOfMonth)
```

example 6:

```kotlin
import mohsen.coder.PersianDate


val persianDate = PersianDate()
val dateDTO = persianDate.now()

println(dateDTO.year)
println(dateDTO.month)
println(dateDTO.dayOfMonth)
```

##Timestamp

to get date timestamp use `getTimestamp()` method.

example:

```kotlin
import mohsen.coder.PersianDate


val timestamp1 = PersianDate("2022.2.4").getTimestamp()
val timestamp2 = PersianDate("1400-11-15").getTimestamp()
val timestamp3 = PersianDate(2022, 2, 4).getTimestamp()
val timestamp4 = PersianDate(1400, 11, 15).getTimestamp()
```