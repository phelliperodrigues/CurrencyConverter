package repository

import application.web.entities.TransactionRequest

class TransactionRequestBuild {

    private val id: Int = 1
    private val userId: String = "user01"
    private val originCurrency: String = "BRL"
    private val originValue: Float = 2.50F
    private val destinyCurrency: String = "USD"
    private val conversionTax: Float = 3.2F

    companion object {
        fun build(transaction: TransactionRequestBuild = TransactionRequestBuild()): TransactionRequest =
            TransactionRequest(
                id = transaction.id,
                userId = transaction.userId,
                originCurrency = transaction.originCurrency,
                originValue = transaction.originValue,
                destinyCurrency = transaction.destinyCurrency,
                conversionTax = transaction.conversionTax
            )
    }
}