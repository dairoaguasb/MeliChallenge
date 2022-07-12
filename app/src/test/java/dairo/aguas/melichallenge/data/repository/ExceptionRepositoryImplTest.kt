package dairo.aguas.melichallenge.data.repository

import dairo.aguas.melichallenge.domain.exception.BadRequestException
import dairo.aguas.melichallenge.domain.exception.UnknownError
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.HttpException

class ExceptionRepositoryImplTest {
    private val httpException = mockk<HttpException>(relaxed = true)

    private val exceptionLogin = ExceptionRepositoryImpl()

    @Test
    fun whenHttpExceptionIs400ShouldReturnBadRequestException() {
        every { httpException.code() } answers { 400 }

        val domainException = exceptionLogin.manageError(httpException)

        assertEquals(domainException, BadRequestException)

        verify(exactly = 2) { httpException.code() }
    }

    @Test
    fun whenManageErrorIsThrowableShouldReturnUnknownError() {
        val domainException = exceptionLogin.manageError(Throwable())

        assertEquals(domainException, UnknownError)
    }
}
