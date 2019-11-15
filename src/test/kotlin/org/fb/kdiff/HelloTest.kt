package org.fb.kdiff

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test


class HelloTest {

    @Test
    fun `dummy green test`() {
        val t = true
        val f = false

        assertFalse( execute(f, f))
        assertTrue( execute(f, t))
        assertTrue( execute(t, f))
        assertTrue( execute(t, t))
    }

    @Test
    internal fun `dummy failing test`() {
        fail<String>("I failed")
    }

    private fun execute(a: Boolean, b: Boolean): Boolean = !(!a && !b)
    fun execute2(a: Boolean, b: Boolean): Boolean = !(!a || !b)

}
