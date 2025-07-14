package com.students.salonapp.data.supabase

import com.students.salonapp.data.models.*
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Order
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SupabaseRepository(
    client: SupabaseClient = SupabaseClientInstance.client,
    private val useStub: Boolean = false // флаг для использования заглушек !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
) {

    private val rest = client.postgrest

    internal val stubServices = listOf(
        Service(id = "2a5e8db6-97b9-4a3a-81d4-289ab8b0dc22", name = "Балаяж", description = "Профессиональное окрашивание волос балаяж", price = 199.0, duration = 120, image_url = "https://avatars.mds.yandex.net/get-altay/5456749/2a0000017cdacd6564a4d79571ed2b1519ac/XXXL", category_id = "2a5e8db6-97b9-4a3a-81d4-289ab8b0dc22"),
        Service(id = "e08679a8-dd3f-4bb2-a019-a45c158dfac8", name = "Окрашивание", description = "Полное окрашивание головы высококачественными красителями", price = 99.0, duration = 90, image_url = "https://avatars.mds.yandex.net/i?id=3d2c8328ded5f3e9d440e688dedbcc2ad152fd5a-5222573-images-thumbs&n=13", category_id = "e08679a8-dd3f-4bb2-a019-a45c158dfac8"),
        Service(id = "d4e5f678-90ab-cdef-1234-567890abcdef", name = "Стайлинг волос", description = "Styling волос.", price = 1000.0, duration = 40, image_url = "https://avatars.mds.yandex.net/get-mpic/11771522/2a0000018b15e4470aff8a5d05dc00a491f0/orig", category_id = "a1b2c3d4-e5f6-7890-abcd-ef1234567890"),
        Service(id = "e5f67890-abcd-ef12-3456-7890abcdef12", name = "Женская стрижка", description = "Стрижка для женщин.", price = 1500.0, duration = 60, image_url = "https://yandex-images.clstorage.net/g5IMI1164/4acd80EJhw/8B98f6lDIdijPFoVcjk213YcewQzJO_7gmZb9Rl4NXwq-LdVlL3saRnq6S1RqZEVXAg5t6HBbI5ytyC_LhKOIL5POS_iHWbYpigRHz4jOsdjUGZHJmb2_4U_V_NwFxIICpSeP7zCkRdRv7UvArXdVxpvA_TY7hmNRPBOkhNK4VvXOE55kEFb8rEkGSdbJjD8dnFwKCFm3o2Tdz-iWhg3GxK6giW5wJoIb4iIPQqFvVEiajuh__AwrkPFb7EkRs1Y7g1CSIl2bNGMJjUqfBkK-U1lQhYAT8qXuEAyi2wHAQwaoaQcg9-3M2-mnyYpu6lSKmAekOnDN5l16WyiMGn_Z8kZenWZVXf-iCodD0c4AcRsc2EQNUjjrJxkNIdLGSMlJYarL5TWrxRwk5wuK4GiYiR_FJjF_zeJWstcuihcz1jtCEJ5tHVK9783ESVgPDj2bHtiLh5Y1peuSgelXD0SICaikAKl4ZUgQ4q5HA6nkUgBQxKewfElq3jkQpUIXvZk3DR5Xa9oW9qDNyYUSzo89WdGWzUlccqqs28ruV4dBTsigLsfsvWXLk-ViBsSq71jInkrmvTGEqB5zk2wJnLgQswLZGawXm7erA4SH2saAt5YUVMFBHLHkI1yDZNdLgIsJ4SjF5nVnx1ntJ45IoeJfCpoGZfHxj6GRNBLpzJa8kXtCUpMtl9t7awrEy9-JjP2X2JGMi5Y7KmPVyWFZQMJOjCYtx208I4wZ4qCCiOlqkIMRA-gx8I2mVbRW6YPa89X9S9Oa5BdY8CFKzMreCMX3WFITAIsY_WBgUQuq3sKPTg_urAAmvWxEEavkw4moKBKBFkXt_rXB51F8k6ZOk7yR_Y7Y1S_aWjfrTc9OUsxM-VCaFIJD37Qq4VhFZl1IwExCrumJ73vixZKp7MzM4u7dgdfMZnD1RqYduBruiNV51PLN0x3qWFB_qY3HQ93EzHGd2tDJCFyxYCcdiquVzw",category_id = "a1b2c3d4-e5f6-7890-abcd-ef1234567890"),
        Service(id = "f67890ab-cdef-1234-5678-90abcdef1234", name = "Маникюр классический", description = "Уход за руками и ногтями.", price = 1200.0, duration = 50, image_url = "https://womanday.top/uploads/posts/2023-09/1695826991_womanday-top-p-manikyur-bogatikh-zhenshchin-2023-48.jpg", category_id = "b2c3d4e5-f678-90ab-cdef-1234567890ab")
    )

    internal val stubMasters = listOf(
        Master(id = "aabbccdd-eeff-0011-2233-445566778899", name = "Иван Иванов", rating = 5.0, bio = "Парикмахер", image_url = "https://avatars.mds.yandex.net/i?id=d9eff7b7ffe7b72c8b11437c5aa95084_l-9222726-images-thumbs&n=13"),
        Master(id = "bbccddee-ff00-1122-3344-556677889900", name = "Мария Петрова", rating = 4.8, bio = "Мастер маникюра", image_url = "https://avatars.mds.yandex.net/i?id=1cb860ee536b80c7e936ffbfd79f18c1_l-5150957-images-thumbs&n=13")
    )

    internal val stubBookings = listOf(
        Booking(id = "cdef1234-5678-90ab-cdef-1234567890ab", user_id = "test_user", service_id = "d4e5f678-90ab-cdef-1234-567890abcdef", master_id = "aabbccdd-eeff-0011-2233-445566778899", date = "2025-06-03", time = "10:00", total_price = 1000.0),
        Booking(id = "def12345-6789-0abc-def1-234567890abc", user_id = "test_user", service_id = "e5f67890-abcd-ef12-3456-7890abcdef12", master_id = "aabbccdd-eeff-0011-2233-445566778899", date = "2025-06-04", time = "12:00", total_price = 1500.0)
    )

    suspend fun getServiceById(serviceId: String): Service? =
        if (useStub) stubServices.find { it.id == serviceId }
        else try {
            withContext(Dispatchers.IO) {
                rest["services"].select {
                    filter { eq("id", serviceId) }
                }.decodeSingleOrNull<Service>()
            } ?: getServiceRoomRepoSafely()?.getAllServices()?.find { it.id == serviceId } ?: stubServices.find { it.id == serviceId }
        } catch (_: Exception) {
            getServiceRoomRepoSafely()?.getAllServices()?.find { it.id == serviceId } ?: stubServices.find { it.id == serviceId }
        }

    suspend fun getMasterById(masterId: String): Master? =
        if (useStub) stubMasters.find { it.id == masterId }
        else try {
            withContext(Dispatchers.IO) {
                rest["masters"].select {
                    filter { eq("id", masterId) }
                }.decodeSingleOrNull<Master>()
            } ?: getMasterRoomRepoSafely()?.getAllMasters()?.find { it.id == masterId } ?: stubMasters.find { it.id == masterId }
        } catch (_: Exception) {
            getMasterRoomRepoSafely()?.getAllMasters()?.find { it.id == masterId } ?: stubMasters.find { it.id == masterId }
        }

    suspend fun createBooking(booking: BookingCreateRequest): Booking? =
        if (useStub) Booking(
            id = (stubBookings.size + 1).toString(),
            user_id = booking.user_id,
            service_id = booking.service_id,
            master_id = booking.master_id,
            date = booking.date,
            time = booking.time,
            total_price = booking.total_price
        )
        else withContext(Dispatchers.IO) {
            rest["bookings"].insert(booking) { single() }.decodeSingleOrNull<Booking>()
        }

    suspend fun getUserBookings(userId: String): List<Booking> =
        if (useStub) stubBookings.filter { it.user_id == userId }
        else try {
            val result = withContext(Dispatchers.IO) {
                rest["bookings"].select {
                    filter { eq("user_id", userId) }
                    order("date", Order.DESCENDING)
                }.decodeList<Booking>()
            }
            when {
                result.isNotEmpty() -> result
                else -> {
                    // fallback на Room
                    try {
                        val roomRepo = getBookingRoomRepoSafely()
                        val local = roomRepo?.getUserBookings(userId)
                        if (!local.isNullOrEmpty()) local else emptyList()
                    } catch (_: Exception) {
                        emptyList()
                    }
                }
            }
        } catch (e: Exception) {
            try {
                val roomRepo = getBookingRoomRepoSafely()
                val local = roomRepo?.getUserBookings(userId)
                if (!local.isNullOrEmpty()) local else emptyList()
            } catch (_: Exception) {
                emptyList()
            }
        }

    private fun getBookingRoomRepoSafely(): com.students.salonapp.data.local.BookingRoomRepository? {
        return try {
            com.students.salonapp.App.instance?.bookingRoomRepo
        } catch (_: Exception) {
            null
        }
    }

    private fun getServiceRoomRepoSafely(): com.students.salonapp.data.local.ServiceRoomRepository? {
        return try {
            com.students.salonapp.App.instance?.serviceRoomRepo
        } catch (_: Exception) {
            null
        }
    }
    private fun getMasterRoomRepoSafely(): com.students.salonapp.data.local.MasterRoomRepository? {
        return try {
            com.students.salonapp.App.instance?.masterRoomRepo
        } catch (_: Exception) {
            null
        }
    }
}