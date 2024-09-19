package expand.example.expandable

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform