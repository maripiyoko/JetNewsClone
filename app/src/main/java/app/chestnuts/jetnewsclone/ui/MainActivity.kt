package app.chestnuts.jetnewsclone.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import app.chestnuts.jetnewsclone.JetNewsCloneApplication

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        val appContainer = (application as JetNewsCloneApplication).container
        setContent {
            JetNewsCloneApp(appContainer = appContainer)
        }
    }
}