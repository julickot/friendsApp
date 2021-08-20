package com.julickot.friendsapp.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

interface Repository {
    fun getUsers(): Flow<List<User>>
    suspend fun refreshData()
}

class RepositoryImpl(private val userDao: UserDao, private val api: FirebaseApiService): Repository {

    private val data = userDao.getAll()

    override fun getUsers() = data

    override suspend fun refreshData() {
        withContext(Dispatchers.IO) {
            val data = api.getUsers()
            userDao.clear()
            userDao.addAll(data)
        }
    }
}