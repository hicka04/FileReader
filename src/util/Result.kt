package util

sealed class Result<T, Exception: java.lang.Exception> {
    data class Success<T, Exception: java.lang.Exception>(val value: T): Result<T, Exception>()
    data class Failure<T, Exception: java.lang.Exception>(val error: Exception): Result<T, Exception>()

    fun onSuccess(onSuccess: (T) -> Unit): Result<T, Exception> {
        when(this) {
            is Success -> onSuccess(this.value)
        }
        return this
    }

    fun onFailure(onFailure: (Exception) -> Unit): Result<T, Exception> {
        when(this) {
            is Failure -> onFailure(this.error)
        }
        return this
    }
}