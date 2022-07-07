package dairo.aguas.melichallenge.data.repository

import dairo.aguas.melichallenge.data.exception.HttpErrors.getHttpError
import dairo.aguas.melichallenge.domain.exception.CommonErrors
import dairo.aguas.melichallenge.domain.exception.DomainException
import dairo.aguas.melichallenge.domain.repository.DomainExceptionRepository
import retrofit2.HttpException

class ExceptionRepositoryImpl : CommonErrors(), DomainExceptionRepository {

    override fun manageError(error: Throwable): DomainException {
        return if (error is HttpException) {
            getHttpError(error)
        } else {
            manageException(error)
        }
    }
}
