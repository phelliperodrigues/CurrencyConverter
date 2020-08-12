package resources.repositories

import domain.repository.Repository
import domain.entities.Transaction
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.slf4j.LoggerFactory
import resources.extensions.toTransaction
import resources.schemas.TransactionSchema

/**
 * Transaction Repository implementation
 */
class TransactionRepository : Repository<Transaction> {

    private val logger = LoggerFactory.getLogger(TransactionRepository::class.java)

    override fun save(entity: Transaction): Transaction = transaction {
        val result = TransactionSchema.insert {
            it[userId] = entity.userId
            it[originCurrency] = entity.originCurrency
            it[originValue] = entity.originValue
            it[destinyCurrency] = entity.destinyCurrency
            it[conversionTax] = entity.conversionTax
            it[dateTime] = entity.dateTime

        }
        logger.info("Save entity with success")
        entity.copy(id = result[TransactionSchema.id])

    }

    override fun findAll(): List<Transaction> = transaction {
        TransactionSchema.selectAll().map {
            it.toTransaction()
        }.toList()

    }

    override fun findAllByUserId(userId: String): List<Transaction> = transaction {
        TransactionSchema.select{ TransactionSchema.userId eq userId }.map {
            it.toTransaction()
        }.toList()
    }
}