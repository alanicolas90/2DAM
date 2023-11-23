package com.example.retrofitrecyclerview.data.repositories

import com.example.retrofitrecyclerview.data.di.RemoteDataSource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ActivityRetainedScoped
class CustomerRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) {
    suspend fun getAllCustomers() = withContext(Dispatchers.IO) { remoteDataSource.getCustomers() }
}