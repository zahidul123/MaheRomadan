package com.example.maheromadan.utils
import com.example.maheromadan.utils.ResponseStatus.ERROR
import com.example.maheromadan.utils.ResponseStatus.SUCCESS
import com.example.maheromadan.utils.ResponseStatus.LOADING

data class Resource<out T>(val status: ResponseStatus, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T): Resource<T> =
            Resource(status = SUCCESS, data = data, message = null)

        fun <T> error(data: T?, message: String): Resource<T> =
            Resource(status = ERROR, data = data, message = message)

        fun <T> loading(data: T?): Resource<T> =
            Resource(status = LOADING, data = data, message = null)
    }
}

