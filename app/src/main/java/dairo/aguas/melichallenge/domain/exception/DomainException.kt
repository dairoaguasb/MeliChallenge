package dairo.aguas.melichallenge.domain.exception

open class DomainException(override val message: String = String()) : Throwable(message)
object NotFoundException : DomainException()
object BadRequestException : DomainException()
object InternalErrorException : DomainException()
object UnknownError : DomainException()
object NoConnectivityDomainException : DomainException()
object TimeOutException : DomainException()
object ParseException : DomainException()
data class HttpError(override val message: String) : DomainException()
