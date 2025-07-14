package com.students.salonapp.ui.screens

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.students.salonapp.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeScreenNavigationTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun fab_navigates_to_map() {
        composeTestRule.onNodeWithContentDescription("Карта").performClick()
        composeTestRule.onNodeWithText("Поиск...").assertIsDisplayed()
    }

    @Test
    fun profile_icon_navigates_to_profile() {
        composeTestRule.onAllNodesWithContentDescription("Аватар").onFirst().performClick()
        composeTestRule.onNodeWithText("Редактировать профиль").assertIsDisplayed()
    }

    @Test
    fun bottom_nav_navigates_to_feed() {
        composeTestRule.onAllNodesWithContentDescription("Лента").onFirst().performClick()
        composeTestRule.onNodeWithText("Instagram").assertExists()
    }

    @Test
    fun bottom_nav_navigates_to_settings() {
        composeTestRule.onAllNodesWithContentDescription("Настройки").onFirst().performClick()
        composeTestRule.onNodeWithText("Тема оформления").assertIsDisplayed()
    }
}

