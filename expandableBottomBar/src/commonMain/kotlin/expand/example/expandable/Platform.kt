package expand.mejdi14.expandable

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform