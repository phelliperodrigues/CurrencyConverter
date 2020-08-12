package resources.schemas

import org.jetbrains.exposed.sql.Table

/**
 * Transaction Schema object
 */
object TransactionSchema: Table() {
    val id = integer("id").autoIncrement().primaryKey()
    val userId = varchar("userId", 50)
    val originCurrency = varchar("originCurrency",3)
    val originValue = float("originValue")
    val destinyCurrency = varchar("destinyCurrency",3)
    val conversionTax = float("conversionTax")
    val dateTime = datetime("dateTime")
}