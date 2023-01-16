package com.appcrafters.rentacar.base.functional

import kotlin.coroutines.CoroutineContext

interface ICoroutineContextProvider {
    val main: CoroutineContext
    val io: CoroutineContext
    val default: CoroutineContext
}