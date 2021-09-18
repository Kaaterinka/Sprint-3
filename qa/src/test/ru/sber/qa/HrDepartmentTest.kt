package ru.sber.qa

import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.unmockkAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import java.time.Clock
import java.time.Instant
import java.time.ZoneOffset

internal class HrDepartmentTest {

    var certificateRequest = mockk<CertificateRequest>()
    var certificate = mockk<Certificate>()
    var hrEmployeeNumber = 321L

    @BeforeEach
    fun setUp() {
        val clockFixed = Clock.fixed(Instant.parse("2021-09-06T10:00:00Z"), ZoneOffset.UTC)
        HrDepartment.clock = clockFixed
        mockkObject(CertificateType.NDFL)
        mockkObject(CertificateType.LABOUR_BOOK)
    }

    @Test
    fun receiveRequest() {
        every { certificateRequest.certificateType } returns CertificateType.NDFL
        assertDoesNotThrow { HrDepartment.receiveRequest(certificateRequest) }
    }

    @Test
    fun processNextRequest() {
        every { certificateRequest.process(hrEmployeeNumber)} returns certificate
    }

    @AfterEach
    fun tearDown() {
        unmockkAll()
    }

}