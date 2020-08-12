package application.web.entities

import domain.entities.Transaction
import org.joda.time.DateTime

class TransactionRequest(
    val id: Int,
    val userId: String,
    private val originCurrency: String,
    private val originValue: Float,
    private val destinyCurrency: String
) {
    /**
     * I don't like this here, but for problems with time I kept it here for now.
     */
    private fun getConversionTax(originCurrency: String, destinyCurrency: String): Float {
        val baseUrl = "https://api.exchangeratesapi.io/latest?base="
        val url: String = baseUrl + originCurrency
        return khttp.get(url).jsonObject.getJSONObject("rates").getDouble(destinyCurrency).toFloat()
    }

    fun toModel(): Transaction = Transaction(
        id = this.id,
        userId = this.userId,
        originCurrency = this.originCurrency,
        originValue = this.originValue,
        destinyCurrency = this.destinyCurrency,
        conversionTax = this.getConversionTax(this.originCurrency, this.destinyCurrency),
        dateTime = DateTime.now()
    )
}