package dairo.aguas.melichallenge.domain.exception

import com.google.gson.JsonParseException
import com.google.gson.JsonSyntaxException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

open class CommonErrors {
    fun manageException(throwable: Throwable): DomainException {
        return manageJavaErrors(throwable)
    }

    private fun manageJavaErrors(throwable: Throwable): DomainException {
        return when (throwable) {
            is SocketTimeoutException -> TimeOutException
            is ConnectException -> InternalErrorException
            else -> manageParsingExceptions(throwable)
        }
    }

    private fun manageParsingExceptions(throwable: Throwable): DomainException {
        return when (throwable) {
            is JsonParseException -> ParseException
            is JsonSyntaxException -> ParseException
            else -> manageOtherException(throwable)
        }
    }

    private fun manageOtherException(throwable: Throwable): DomainException {
        return when (throwable) {
            is UnknownHostException -> NoConnectivityDomainException
            else -> UnknownError
        }
    }
}
