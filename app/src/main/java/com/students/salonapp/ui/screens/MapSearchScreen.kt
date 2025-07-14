package com.students.salonapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.students.salonapp.ui.theme.salonPinkOnPrimaryContainer
import com.students.salonapp.ui.theme.salonPinkPrimaryContainer
import com.students.salonapp.ui.theme.salonSurfaceLight

@SuppressLint("DefaultLocale")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapSearchScreen() {
    var search by remember { mutableStateOf("") }
    var price by remember { mutableFloatStateOf(41.36f) }
    val location = LatLng(56.6388, 47.8908)
    val cameraPositionState = rememberCameraPositionState {
        position = com.google.android.gms.maps.model.CameraPosition.fromLatLngZoom(location, 14f)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(salonSurfaceLight)
    ) {
        // Поисковая строка
        OutlinedTextField(
            value = search,
            onValueChange = { search = it },
            placeholder = { Text("Поиск...") },
            leadingIcon = { Icon(Icons.Default.LocationOn, contentDescription = null) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(24.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = salonPinkPrimaryContainer,
                unfocusedBorderColor = Color.LightGray
            )
        )
        // Карта
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = Color.Transparent)
        ) {
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState
            ) {
                Marker(
                    state = com.google.maps.android.compose.MarkerState(position = location),
                    title = "Салон красоты",
                    snippet = "Йошкар-Ола, ул. Примерерная 108 пом. 33"
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        // Слайдер цены
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 24.dp)
        ) {
            Text("Цена", fontWeight = FontWeight.Medium)
            Spacer(modifier = Modifier.width(8.dp))
            Slider(
                value = price,
                onValueChange = { price = it },
                valueRange = 0f..100f,
                steps = 10,
                colors = SliderDefaults.colors(
                    thumbColor = salonPinkPrimaryContainer,
                    activeTrackColor = salonPinkPrimaryContainer
                ),
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(String.format("%.2f₽", price), fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.height(16.dp))
        // Фильтры рейтинга (чипсы)
        Text("Рейтинг", modifier = Modifier.padding(start = 24.dp, bottom = 4.dp))
        Row(
            modifier = Modifier.padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            (1..5).forEach { rating ->
                FilterChip(
                    selected = false,
                    onClick = { /* TODO: фильтр по рейтингу */ },
                    label = { Text(rating.toString()) },
                    shape = RoundedCornerShape(16.dp),
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = salonPinkPrimaryContainer,
                        selectedLabelColor = salonPinkOnPrimaryContainer
                    )
                )
            }
        }
        // TODO: Вывод результатов поиска
    }
}
