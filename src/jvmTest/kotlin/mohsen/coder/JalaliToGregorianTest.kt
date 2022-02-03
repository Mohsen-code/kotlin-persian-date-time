package mohsen.coder

import kotlin.test.Test
import kotlin.test.assertContains

class JalaliToGregorianTest {
    @Test
    fun testJalaliToGregorianDate() {
        assertContains(JalaliToGregorian(1400, 1, 1).toString(), "2021/3/21")
        assertContains(JalaliToGregorian(1400, 3, 15).toString(), "2021/6/5")
        assertContains(JalaliToGregorian(1400, 4, 18).toString(), "2021/7/9")
        assertContains(JalaliToGregorian(1400, 5, 19).toString(), "2021/8/10")
        assertContains(JalaliToGregorian(1400, 6, 17).toString(), "2021/9/8")
        assertContains(JalaliToGregorian(1400, 7, 5).toString(), "2021/9/27")
        assertContains(JalaliToGregorian(1400, 8, 23).toString(), "2021/11/14")
        assertContains(JalaliToGregorian(1400, 9, 29).toString(), "2021/12/20")
        assertContains(JalaliToGregorian(1400, 10, 2).toString(), "2021/12/23")
        assertContains(JalaliToGregorian(1400, 11, 18).toString(), "2022/2/7")
        assertContains(JalaliToGregorian(1400, 12, 7).toString(), "2022/2/26")
    }
}