package util

sealed class Result<out T, out Error: Throwable> {
    data class Success<T>(val value: T): Result<T, Nothing>()
    data class Failure<Error: Throwable>(val error: Error): Result<Nothing, Error>()

    fun onSuccess(onSuccess: (T) -> Unit): Result<T, Error> {
        when(this) {
            is Success -> onSuccess(value)
        }
        return this
    }

    fun onFailure(onFailure: (Error) -> Unit): Result<T, Error> {
        when(this) {
            is Failure -> onFailure(error)
        }
        return this
    }

    fun getOrNull(): T? {
        return when(this) {
            is Success -> value
            else -> null
        }
    }

    fun <R: Throwable> mapError(transform: (Error) -> R): Result<T, R> {
        return when(this) {
            is Success -> Success(this.value)
            is Failure -> Failure(transform(this.error))
        }
    }
}