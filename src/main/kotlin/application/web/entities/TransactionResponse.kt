package application.web.entities

import domain.entities.Transaction
import org.joda.time.DateTime

class TransactionResponse(
    val id: Int,
    val userId: Int,
    val originCurrency: String,
    val originValue: Float,
    val destinyCurrency: String,
    val destinyValue: Float,
    val conversionTax: Float,
    val dateTime: DateTime
) {
    companion object {
        fun toResponse(transaction: Transaction): TransactionResponse = TransactionResponse(
            id = transaction.id,
            userId = transaction.userId,
            originCurrency = transaction.originCurrency,
            originValue = transaction.originValue,
            destinyCurrency = transaction.destinyCurrency,
            destinyValue = transaction.originValue * transaction.conversionTax,
            conversionTax = transaction.conversionTax,
            dateTime = transaction.dateTime
        )
    }

}