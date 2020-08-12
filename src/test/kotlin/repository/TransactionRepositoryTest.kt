package repository

import org.assertj.core.api.Assertions.assertThat
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

    @Test
    fun should_return_transactions_by_user_id(){
        var transactions = transactionRepository.findAllByUserId("user01")
        assertThat(0).isEqualTo(transactions.size)

        transactionRepository.save(transactionBuild)
        transactions = transactionRepository.findAllByUserId("user01")
        assertThat(1).isEqualTo(transactions.size)
    }
}