package application.web.entities

import domain.entities.Transaction
import org.joda.time.format.DateTimeFormat

class TransactionResponse(
    val id: Int,
    val userId: String,
    val originCurrency: String,
    val originValue: Float,
    val destinyCurrency: String,
    val destinyValue: Float,
    val conversionTax: Float,
    val dateTime: String
) {
    companion object {
        private val formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")

        fun toResponse(transaction: Transaction): TransactionResponse = TransactionResponse(
            id = transaction.id,
            userId = transaction.userId,
            originCurrency = transaction.originCurrency,
            originValue = transaction.originValue,
            destinyCurrency = transaction.destinyCurrency,
            destinyValue = transaction.originValue * transaction.conversionTax,
            conversionTax = transaction.conversionTax,
            dateTime = transaction.dateTime.toString(formatter)
        )
    }

}