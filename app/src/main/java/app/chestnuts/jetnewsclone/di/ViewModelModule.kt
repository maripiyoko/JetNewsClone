package app.chestnuts.jetnewsclone.di

import app.chestnuts.jetnewsclone.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val viewModelModules = module {
    viewModel { HomeViewModel(get()) }
}