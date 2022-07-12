package dairo.aguas.melichallenge.domain.exception

import dairo.aguas.melichallenge.R

class ExceptionHandler {

    fun manageException(domainException: DomainException): Int =
        when (domainException) {
            is TimeOutException -> R.string.error_time_out
            is InternalErrorException -> R.string.error_internal_error_exception
            is ParseException -> R.string.error_parsing_error
            is NoConnectivityDomainException -> R.string.error_internet_connection
            is NotFoundException -> R.string.error_http_not_found
            else -> R.string.error_some_wrong
        }
}
