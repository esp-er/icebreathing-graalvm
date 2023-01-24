import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme

import androidx.compose.ui.unit.*
import androidx.compose.material.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import club.eridani.compose.jwm.ApplicationWindow
import club.eridani.compose.jwm.ManageWindow
import kotlin.system.exitProcess

import io.github.humbleui.jwm.App

fun main() {
    //System.setProperty("skiko.library.path", ".")
    App.start {
        val winSize = IntSize(450,580)
        val app = ApplicationWindow(
            onClose = { exitProcess(0) },
            title = "Ice Breathing",
            winSize = winSize,
            winPos = IntSize(200,200)
        ){
            System.setProperty("skiko.vsync.enabled", "false")
            var title by remember { mutableStateOf("Ice Breathing") }
            ManageWindow(
                title = title,
                )


            val buttonVisible = true
            val composableScope = rememberCoroutineScope()
            var thisSession by remember{ mutableStateOf( SessionData(30, 6, emptyMap()) )}

            fun showButtons() {
                /*composableScope.launch {
                    if (!buttonVisible) {
                        buttonVisible = true
                        delay(3000)
                        buttonVisible = false
                    }
                }*/
            }
            fun clickedBack(){
                //loadSettings(appConfig)
                AppState.screenState(ScreenType.Start)
            }
            IceBreathingTheme {
                Surface(
                    color = MaterialTheme.colors.background,
                    contentColor = MaterialTheme.colors.background,
                    modifier = Modifier.fillMaxSize(1f)
                        //.pointerMoveFilter (onMove = { showButtons(); false }
                ){
                    //todo: at finished we transition to a breath hold screen // different animation
                    fun clickedStartBreathing(s: SessionData) {
                        thisSession = s
                        AppState.screenState(ScreenType.Breathe)
                    }
                    if(AppState.screenState() == ScreenType.Start) {
                        StartScreen(::clickedStartBreathing)
                    }
                    else{
                        BreatheScreen(buttonVisible, thisSession, ::clickedBack)
                    }
                }
            }


        }

        val bounds = app.getBounds()
        app.setPos(bounds.width / 2 - winSize.width / 2, bounds.height / 2 - winSize.height / 2)
    }
}