package domain

import domain.entities.Transaction
import domain.service.TransactionService
import org.junit.jupiter.api.Assertions.*
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import repository.TransactionRequestBuild
import resources.repositories.TransactionRepository

class TransactionServiceTest {

    private val transactionRepositoryMock = mockk<TransactionRepository>()
    private val transactionService = TransactionService(transactionRepositoryMock)
    private val transactionBuild = TransactionRequestBuild.build().toModel()

    @Test
    fun should_save_transaction() {
        every { transactionRepositoryMock.save(transactionBuild) } returns generateTransaction(transactionBuild)
        val transaction = transactionService.save(transactionBuild)
        assertNotNull(transaction)
        verify { transactionRepositoryMock.save(transactionBuild) }
    }

    @Test
    fun should_return_list_transactions() {
        every { transactionRepositoryMock.findAll() } returns listOf(transactionBuild)
        val transactions = transactionService.findAll()

        assertThat(transactions.size).isGreaterThan(0)
        verify { transactionRepositoryMock.findAll() }
    }

    @Test
    fun should_return_list_transactions_by_user_id() {
        every { transactionRepositoryMock.findAllByUserId(any()) } returns listOf(transactionBuild)
        val transactions = transactionService.findAllByUserId("user01")

        assertThat(transactions.size).isGreaterThan(0)
        verify { transactionRepositoryMock.findAllByUserId("user01") }
    }

    private fun generateTransaction(transaction: Transaction) = transaction.copy()

}