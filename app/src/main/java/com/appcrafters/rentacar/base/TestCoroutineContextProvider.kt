package com.appcrafters.rentacar.base

import com.appcrafters.rentacar.base.functional.ICoroutineContextProvider
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlin.coroutines.CoroutineContext

class TestCoroutineContextProvider : ICoroutineContextProvider {
    override val main: CoroutineContext by lazy { TestCoroutineDispatcher() }
    override val io: CoroutineContext by lazy { TestCoroutineDispatcher() }
    override val default: CoroutineContext by lazy { TestCoroutineDispatcher() }
}