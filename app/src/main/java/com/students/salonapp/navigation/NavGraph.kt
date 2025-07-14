package com.students.salonapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.students.salonapp.data.models.Promo
import com.students.salonapp.data.models.Service
import com.students.salonapp.ui.screens.BookingConfirmationScreen
import com.students.salonapp.ui.screens.BookingHistoryScreen
import com.students.salonapp.ui.screens.DateTimePickerScreen
import com.students.salonapp.ui.screens.HelpScreen
import com.students.salonapp.ui.screens.HomeMenuItem
import com.students.salonapp.ui.screens.HomeScreen
import com.students.salonapp.ui.screens.InstagramFeedScreen
import com.students.salonapp.ui.screens.MapSearchScreen
import com.students.salonapp.ui.screens.MasterListScreen
import com.students.salonapp.ui.screens.ProfileScreen
import com.students.salonapp.ui.screens.PromoScreen
import com.students.salonapp.ui.screens.ServiceDetailScreen
import com.students.salonapp.ui.screens.ServiceListScreen
import com.students.salonapp.ui.screens.SettingsScreen
import com.students.salonapp.ui.screens.ShowcaseScreen
import com.students.salonapp.ui.screens.SplashScreen
import com.students.salonapp.ui.screens.WalletScreen



@Composable
fun SalonNavGraph(
    isDarkTheme: Boolean = false,
    onThemeChange: (Boolean) -> Unit = {}
) {
    val navController = rememberNavController()

    val promos = listOf(
        Promo(
            id = "1",
            title = "Скидка 20% на первое посещение",
            description = "При первом посещении салона",
            imageUrl = "https://https://avatars.mds.yandex.net/i?id=54efc9b51cb0dd3f027fa3b8c15e1b6e_l-5236122-images-thumbs&n=13.jpg",
            discount = 20,
            endDate = "2025-12-01",
            conditions = "При первом посещении"
        ),
        Promo(
            id = "2",
            title = "Бесплатная консультация стилиста",
            description = "При записи на любую услугу",
            imageUrl = "https://avatars.mds.yandex.net/i?id=fc71856cbc099fb9690add5734aacb2b_l-5270087-images-thumbs&n=13",
            discount = 0,
            endDate = "2025-11-15",
            conditions = "При записи на услугу"
        ),
        Promo(
            id = "3",
            title = "Подарок при записи онлайн",
            description = "При записи через приложение",
            imageUrl = "https://avatars.mds.yandex.net/i?id=b9035612dd465054c1d9cf1bc65b67d7_l-5258986-images-thumbs&n=13",
            discount = 0,
            endDate = "2025-9-30",
            conditions = "При онлайн-записи"
        )
    )

    val categories = listOf(
        "promo" to "Примеры работ",
        "haircut" to "Стрижка",
        "color" to "Окрашивание",
        "styling" to "Стайлинг"
    )

    val sampleServices = listOf(
        Service(
            id = "2a5e8db6-97b9-4a3a-81d4-289ab8b0dc22",
            name = "Балаяж",
            description = "Профессиональное окрашивание волос балаяж",
            duration = 120,
            price = 199.0,
            image_url = "https://avatars.mds.yandex.net/get-altay/5456749/2a0000017cdacd6564a4d79571ed2b1519ac/XXXL",
            category_id = "2a5e8db6-97b9-4a3a-81d4-289ab8b0dc22"
        ),
        Service(
            id = "e08679a8-dd3f-4bb2-a019-a45c158dfac8",
            name = "Окрашивание",
            description = "Полное окрашивание головы высококачественными красителями",
            duration = 90,
            price = 99.0,
            image_url = "https://avatars.mds.yandex.net/i?id=3d2c8328ded5f3e9d440e688dedbcc2ad152fd5a-5222573-images-thumbs&n=13",
            category_id = "e08679a8-dd3f-4bb2-a019-a45c158dfac8"
        )
    )

    NavHost(
        navController = navController,
        startDestination = Routes.SPLASH_SCREEN
    ) {
        composable(Routes.SPLASH_SCREEN) {
            SplashScreen(onContinue = {
                navController.navigate(Routes.HOME_SCREEN) {
                    popUpTo(Routes.SPLASH_SCREEN) { inclusive = true }
                }
            })
        }
        composable(Routes.PROMO_SCREEN) {
            PromoScreen(
                onBack = { navController.popBackStack() },
                onContinueClicked = {
                    navController.navigate(Routes.HOME_SCREEN) {
                        popUpTo(Routes.PROMO_SCREEN) { inclusive = true }
                    }
                },
                promos = promos
            )
        }
        composable(Routes.HOME_SCREEN) {
            HomeScreen(
                categories = categories,
                onCategoryClick = { id, title ->
                    navController.navigate("service_list_screen/$id/$title")
                },
                sampleServices = sampleServices,
                onServiceClick = { service ->
                    navController.navigate("service_detail_screen/${service.id}")
                },
                onProfileClick = {
                    navController.navigate(Routes.PROFILE_SCREEN)
                },
                onMenuItemClick = { menuItem ->
                    when (menuItem) {
                        HomeMenuItem.PROFILE -> navController.navigate(Routes.PROFILE_SCREEN)
                        HomeMenuItem.FEED -> navController.navigate("instagram_feed_screen")
                        HomeMenuItem.BOOKINGS -> navController.navigate(Routes.BOOKING_HISTORY_SCREEN)
                        HomeMenuItem.SETTINGS -> navController.navigate("settings_screen")
                        HomeMenuItem.PROMO -> navController.navigate(Routes.PROMO_SCREEN)
                        HomeMenuItem.SHOWCASE -> navController.navigate("showcase_screen")
                    }
                },
                onMapClick = {
                    navController.navigate("map_search_screen")
                }
            )
        }
        composable(
            route = Routes.SERVICE_LIST_SCREEN,
            arguments = listOf(
                navArgument("categoryId") { type = NavType.StringType },
                navArgument("categoryTitle") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            ServiceListScreen(
                onBack = { navController.popBackStack() },
                onServiceClick = { service ->
                    navController.navigate("master_list_screen/${service.id}/${service.name}/${service.price}")
                }
            )
        }
        composable(
            "master_list_screen/{serviceId}/{serviceName}/{price}",
            arguments = listOf(
                navArgument("serviceId") { type = NavType.StringType },
                navArgument("serviceName") { type = NavType.StringType },
                navArgument("price") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val serviceId = backStackEntry.arguments?.getString("serviceId") ?: ""
            val serviceName = backStackEntry.arguments?.getString("serviceName") ?: ""
            val price = backStackEntry.arguments?.getString("price")?.toDoubleOrNull() ?: 0.0
            MasterListScreen(
                onBack = { navController.popBackStack() },
                onMasterClick = { masterId, masterName ->
                    navController.navigate("date_time_picker_screen/$serviceId/$serviceName/$price/$masterId/$masterName")
                }
            )
        }
        composable(
            "date_time_picker_screen/{serviceId}/{serviceName}/{price}/{masterId}/{masterName}",
            arguments = listOf(
                navArgument("serviceId") { type = NavType.StringType },
                navArgument("serviceName") { type = NavType.StringType },
                navArgument("price") { type = NavType.StringType },
                navArgument("masterId") { type = NavType.StringType },
                navArgument("masterName") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val serviceId = backStackEntry.arguments?.getString("serviceId") ?: ""
            val serviceName = backStackEntry.arguments?.getString("serviceName") ?: ""
            val price = backStackEntry.arguments?.getString("price")?.toDoubleOrNull() ?: 0.0
            val masterId = backStackEntry.arguments?.getString("masterId") ?: ""
            val masterName = backStackEntry.arguments?.getString("masterName") ?: ""
            DateTimePickerScreen(
                serviceName = serviceName,
                masterName = masterName,
                onDateTimeSelected = { date, time ->
                    navController.navigate("booking_confirmation_screen/$serviceId/$serviceName/$price/$masterId/$masterName/$date/$time")
                },
                onBack = { navController.popBackStack() }
            )
        }
        composable(
            "booking_confirmation_screen/{serviceId}/{serviceName}/{price}/{masterId}/{masterName}/{date}/{time}",
            arguments = listOf(
                navArgument("serviceId") { type = NavType.StringType },
                navArgument("serviceName") { type = NavType.StringType },
                navArgument("price") { type = NavType.StringType },
                navArgument("masterId") { type = NavType.StringType },
                navArgument("masterName") { type = NavType.StringType },
                navArgument("date") { type = NavType.StringType },
                navArgument("time") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val serviceId = backStackEntry.arguments?.getString("serviceId") ?: ""
            val serviceName = backStackEntry.arguments?.getString("serviceName") ?: ""
            val masterId = backStackEntry.arguments?.getString("masterId") ?: ""
            val masterName = backStackEntry.arguments?.getString("masterName") ?: ""
            val date = backStackEntry.arguments?.getString("date") ?: ""
            val time = backStackEntry.arguments?.getString("time") ?: ""
            val price = backStackEntry.arguments?.getString("price")?.toDoubleOrNull() ?: 0.0
            BookingConfirmationScreen(
                serviceId = serviceId,
                masterId = masterId,
                serviceName = serviceName,
                masterName = masterName,
                date = date,
                time = time,
                price = price,
                onConfirm = { navController.popBackStack(Routes.HOME_SCREEN, false) },
                onBack = { navController.popBackStack() }
            )
        }
        composable(Routes.PROFILE_SCREEN) {
            ProfileScreen(
                onEditProfile = { /* TODO */ },
                onViewHistory = { navController.navigate(Routes.BOOKING_HISTORY_SCREEN) },
                onViewWallet = { navController.navigate(Routes.WALLET_SCREEN) },
                onViewSettings = { navController.navigate("settings_screen") },
                onViewHelp = { navController.navigate("help_screen") },
                onViewMap = { navController.navigate("map_search_screen") },
                onLogout = {
                    //  signOut (Supabase) попраавить код позже
                    // SupabaseClientInstance.client.auth.signOut()
                }
            )
        }
        composable(Routes.BOOKING_HISTORY_SCREEN) {
            BookingHistoryScreen(
                onBack = { navController.popBackStack() }
            )
        }
        composable(Routes.WALLET_SCREEN) {
            WalletScreen(
                onBack = { navController.popBackStack() }
            )
        }
        composable("settings_screen") {
            SettingsScreen(
                isDarkTheme = isDarkTheme, // или передайте актуальное состояние темы, если требуется
                onThemeChange = onThemeChange, // если нужно, реализуйте смену темы глобально
                onBack = { navController.popBackStack() }
            )
        }
        composable("help_screen") {
            HelpScreen(
                onBack = { navController.popBackStack() }
            )
        }
        composable(
            route = "service_detail_screen/{serviceId}",
            arguments = listOf(
                navArgument("serviceId") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val serviceId = backStackEntry.arguments?.getString("serviceId") ?: ""
            ServiceDetailScreen(
                serviceId = serviceId,
                onBack = { navController.popBackStack() },
                onSelectMaster = { sId, sName, price ->
                    navController.navigate("master_list_screen/$sId/$sName/$price")
                }
            )
        }
        composable("instagram_feed_screen") {
            InstagramFeedScreen(
                onBack = { navController.popBackStack() }
            )
        }
        composable("showcase_screen") {
            ShowcaseScreen(
                onWorkSelected = {},
                onBack = { navController.popBackStack() }
            )
        }
        composable("map_search_screen") {
            MapSearchScreen()
        }
    }
}