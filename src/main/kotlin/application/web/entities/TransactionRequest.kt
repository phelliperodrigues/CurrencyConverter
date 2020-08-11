package application.web.entities

import domain.entities.Transaction
import org.joda.time.DateTime

class TransactionRequest(
    val id: Int,
    private val userId: String,
    private val originCurrency: String,
    private val originValue: Float,
    private val destinyCurrency: String,
    private val conversionTax: Float
) {
    fun toModel(): Transaction = Transaction(
        id = this.id,
        userId = this.userId,
        originCurrency = this.originCurrency,
        originValue = this.originValue,
        destinyCurrency = this.destinyCurrency,
        conversionTax = this.conversionTax,
        dateTime = DateTime.now()
    )
}