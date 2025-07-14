package com.students.salonapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.students.salonapp.ui.theme.SalonappTheme
import com.students.salonapp.ui.theme.salonPinkOnPrimaryContainer
import com.students.salonapp.ui.theme.salonPinkPrimaryContainer
import com.students.salonapp.ui.theme.salonSurfaceLight
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * DateTimePickerScreen:
 * - Розовая панель с названием сервиса и мастера (имена передаются из Nav или SharedViewModel)
 * - Белый фон под панелью
 * - Сам компонент DateTimePicker, занимающий весь остальной экран
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateTimePickerScreen(
    serviceName: String,
    masterName: String,
    onDateTimeSelected: (String, String) -> Unit,
    onBack: () -> Unit
) {
    val today = remember { LocalDate.now() }
    val dates = remember { (0..6).map { today.plusDays(it.toLong()) } }
    var selectedDate by remember { mutableStateOf(today) }
    var selectedTime by remember { mutableStateOf("") }
    val timeslots = listOf("09:00", "10:30", "12:00", "14:00", "16:00", "18:00")
    val dayNames = mapOf(
        "MON" to "Пн", "TUE" to "Вт", "WED" to "Ср", "THU" to "Чт", "FRI" to "Пт", "SAT" to "Сб", "SUN" to "Вс"
    )
    Box(modifier = Modifier.fillMaxSize()) {
        // Фоновый градиент
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            salonPinkPrimaryContainer.copy(alpha = 0.1f),
                            salonSurfaceLight
                        )
                    )
                )
        )
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = "Выбор даты и времени",
                            style = MaterialTheme.typography.headlineSmall,
                            color = salonPinkOnPrimaryContainer
                        )
                        Text(
                            text = "$serviceName, $masterName",
                            style = MaterialTheme.typography.bodyMedium,
                            color = salonPinkOnPrimaryContainer.copy(alpha = 0.7f)
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Назад",
                            tint = salonPinkOnPrimaryContainer
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = salonPinkPrimaryContainer,
                    navigationIconContentColor = salonPinkOnPrimaryContainer,
                    actionIconContentColor = salonPinkOnPrimaryContainer
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Выберите дату:",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
            ) {
                items(dates) { date ->
                    val isSelected = date == selectedDate
                    val backgroundColor = if (isSelected) salonPinkPrimaryContainer else salonSurfaceLight
                    val textColor = if (isSelected) salonPinkOnPrimaryContainer else MaterialTheme.colorScheme.onSurfaceVariant
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .width(80.dp)
                            .clip(MaterialTheme.shapes.medium)
                            .background(backgroundColor)
                            .clickable { selectedDate = date }
                            .padding(vertical = 8.dp)
                    ) {
                        Text(
                            text = dayNames[date.dayOfWeek.name.substring(0, 3)] ?: "",
                            style = MaterialTheme.typography.bodyMedium,
                            color = textColor
                        )
                        Text(
                            text = date.format(DateTimeFormatter.ofPattern("dd.MM")),
                            style = MaterialTheme.typography.bodyLarge,
                            color = textColor
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Выберите время:",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            androidx.compose.foundation.layout.FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
            ) {
                timeslots.forEach { time ->
                    val isSelected = time == selectedTime
                    val backgroundColor = if (isSelected) salonPinkPrimaryContainer else salonSurfaceLight
                    val textColor = if (isSelected) salonPinkOnPrimaryContainer else MaterialTheme.colorScheme.onSurfaceVariant
                    Text(
                        text = time,
                        style = MaterialTheme.typography.bodyLarge,
                        color = textColor,
                        modifier = Modifier
                            .clip(MaterialTheme.shapes.small)
                            .background(backgroundColor)
                            .clickable { selectedTime = time }
                            .padding(horizontal = 24.dp, vertical = 16.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = {
                    val dateStr = selectedDate.format(DateTimeFormatter.ISO_LOCAL_DATE)
                    onDateTimeSelected(dateStr, selectedTime)
                },
                enabled = selectedTime.isNotEmpty(),
                modifier = Modifier.align(Alignment.End).padding(horizontal = 16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = salonPinkPrimaryContainer,
                    contentColor = salonPinkOnPrimaryContainer
                )
            ) {
                Text(text = "Далее", style = MaterialTheme.typography.labelLarge)
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun DateTimePickerScreenPreview() {
    SalonappTheme {
        DateTimePickerScreen(
            serviceName = "Массаж",
            masterName = "Иванов Иван",
            onDateTimeSelected = { date, time -> },
            onBack = {}
        )
    }
}
