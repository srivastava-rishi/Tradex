package com.rsstudio.tradex.util

import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Locale

fun String.isPositiveAmount(): Boolean {
    val sanitized = this.replace("[^\\d.-]".toRegex(), "")
    return sanitized.toDoubleOrNull()?.let { it > 0 } ?: false
}


fun Double.formatAsCurrency(locale: Locale = Locale("en", "IN")): String {
    val decimalFormat = NumberFormat.getCurrencyInstance(locale) as DecimalFormat
    decimalFormat.minimumFractionDigits = 2
    return decimalFormat.format(this)
}