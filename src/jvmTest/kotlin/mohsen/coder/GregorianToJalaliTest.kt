package mohsen.coder

import kotlin.test.Test
import kotlin.test.assertContains

class GregorianToJalaliTest {
    @Test
    fun testGregorianToJalaliDate() {
        assertContains(GregorianToJalali(2022, 2, 2).toString(), "1400/11/13")
        assertContains(GregorianToJalali(2022, 1, 1).toString(), "1400/10/11")
        assertContains(GregorianToJalali(2022, 4, 15).toString(), "1401/1/26")
        assertContains(GregorianToJalali(2021, 7, 13).toString(), "1400/4/22")
        assertContains(GregorianToJalali(2021, 10, 13).toString(), "1400/7/21")
        assertContains(GregorianToJalali(2022, 12, 23).toString(), "1401/10/2")
    }
}