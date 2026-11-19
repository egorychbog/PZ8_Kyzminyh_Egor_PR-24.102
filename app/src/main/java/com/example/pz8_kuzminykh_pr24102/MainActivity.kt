
package com.example.pz8_kuzminykh_pr24102

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pz8_kuzminykh_pr24102.ui.theme.Pz8_Kuzminykh_PR24102Theme
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import kotlinx.coroutines.delay

/**
 * Главная Activity приложения.
 * Отвечает за запуск экрана часов и даты.
 * Дата создания: 20-11-2026.
 */
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Pz8_Kuzminykh_PR24102Theme(
                dynamicColor = false
            ) {
                Pz8ClockScreen()
            }
        }
    }
}

/**
 * Экран отображения текущего времени и даты.
 * Обновляет время каждую секунду.
 */
@Composable
fun Pz8ClockScreen() {

    var currentDateTime by remember {
        mutableStateOf(LocalDateTime.now())
    }

    LaunchedEffect(Unit) {
        while (true) {
            currentDateTime = LocalDateTime.now()
            delay(1000L)
        }
    }

    Pz8ClockContent(currentDateTime)
}

/**
 * Отображает часы и дату.
 *
 * @param dateTime текущие дата и время.
 */
@Composable
fun Pz8ClockContent(dateTime: LocalDateTime) {

    val russianLocale = Locale.forLanguageTag("ru-RU")

    val currentTime = dateTime.format(
        DateTimeFormatter.ofPattern("HH:mm:ss", russianLocale)
    )

    val currentDate = dateTime.format(
        DateTimeFormatter.ofPattern("d MMMM yyyy", russianLocale)
    )

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "KOTLIN CLOCK",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(35.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .widthIn(max = 420.dp),
                shape = MaterialTheme.shapes.extraLarge,
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 8.dp
                )
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            vertical = 35.dp,
                            horizontal = 18.dp
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = "ТЕКУЩЕЕ ВРЕМЯ",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.secondary
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = currentTime,
                        style = MaterialTheme.typography.displayLarge,
                        color = MaterialTheme.colorScheme.primary,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(30.dp))

                    Text(
                        text = "ТЕКУЩАЯ ДАТА",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.secondary
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = currentDate,
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        textAlign = TextAlign.Center
                    )
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            Text(
                text = "Дата и время обновляются автоматически",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.secondary,
                textAlign = TextAlign.Center
            )
        }
    }
}

/**
 * Предварительный просмотр светлой темы.
 */
@Preview(
    name = "Light Theme",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun Pz8LightClockPreview() {
    Pz8_Kuzminykh_PR24102Theme(
        darkTheme = false,
        dynamicColor = false
    ) {
        Pz8ClockContent(
            LocalDateTime.of(2026, 11, 20, 15, 30, 45)
        )
    }
}

/**
 * Предварительный просмотр тёмной темы.
 */
@Preview(
    name = "Dark Theme",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun Pz8DarkClockPreview() {
    Pz8_Kuzminykh_PR24102Theme(
        darkTheme = true,
        dynamicColor = false
    ) {
        Pz8ClockContent(
            LocalDateTime.of(2026, 11, 20, 15, 30, 45)
        )
    }
}
