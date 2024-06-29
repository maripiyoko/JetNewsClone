package app.chestnuts.jetnewsclone.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import app.chestnuts.jetnewsclone.JetNewsCloneApplication

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        val appContainer = (application as JetNewsCloneApplication).container
        setContent {
            val widthSizeClass = calculateWindowSizeClass(activity = this).widthSizeClass
            JetNewsCloneApp(
                appContainer = appContainer,
                widthSizeClass = widthSizeClass
            )
        }
    }
}