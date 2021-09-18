package ru.sber.qa

import io.mockk.every
import io.mockk.mockkObject
import io.mockk.unmockkAll
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import kotlin.random.Random

internal class ScannerTest {

    @Test
    fun getScanData() {
        mockkObject(Random)

        val scanData = Random.nextBytes(100)

        every { Random.nextLong(5000L, 15000L) } returns 10000L
        every { Random.nextBytes(100) } returns scanData

        assertDoesNotThrow { Scanner.getScanData() }
        assertEquals(scanData, Scanner.getScanData())

        unmockkAll()
    }
}