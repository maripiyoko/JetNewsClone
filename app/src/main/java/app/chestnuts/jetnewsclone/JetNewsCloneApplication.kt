package app.chestnuts.jetnewsclone

import android.app.Application
import app.chestnuts.jetnewsclone.di.exportedCommonModules
import app.chestnuts.jetnewsclone.di.viewModelModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class JetNewsCloneApplication : Application() {
    companion object {
        const val JETNEWS_APP_URI = "https://developer.android.com/jetnews"
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@JetNewsCloneApplication)
            modules(
                exportedCommonModules +
                viewModelModules
            )
        }
    }
}