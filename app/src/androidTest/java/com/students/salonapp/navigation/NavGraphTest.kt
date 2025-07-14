package com.students.salonapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.compose.rememberNavController
import org.junit.Rule
import org.junit.Test

class NavGraphTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun navGraph_startsAtSplashScreen() {
        composeTestRule.setContent {
            SalonNavGraph()
        }
        composeTestRule.onNodeWithText("Splash", substring = true).assertExists()
    }
}

