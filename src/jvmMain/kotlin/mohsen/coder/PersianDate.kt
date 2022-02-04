package mohsen.coder

import mohsen.coder.dto.DateDTO
import java.sql.Timestamp
import java.time.Instant
import java.time.format.DateTimeFormatter
import java.util.Calendar

class PersianDate {
    private var timestampEntered = false
    private var year: Int = 0
    private var month: Int = 0
    private var dayOfMonth: Int = 0

    constructor()

    constructor(date: String) {
        val pattern =
            Regex("[1-9][0-9]{3}[-_\\/\\.\\,](((1)[0-2])|[1-9])[-_\\/\\.\\,]((0[1-9])|(1[0-9])|(2[0-9])|(3[0-1])|[1-9]{1})\$")
        if (!pattern.containsMatchIn(date)) {
            throw java.lang.Exception("Input date does not match pattern")
        }

        val dateList = date.split(Regex("[-_\\/\\.\\,]"))
        year = dateList[0].toInt()
        month = dateList[1].toInt()
        dayOfMonth = dateList[2].toInt()
    }

    constructor(timestamp: Long) {
        if (timestamp.toString().length < 13) {
            throw java.lang.Exception("Entered timestamp is not valid! timestamp length must be 13 character!")
        }
        this.timestampEntered = true
        val date = this.convertTimestampToDate(timestamp)
        year = date.year
        month = date.month
        dayOfMonth = date.dayOfMonth
    }

    constructor(inputYear: Int, inputMonth: Int, inputDayOfMonth: Int) {
        year = inputYear
        month = inputMonth
        dayOfMonth = inputDayOfMonth
    }

    constructor(inputYear: Int, inputMonth: PersianMonths, inputDayOfMonth: Int) {
        year = inputYear
        month = when (inputMonth) {
            PersianMonths.Farvardin -> 1
            PersianMonths.Ordibehesht -> 2
            PersianMonths.Khordad -> 3
            PersianMonths.Tir -> 4
            PersianMonths.Mordad -> 5
            PersianMonths.Shahrivar -> 6
            PersianMonths.Mehr -> 7
            PersianMonths.Aban -> 8
            PersianMonths.Azar -> 9
            PersianMonths.Dey -> 10
            PersianMonths.Bahman -> 11
            PersianMonths.Esfand -> 12
        }
        dayOfMonth = inputDayOfMonth
    }

    constructor(inputYear: Int, inputMonth: EnglishMonths, inputDayOfMonth: Int) {
        year = inputYear
        month = when (inputMonth) {
            EnglishMonths.January -> 1
            EnglishMonths.February -> 2
            EnglishMonths.March -> 3
            EnglishMonths.April -> 4
            EnglishMonths.May -> 5
            EnglishMonths.June -> 6
            EnglishMonths.July -> 7
            EnglishMonths.August -> 8
            EnglishMonths.September -> 9
            EnglishMonths.October -> 10
            EnglishMonths.November -> 11
            EnglishMonths.December -> 12
        }
        dayOfMonth = inputDayOfMonth
    }

    fun toGregorian(): DateDTO {
        if (this.timestampEntered) {
            return DateDTO(this.year, this.month, this.dayOfMonth)
        }
        val jalaliToGregorian = JalaliToGregorian(this.year, this.month, this.dayOfMonth)
        return DateDTO(
            jalaliToGregorian.gregorianYear,
            jalaliToGregorian.gregorianMonth,
            jalaliToGregorian.gregorianDayOfMonth
        )
    }

    fun toJalali(): DateDTO {
        val gregorianToJalali = GregorianToJalali(this.year, this.month, this.dayOfMonth)
        return DateDTO(
            gregorianToJalali.jalaliYear,
            gregorianToJalali.jalaliMonth,
            gregorianToJalali.jalaliDayOfMonth
        )
    }

    fun now(gregorian: Boolean = false): DateDTO {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.MONTH, Calendar.MONTH)
        val timestamp = calendar.timeInMillis
        val date = this.convertTimestampToDate(timestamp)
        if (gregorian) {
            return date
        }
        val gregorianToJalali = GregorianToJalali(date.year, date.month, date.dayOfMonth)
        return DateDTO(
            gregorianToJalali.jalaliYear,
            gregorianToJalali.jalaliMonth,
            gregorianToJalali.jalaliDayOfMonth
        )
    }

    private fun convertTimestampToDate(timestamp: Long): DateDTO {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = timestamp
        calendar.set(Calendar.MONTH, Calendar.MONTH)
        return DateDTO(
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
    }

    fun getTimestamp(): Long {
        if (this.timestampEntered) {
            throw java.lang.Exception("You can't get timestamp on entered timestamp value!")
        }
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, this.year)
        calendar.set(Calendar.MONTH, this.month)
        calendar.set(Calendar.DAY_OF_MONTH, this.dayOfMonth)
        if (calendar.timeInMillis > 0) return calendar.timeInMillis

        val dateDTO = this.toGregorian()
        calendar.set(Calendar.YEAR, dateDTO.year)
        calendar.set(Calendar.MONTH, dateDTO.month)
        calendar.set(Calendar.DAY_OF_MONTH, dateDTO.dayOfMonth)
        return calendar.timeInMillis
    }
}