package dairo.aguas.melichallenge.extention

import java.text.NumberFormat
import java.util.Locale
import kotlin.math.roundToInt

fun Double.formatMoney(): String {
    val format: NumberFormat = NumberFormat.getCurrencyInstance(Locale("es", "CO"))
    format.maximumFractionDigits = 0

    return format.format(this.roundToInt())
}
