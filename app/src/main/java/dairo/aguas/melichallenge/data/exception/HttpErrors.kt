package dairo.aguas.melichallenge.data.exception

import com.google.gson.Gson
import dairo.aguas.melichallenge.domain.exception.BadRequestException
import dairo.aguas.melichallenge.domain.exception.DomainException
import dairo.aguas.melichallenge.domain.exception.HttpError
import dairo.aguas.melichallenge.domain.exception.InternalErrorException
import dairo.aguas.melichallenge.domain.exception.NotFoundException
import retrofit2.HttpException
import javax.net.ssl.HttpsURLConnection

object HttpErrors {
    private val httpErrors = mapOf(
        HttpsURLConnection.HTTP_BAD_REQUEST to BadRequestException,
        HttpsURLConnection.HTTP_NOT_FOUND to NotFoundException,
        HttpsURLConnection.HTTP_INTERNAL_ERROR to InternalErrorException
    )

    fun getHttpError(error: HttpException): DomainException {
        return if (httpErrors.containsKey(error.code())) {
            httpErrors.getValue(error.code())
        } else {
            HttpError(getMessage(error).message)
        }
    }

    private fun getMessage(exception: HttpException): DomainException {
        return try {
            var jsonString = exception.response()?.errorBody()?.string()
            if (jsonString.isNullOrEmpty()) jsonString = String()
            Gson().fromJson(jsonString, DomainException::class.java)
        } catch (exception_: Exception) {
            DomainException(String())
        }
    }
}
