package mohsen.coder

class JalaliToGregorian {

    var gregorianYear = 0;
    var gregorianMonth = 0;
    var gregorianDayOfMonth = 0
    private val gregorianMonths = arrayOf(30, 31, 30, 31, 31, 30, 31, 30, 31, 31, 28, 31)
    private val gregorianMonthsLeap = arrayOf(30, 31, 30, 31, 31, 30, 31, 30, 31, 31, 29, 31)

    constructor(jalaliYear: Int, jalaliMonth: Int, jalaliDaysOfMonth: Int) {
        gregorianYear = jalaliYear + 621
        var marchdaydiff = if (yearIsLeap(gregorianYear)) 12 else 11
        var dayCount = 0

        if (jalaliMonth in 1..6) {
            dayCount = (jalaliMonth - 1) * 31 + jalaliDaysOfMonth
        } else if (jalaliMonth in 7..12) {
            dayCount = (6 * 31) + (jalaliMonth - 7) * 30 + jalaliDaysOfMonth
        }

        if (dayCount < marchdaydiff) {
            gregorianDayOfMonth = dayCount + (31 - marchdaydiff)
            gregorianMonth = 3
        } else {
            var remainDays = dayCount - marchdaydiff
            var i = 0
            if (yearIsLeap(gregorianYear + 1)) {
                while (remainDays > gregorianMonths[i]) {
                    remainDays -= gregorianMonthsLeap[i]
                    i++
                }
            } else {
                while (remainDays > gregorianMonths[i]) {
                    remainDays -= gregorianMonths[i]
                    i++
                }
            }

            gregorianDayOfMonth = remainDays
            if (i > 8) {
                gregorianMonth = i - 8
                gregorianYear += 1
            } else {
                gregorianMonth = i + 4
            }
        }

    }

    private fun yearIsLeap(gregorianYear: Int): Boolean {
        return ((gregorianYear % 100) != 0 && (gregorianYear % 4) == 0) || ((gregorianYear % 100) == 0 && (gregorianYear % 400) == 0)
    }

    override fun toString(): String {
        return "$gregorianYear/$gregorianMonth/$gregorianDayOfMonth"
    }

}