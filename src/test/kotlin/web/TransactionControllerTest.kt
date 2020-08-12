package web

import application.web.controllers.TransactionController
import application.web.entities.TransactionRequest
import application.web.exceptions.InvalidTransaction
import domain.entities.Transaction
import domain.service.Service
import io.javalin.http.BadRequestResponse
import io.javalin.http.Context
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.eclipse.jetty.http.HttpStatus
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions
import repository.TransactionRequestBuild

class TransactionControllerTest {

    private var ctxMock = mockk<Context>(relaxed = true)
    private val transactionServiceMock = mockk<Service<Transaction>>()
    private val transactionRequest = TransactionRequestBuild.build()
    private val transactionController = TransactionController(transactionServiceMock)

    @Test
    fun should_return_list_transactions() {
        every { transactionServiceMock.findAll() } returns listOf(generateTransaction(transactionRequest))

        val registerTransactions = transactionController.listTransactions(ctxMock)

        assertThat(1).isEqualTo(registerTransactions.size)
        verify { ctxMock.status(HttpStatus.OK_200) }
        verify { transactionServiceMock.findAll() }

    }

    @Test
    fun should_return_InvalidTransaction() {
        every { ctxMock.bodyAsClass(TransactionRequest::class.java) } throws BadRequestResponse("Couldn't deserialize body")

        Assertions.assertThrows(InvalidTransaction::class.java) {
            transactionController.registerTransaction(ctxMock)
        }.let {
            assertThat(it.type).isEqualTo("Transaction invalid")
        }
    }

    private fun generateTransaction(transactionRequest: TransactionRequest) = transactionRequest.toModel().copy()

}