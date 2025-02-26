package basic.mejdi14.component

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform