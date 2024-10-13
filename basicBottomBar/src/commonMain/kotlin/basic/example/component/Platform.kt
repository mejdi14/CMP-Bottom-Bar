package basic.example.component

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform