package com.example.coroutinecancellationandexceptionhandling

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.example.coroutinecancellationandexceptionhandling.ui.theme.CoroutineCancellationAndExceptionHandlingTheme
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val handler = CoroutineExceptionHandler { _, throwable -> println("Catch: $throwable") }
        lifecycleScope.launch {
            val job = launch {
                try {
                    delay(500)
                } catch (e: Exception) {
                    ensureActive()
                    e.printStackTrace()
                }
                print("Coroutine 1 finished")
            }
            delay(300)
            job.cancel()
        }
    }
}
