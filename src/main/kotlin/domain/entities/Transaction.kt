package domain.entities

import org.joda.time.DateTime

/**
 * Transaction entity class
 */
data class Transaction (
    val id: Int,
    val userId: Int,
    val originCurrency: String,
    val originValue: Float,
    val destinyCurrency: String,
    val conversionTax: Float,
    val dateTime: DateTime
)