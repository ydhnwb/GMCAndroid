package com.annaalfiani.gmcapps

import android.app.Application
import com.annaalfiani.gmcapps.repositories.MovieRepository
import com.annaalfiani.gmcapps.repositories.UserRepository
import com.annaalfiani.gmcapps.ui.detail_movie.DetailViewModel
import com.annaalfiani.gmcapps.ui.login.SignInViewModel
import com.annaalfiani.gmcapps.ui.main.home.HomeViewModel
import com.annaalfiani.gmcapps.ui.main.profile.ProfileViewModel
import com.annaalfiani.gmcapps.ui.order.OrderViewModel
import com.annaalfiani.gmcapps.webservices.ApiCllient
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@MyApp)
            modules(listOf(retrofitModule, repositoryModule, viewModelModule))
        }
    }
}

val retrofitModule = module {
    single { ApiCllient.instance() }
}
val repositoryModule = module {
    factory { UserRepository(get()) }
    factory { MovieRepository(get()) }
}
val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { ProfileViewModel(get()) }
    viewModel { SignInViewModel(get()) }
    viewModel { DetailViewModel(get()) }
    viewModel { OrderViewModel(get()) }
}