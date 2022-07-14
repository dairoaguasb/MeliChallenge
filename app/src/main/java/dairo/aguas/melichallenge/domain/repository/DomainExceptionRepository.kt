package dairo.aguas.melichallenge.domain.repository

import dairo.aguas.melichallenge.domain.exception.DomainException

interface DomainExceptionRepository {
    fun manageError(error: Throwable): DomainException
}
