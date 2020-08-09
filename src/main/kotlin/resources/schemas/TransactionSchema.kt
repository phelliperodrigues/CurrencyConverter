package resources.schemas

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.jodatime.datetime

/**
 * Transaction Schema object
 */
object TransactionSchema: Table() {
    val id = integer("id").autoIncrement()
    val userId = integer("userId")
    val originCurrency = varchar("originCurrency",3)
    val originValue = float("originValue")
    val destinyCurrency = varchar("destinyCurrency",3)
    val conversionTax = float("conversionTax")
    val dateTime = datetime("dateTime")

    override val primaryKey = PrimaryKey(id)
}