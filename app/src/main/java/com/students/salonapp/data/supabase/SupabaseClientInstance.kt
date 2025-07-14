package com.students.salonapp.data.supabase

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.auth.Auth


private const val SUPABASE_URL = "https://zqywkdvqziytyqwxyuix.supabase.co"
private const val SUPABASE_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InpxeXdrZHZxeml5dHlxd3h5dWl4Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3NTAyNjkzNzMsImV4cCI6MjA2NTg0NTM3M30.bUPIPqZnO69pVDXPzhEW1NJZ4baJT7mC-weS6vc1tgo"

object SupabaseClientInstance {
    //  инстанс SupabaseClient в приложении
    val client: SupabaseClient by lazy {
        createSupabaseClient(
            supabaseUrl = SUPABASE_URL,
            supabaseKey = SUPABASE_KEY
        ) {
            install(Postgrest)
            install(Auth)
        }
    }
}

