package org.fb.kdiff

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class HelloTest {

    @Test
    fun `dummy test`() {
        val t = true
        val f = false

        assertEquals(false, execute(f, f))
        assertEquals(true, execute(f, t))
        assertEquals(true, execute(t, f))
        assertEquals(true, execute(t, t))

    }

    fun execute(a: Boolean, b: Boolean): Boolean = !(!a && !b)
    fun execute2(a: Boolean, b: Boolean): Boolean = !(!a || !b)

}
