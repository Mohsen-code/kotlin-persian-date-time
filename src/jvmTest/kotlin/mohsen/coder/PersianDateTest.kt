package mohsen.coder

import kotlin.test.Test
import kotlin.test.assertContains

class PersianDateTest {
    @Test
    fun testStringDate() {
        var persianDate = PersianDate("1400.11.15")
        assertContains(persianDate.toGregorian().toString(), "2022/2/4")

        persianDate = PersianDate("2022/2-4")
        assertContains(persianDate.toJalali().toString(), "1400/11/15")

        persianDate = PersianDate("2022.2-4")
        assertContains(persianDate.toJalali().toString(), "1400/11/15")

        persianDate = PersianDate("2022,2/4")
        assertContains(persianDate.toJalali().toString(), "1400/11/15")

        persianDate = PersianDate("1400-11,15")
        assertContains(persianDate.toGregorian().toString(), "2022/2/4")
    }

    @Test
    fun testTimestampDate(){
        val persianDate = PersianDate(1643980435000)
        assertContains(persianDate.toGregorian().toString(), "2022/2/4")
        assertContains(persianDate.toJalali().toString(), "1400/11/15")
    }

    @Test
    fun testDateBaseOnNumbers(){
        var persianDate = PersianDate(1400, 11, 15)
        assertContains(persianDate.toGregorian().toString(), "2022/2/4")

        persianDate = PersianDate(2022, 2, 4)
        assertContains(persianDate.toJalali().toString(), "1400/11/15")
    }

    @Test
    fun testDateBaseOnEnglishMonths(){
        val persianDate = PersianDate(2022, EnglishMonths.February, 4)
        assertContains(persianDate.toJalali().toString(), "1400/11/15")
    }

    @Test
    fun testDateBaseOnPersianMonths(){
        val persianDate = PersianDate(1400, PersianMonths.Bahman, 15)
        assertContains(persianDate.toGregorian().toString(), "2022/2/4")
    }

    @Test
    fun testCurrentDate(){
        val persianDate = PersianDate()
        assertContains(persianDate.now().toString(), "1400/11/15")
        assertContains(persianDate.now(true).toString(), "2022/2/4")
    }

    @Test
    fun testGetTimestamp(){
        val timestamp1 = PersianDate("2022.2.4").getTimestamp()
        val timestamp2 = PersianDate("1400-11-15").getTimestamp()
        val timestamp3 = PersianDate(2022, 2, 4).getTimestamp()
        val timestamp4 = PersianDate(1400, 11, 15).getTimestamp()

        if (timestamp1 == timestamp2) assert(true)
        else assert(false)

        if (timestamp3 == timestamp4) assert(true)
        else assert(false)

        if (timestamp1 == timestamp4) assert(true)
        else assert(false)

        if (timestamp2 == timestamp3) assert(true)
        else assert(false)
    }

}