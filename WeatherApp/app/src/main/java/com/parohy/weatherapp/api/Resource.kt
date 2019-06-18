package com.parohy.weatherapp.api

class Resource<T> private constructor(
    var data: T? = null,
    var status: Status = Status.NOT_SET,
    var error: Throwable? = null) {

    companion object {
        fun <T> success(data: T): Resource<T> = Resource(data, Status.SUCCESS)
        fun <T> error(error: Throwable, data: T): Resource<T> = Resource(data, Status.ERROR, error)
        fun <T> error(error: Throwable): Resource<T> = Resource(null, Status.ERROR, error)
        fun <T> loading(data: T): Resource<T> = Resource(data, Status.LOADING)
        fun <T> loading(): Resource<T> = Resource(null, Status.LOADING)
        fun <T> init(): Resource<T> = Resource(null)
    }

    fun isLoading(): Boolean = status == Status.LOADING

    fun isSuccessful(): Boolean = status == Status.SUCCESS

    enum class Status {
        NOT_SET,
        SUCCESS,
        ERROR,
        LOADING
    }
}