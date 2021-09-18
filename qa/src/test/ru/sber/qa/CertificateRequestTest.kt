package ru.sber.qa

import io.mockk.*
import org.junit.jupiter.api.Test
import kotlin.random.Random
import kotlin.test.assertEquals

internal class CertificateRequestTest {

    var certificateType = mockk<CertificateType>()
    var employeeNumber = 256L
    var hrEmployeeNumber = 345L
    var certificateRequest = CertificateRequest(employeeNumber, certificateType)
    var data = Random.nextBytes(100)

    @Test
    fun process() {
        mockkConstructor(Certificate::class)
        mockkObject(Scanner)

        every { Scanner.getScanData()  } returns data

        val certificate= certificateRequest.process(hrEmployeeNumber)

        assertEquals(certificate.certificateRequest,certificateRequest)
        assertEquals(certificate.data,data)
        assertEquals(certificate.processedBy,hrEmployeeNumber)

        unmockkAll()
    }

    @Test
    fun getEmployeeNumber() {
        assertEquals(employeeNumber, certificateRequest.employeeNumber)
    }

    @Test
    fun getCertificateType() {
        assertEquals(certificateType, certificateRequest.certificateType)
    }
}