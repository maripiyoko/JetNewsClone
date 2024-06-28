package app.chestnuts.jetnewsclone

import android.app.Application
import app.chestnuts.jetnewsclone.data.AppContainer
import app.chestnuts.jetnewsclone.data.AppContainerImpl

class JetNewsCloneApplication : Application() {
    companion object {
        const val JETNEWS_APP_URI = "https://developer.android.com/jetnews"
    }

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppContainerImpl(this)
    }
}