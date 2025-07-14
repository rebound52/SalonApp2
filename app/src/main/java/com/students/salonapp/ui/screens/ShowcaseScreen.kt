package com.students.salonapp.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.students.salonapp.ui.theme.salonPinkOnPrimaryContainer
import com.students.salonapp.ui.theme.salonPinkPrimaryContainer
import com.students.salonapp.ui.theme.salonSurfaceLight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowcaseScreen(
    works: List<ShowcaseWork> = listOf(
        ShowcaseWork(
            id = "1",
            title = "Выпрямление кератином",
            description = "Косметическая процедура, которая позволяет сделать волосы прямыми и шелковистыми.",
            imageUrl = "https://i.yapx.ru/Zun98.jpg",
            category = "Выпрямление",
            master = "Анна Петрова",
            date = "2025-06-01"
        ),
        ShowcaseWork(
            id = "2",
            title = "Ботокс для волос",
            description = "Процедура для глубокого восстановления волос с помощью специального состава на основе аминокислот, кератина, масел и витаминов..",
            imageUrl = "https://i.yapx.ru/Zun99.jpg",
            category = "Восстановление",
            master = "Ирина Аллегрова",
            date = "2025-06-10"
        ),
        ShowcaseWork(
            id = "3",
            title = "Химическая завивка",
            description = "Салонная процедура, которая позволяет на длительный срок превратить прямые волосы в кудри.",
            imageUrl = "https://i.yapx.ru/ZuoIw.jpg",
            category = "Завивка",
            master = "Мария Шарапова",
            date = "2025-06-15"
        ),
        ShowcaseWork(
            id = "4",
            title = "Маллет",
            description = "(англ. mullet — кефаль) — тип причёски (стрижки), при которой волосы пострижены коротко спереди и по бокам, а сзади они остаются длинными. ",
            imageUrl = "https://i.yapx.ru/ZuoIy.jpg",
            category = "Стрижка",
            master = "Брок Леснар",
            date = "2025-06-15"
        ),
        ShowcaseWork(
            id = "5",
            title = "Мелирование волос",
            description = "Техника окрашивания, при которой отдельные пряди выделяются за счёт применения более светлых или контрастных оттенков по сравнению с основным цветом.",
            imageUrl = "https://i.yapx.ru/ZuoI0.jpg",
            category = "Окрашивание",
            master = "Василий Ложкин",
            date = "2025-06-15"
        )
    ),
    onWorkSelected: (ShowcaseWork) -> Unit,
    onBack: () -> Unit
) {
    var selectedCategory by remember { mutableStateOf<String?>(null) }
    var showFilters by remember { mutableStateOf(false) }

    rememberInfiniteTransition(label = "filter")

    Box(modifier = Modifier.fillMaxSize()) {
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
                    Text(
                        text = "Портфолио",
                        style = MaterialTheme.typography.headlineSmall,
                        color = salonPinkOnPrimaryContainer
                    )
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
                actions = {
                    IconButton(
                        onClick = { showFilters = !showFilters }
                    ) {
                        Icon(
                            imageVector = Icons.Default.FilterList,
                            contentDescription = "Фильтры",
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

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    AnimatedVisibility(
                        visible = showFilters,
                        enter = slideInVertically() + fadeIn(),
                        exit = slideOutVertically() + fadeOut()
                    ) {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .shadow(4.dp, RoundedCornerShape(16.dp)),
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.surface
                            )
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            ) {
                                Text(
                                    text = "Категории",
                                    style = MaterialTheme.typography.titleLarge,
                                    color = MaterialTheme.colorScheme.onSurface
                                )

                                Spacer(modifier = Modifier.height(16.dp))
                            }
                        }
                    }
                }

                items(
                    works.filter { work ->
                        selectedCategory == null || selectedCategory == "Все" || work.category == selectedCategory
                    }
                ) { work ->
                    WorkCard(
                        work = work,
                        onClick = { onWorkSelected(work) }
                    )
                }
            }
        }
    }
}

@Composable
private fun WorkCard(
    work: ShowcaseWork,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(4.dp, RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        onClick = onClick
    ) {
        Column {
            AsyncImage(
                model = work.imageUrl,
                contentDescription = work.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = work.title,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = work.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = work.master,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    TextButton(
                        onClick = onClick,
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        Text("Подробнее")
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }
        }
    }
}

data class ShowcaseWork(
    val id: String,
    val title: String,
    val description: String,
    val imageUrl: String,
    val category: String,
    val master: String,
    val date: String
)
