package com.students.salonapp.ui.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Feed
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.students.salonapp.data.models.Service
import com.students.salonapp.ui.components.CategoryChipsRow
import com.students.salonapp.ui.components.ServiceCard
import com.students.salonapp.ui.theme.*

enum class HomeMenuItem {
    PROFILE, FEED, BOOKINGS, SETTINGS, PROMO, SHOWCASE
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    categories: List<Pair<String, String>>,
    onCategoryClick: (String, String) -> Unit,
    sampleServices: List<Service>,
    onServiceClick: (Service) -> Unit,
    onProfileClick: () -> Unit,
    onMenuItemClick: (HomeMenuItem) -> Unit,
    onMapClick: () -> Unit // новый параметр
) {
    var selectedCategory by remember { mutableStateOf<String?>(null) }
    
    // Анимация для приветствия
    val infiniteTransition = rememberInfiniteTransition(label = "welcome")
    val alpha by infiniteTransition.animateFloat(
        initialValue = 0.6f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000),
            repeatMode = RepeatMode.Reverse
        ),
        label = "welcome"
    )

    Box(modifier = Modifier.fillMaxSize()) {
        // Фоновый градиент
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            salonPinkPrimaryContainer,
                            salonSurfaceLight
                        )
                    )
                )
        )
        // Основной контент
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(bottom = 80.dp),
        ) {
            // Верхняя секция с приветствием
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 48.dp, bottom = 24.dp)
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Привет, Вероника",
                        style = MaterialTheme.typography.headlineLarge,
                        color = salonPinkOnPrimaryContainer
                    )
                    Text(
                        text = "Намасте!",
                        style = MaterialTheme.typography.bodyLarge,
                        color = salonPinkOnPrimaryContainer.copy(alpha = alpha)
                    )
                }
                IconButton(
                    onClick = onProfileClick,
                    modifier = Modifier
                        .size(48.dp)
                        .shadow(8.dp, RoundedCornerShape(24.dp))
                        .background(salonPinkOnPrimaryContainer, RoundedCornerShape(24.dp))
                ) {
                    AsyncImage(
                        model = "https://avatars.mds.yandex.net/get-yapic/30955/NLUmmLjsxg2qXO7S4xAy9lFGDkA-1/orig",
                        contentDescription = "Аватар",
                        modifier = Modifier
                            .size(120.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                }
            }

            // Категории с анимацией
            AnimatedVisibility(
                visible = true,
                enter = fadeIn() + slideInVertically(),
                modifier = Modifier.padding(vertical = 16.dp)
            ) {
                CategoryChipsRow(
                    categories = categories,
                    onCategorySelected = { id, title ->
                        selectedCategory = id
                        // Навигация с подстановкой параметров для открытия ServiceListScreen
                        onCategoryClick(id, title)
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            // Промо-баннер
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp) // уменьшена высота
                    .padding(vertical = 8.dp) // уменьшены вертикальные отступы
                    .clickable { onMenuItemClick(HomeMenuItem.PROMO) },
                shape = RoundedCornerShape(20.dp), // чуть меньше скругление
                colors = CardDefaults.cardColors(
                    containerColor = salonPinkPrimaryContainer
                )
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    AsyncImage(
                        model = "https://avatars.mds.yandex.net/i?id=a37c514c08ec1aa2b3c4bf2a24da6729e8d52dee-11922792-images-thumbs&n=13",
                        contentDescription = "Промо-акция",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(10.dp) // уменьшен внутренний паддинг
                    ) {
                        Text(
                            "Скидка 20% на первый визит",
                            style = MaterialTheme.typography.titleMedium, // уменьшен стиль
                            color = Color.White,
                        )
                        Text(
                            "До конца месяца",
                            style = MaterialTheme.typography.bodySmall, // уменьшен стиль
                            color = Color.White
                        )
                    }
                }
            }

            // Список услуг с анимацией
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.weight(1f)
            ) {
                items(
                    items = sampleServices,
                    key = { it.id }
                ) { service ->
                    AnimatedVisibility(
                        visible = true,
                        enter = fadeIn() + slideInVertically()
                    ) {
                        ServiceCard(
                            service = service,
                            onClick = {
                                // Проверка и вывод id для отладки
                                println("[DEBUG] service.id: ${service.id}")
                                if (service.id.isBlank()) {
                                    // Можно показать Snackbar или Toast, если нужно
                                } else {
                                    onServiceClick(service.copy(id = service.id.trim()))
                                }
                            }
                        )
                    }
                }
            }
        }
        // --- Нижнее меню ---
        NavigationBar(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 20.dp, start = 24.dp, end = 24.dp)
                .height(48.dp)
                .clip(RoundedCornerShape(20.dp))
                .shadow(8.dp, RoundedCornerShape(20.dp))
                .widthIn(max = 400.dp),
            tonalElevation = 4.dp,
            containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.98f),
            contentColor = MaterialTheme.colorScheme.onSurface
        ) {
            NavigationBarItem(
                selected = false,
                onClick = { onMenuItemClick(HomeMenuItem.PROFILE) },
                icon = { Icon(Icons.Default.Person, contentDescription = "Профиль", modifier = Modifier.size(18.dp)) },
                label = null,
                alwaysShowLabel = false
            )
            NavigationBarItem(
                selected = false,
                onClick = { onMenuItemClick(HomeMenuItem.FEED) },
                icon = { Icon(Icons.AutoMirrored.Filled.Feed, contentDescription = "Лента", modifier = Modifier.size(18.dp)) },
                label = null,
                alwaysShowLabel = false
            )
            NavigationBarItem(
                selected = false,
                onClick = { onMenuItemClick(HomeMenuItem.BOOKINGS) },
                icon = { Icon(Icons.Default.History, contentDescription = "Записи", modifier = Modifier.size(18.dp)) },
                label = null,
                alwaysShowLabel = false
            )
            NavigationBarItem(
                selected = false,
                onClick = { onMenuItemClick(HomeMenuItem.SHOWCASE) },
                icon = { Icon(Icons.Default.Star, contentDescription = "Showcase", modifier = Modifier.size(18.dp)) },
                label = null,
                alwaysShowLabel = false
            )
            NavigationBarItem(
                selected = false,
                onClick = { onMenuItemClick(HomeMenuItem.SETTINGS) },
                icon = { Icon(Icons.Default.Settings, contentDescription = "Настройки", modifier = Modifier.size(18.dp)) },
                label = null,
                alwaysShowLabel = false
            )
        }
        // --- FAB ---
        FloatingActionButton(
            onClick = onMapClick,
            containerColor = salonPinkPrimaryContainer,
            contentColor = salonPinkOnPrimaryContainer,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 32.dp, bottom = 64.dp)
        ) {
            Icon(Icons.Default.Map, contentDescription = "Карта")
        }
    }
}
