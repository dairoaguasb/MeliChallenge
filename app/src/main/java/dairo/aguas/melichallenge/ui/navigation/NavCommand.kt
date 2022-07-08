package dairo.aguas.melichallenge.ui.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class NavCommand(
    internal val feature: Feature,
    internal val subRoute: String = SUB_ROUTE,
    private val navArgs: List<NavArg> = emptyList()
) {
    class ContentType(feature: Feature) : NavCommand(feature)

    class ContentTypeDetail(feature: Feature) :
        NavCommand(feature, DETAIL, listOf(NavArg.ItemId)) {
        fun createRoute(itemId: String) = "${feature.route}/$subRoute/$itemId"
    }

    val route = run {
        val argValues = navArgs.map { "{${it.key}}" }
        listOf(feature.route)
            .plus(subRoute)
            .plus(argValues)
            .joinToString("/")
    }

    val args = navArgs.map {
        navArgument(it.key) { type = it.navType }
    }
}

enum class NavArg(val key: String, val navType: NavType<*>) {
    ItemId(ITEM_ID, NavType.StringType)
}

private const val ITEM_ID = "itemId"
private const val SUB_ROUTE = "subRoute"
private const val DETAIL = "detail"
