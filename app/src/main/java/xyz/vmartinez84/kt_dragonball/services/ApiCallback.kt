package xyz.vmartinez84.kt_dragonball.services

import xyz.vmartinez84.kt_dragonball.models.Personaje

interface ApiCallback<T> {
    fun onSuccess(data: Personaje)
    fun onError(error: String)
}