package com.julickot.friendsapp

import android.app.Application
import android.content.Context
import com.julickot.friendsapp.data.*
import com.julickot.friendsapp.ui.details.DetailViewModel
import com.julickot.friendsapp.ui.home.HomeViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    fun provideDao(context: Context): UserDao = UserDatabase.getInstance(context).userDao
    fun provideRepository(dao: UserDao, api: FirebaseApiService): Repository = RepositoryImpl(dao, api)
    fun provideSettings(app: Application): Settings = SettingsImpl(app)

    single {
        provideDao(androidContext())
    }

    single {
        NetworkService.firebaseService
    }

    viewModel {
        DetailViewModel(get(), get())
    }

    viewModel {
        HomeViewModel(get(), get())
    }

    factory {
        provideRepository(get(), get())
    }

    factory {
        provideSettings(androidApplication())
    }
}