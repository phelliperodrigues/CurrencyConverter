package repository

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import resources.repositories.TransactionRepository

class TransactionRepositoryTest : RepositoryBase() {

    private val transactionBuild = TransactionRequestBuild.build().toModel()

    private val transactionRepository = TransactionRepository()

    @Test
    fun should_persist_transaction(){
        val transaction = transactionRepository.save(transactionBuild)
        assertNotNull(transaction)
    }
}