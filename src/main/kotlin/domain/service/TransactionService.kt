package domain.service

import domain.entities.Transaction
import domain.repository.Repository
import org.slf4j.LoggerFactory

/**
 * Transaction Service implementation
 */
class TransactionService(
    private val transactionRepository: Repository<Transaction>
) : Service<Transaction> {

    private val logger = LoggerFactory.getLogger(TransactionService::class.java)

    override fun save(entity: Transaction): Transaction = try {
        transactionRepository.save(entity).also {
            logger.info("Transaction save success")
        }
    } catch (ex: Exception) {
        logger.error(ex.toString())
        throw Exception("Not was possible save entity")
    }

    override fun findAll(): List<Transaction> {
        return transactionRepository.findAll()
    }

    override fun findAllByUserId(userId: String): List<Transaction> {
        return transactionRepository.findAllByUserId(userId)
    }

}