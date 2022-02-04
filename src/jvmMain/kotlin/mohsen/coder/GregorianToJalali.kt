package mohsen.coder

class GregorianToJalali {
    // اختلاف روز های سال میلادی و شمسی در حالت کبیسه و غیر کبیسه 79 روز است
    private val farvardinDayDiff = 79
//    private var pattern: String? = null
    private var dayCount = 0
    private var deyDayDiff = 0 // اختلاف روز اول دی ماه با اول ژانویه
    var jalaliDayOfMonth: Int = 0
        private set
    var jalaliMonth: Int = 0
        private set
    var jalaliYear: Int = 0
        private set
    private val sumDaysOfGregorianMonths = arrayOf(0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334)
    private val sumDaysOfGregorianMonthsLeap = arrayOf(0, 31, 60, 91, 121, 152, 182, 213, 244, 274, 305, 335)



    constructor(gregorianYear: Int, gregorianMonth: Int, gregorianDayOfMonth: Int) {
        this.convertDate(gregorianYear, gregorianMonth, gregorianDayOfMonth)
    }

    // مشخص می کند آیا سال کبیسه است یا نه
    private fun isYearLeap(gregorianYear: Int): Boolean {
        return ((gregorianYear % 100) != 0 && (gregorianYear % 4) == 0) || ((gregorianYear % 100) == 0 && (gregorianYear % 400) == 0)
    }

    private fun convertDate(gregorianYear: Int, gregorianMonth: Int, gregorianDayOfMonth: Int){
        dayCount = if (isYearLeap(gregorianYear)) {
            sumDaysOfGregorianMonthsLeap[gregorianMonth - 1] + gregorianDayOfMonth
        } else {
            sumDaysOfGregorianMonths[gregorianMonth - 1] + gregorianDayOfMonth
        }

        // اختلاف روز بین اولین روز سال میلادی داده شده و اولین روز دی ماه در سال شمسی
        if (isYearLeap(gregorianYear - 1))
            deyDayDiff = 11
        else
            deyDayDiff = 10

        // اگر تعداد روز ها بیشتر از 79 باشد
        if (dayCount > farvardinDayDiff) {
            dayCount -= farvardinDayDiff

            // در 6 ماه اول سال قرار داریم
            if (dayCount <= 186) {

                if (dayCount % 31 == 0) {
                    jalaliMonth = dayCount / 31
                    jalaliDayOfMonth = 31
                } else {
                    jalaliMonth = (dayCount / 31) + 1
                    jalaliDayOfMonth = (dayCount % 31)
                }

            }
            // در 6 ماه دوم سال قرار داریم
            else {
                dayCount -= 186
                if (dayCount % 30 == 0) {
                    jalaliMonth = (dayCount / 30) + 6
                    jalaliDayOfMonth = 30
                } else {
                    jalaliMonth = (dayCount / 30) + 7
                    jalaliDayOfMonth = (dayCount % 30)
                }
            }

            jalaliYear = gregorianYear - 621

        }
        // اگر تعداد روز های کمتر از 79 باشد
        else {
            dayCount += deyDayDiff
            if (dayCount % 30 == 0) {
                jalaliMonth = (dayCount / 30) + 9
                jalaliDayOfMonth = 30
            } else {
                jalaliMonth = (dayCount / 30) + 10
                jalaliDayOfMonth = (dayCount % 30)
            }
            jalaliYear = gregorianYear - 622
        }
    }

    override fun toString(): String {
        return "$jalaliYear/$jalaliMonth/$jalaliDayOfMonth"
    }
}