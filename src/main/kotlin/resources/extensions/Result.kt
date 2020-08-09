package resources.extensions

import domain.entities.Transaction
import org.jetbrains.exposed.sql.ResultRow
import resources.schemas.TransactionSchema

fun ResultRow.toTransaction(): Transaction = Transaction(
    id = get(TransactionSchema.id),
    userId = get(TransactionSchema.userId),
    originCurrency = get(TransactionSchema.originCurrency),
    originValue = get(TransactionSchema.originValue),
    destinyCurrency = get(TransactionSchema.destinyCurrency),
    conversionTax = get(TransactionSchema.conversionTax),
    dateTime = get(TransactionSchema.dateTime)
)