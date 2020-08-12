package application.web.controllers

import application.web.entities.TransactionRequest
import application.web.entities.TransactionResponse
import application.web.exceptions.InvalidTransaction
import domain.entities.Transaction
import domain.service.Service
import io.javalin.apibuilder.ApiBuilder.*
import io.javalin.http.BadRequestResponse
import io.javalin.http.Context
import org.eclipse.jetty.http.HttpStatus
import org.slf4j.LoggerFactory

class TransactionController(
    private val transactionService: Service<Transaction>
) {

    private val logger = LoggerFactory.getLogger(TransactionController::class.java)

    fun router() {
        path("/transaction") {
            post { ctx -> ctx.json(registerTransaction(ctx)) }
            get { ctx -> ctx.json(listTransactions(ctx)) }

        }

        path("/transaction/:userId") {
            get { ctx -> ctx.json(listTransactionsByUserId(ctx)) }
        }

    }

    fun registerTransaction(ctx: Context): TransactionResponse = try {
        ctx.bodyAsClass(TransactionRequest::class.java).let {
            logger.info("Save transaction with id ${it.id}")
            ctx.status(HttpStatus.CREATED_201)
            TransactionResponse.toResponse(transactionService.save(it.toModel()))
        }
    } catch (ex: BadRequestResponse) {
        logger.error(ex.toString())
        throw InvalidTransaction(
            type = "Transaction invalid",
            message = ex.message.toString()
        )
    }

    fun listTransactions(ctx: Context): List<TransactionResponse> {
        logger.info("Find all Transactions")
        return transactionService.findAll().map { TransactionResponse.toResponse(it) }.also {
            ctx.status(HttpStatus.OK_200)
        }
    }

    fun listTransactionsByUserId(ctx: Context): List<TransactionResponse> {
        val userId = ctx.pathParam("userId")
        logger.info("Find all transactions from user $userId")
        return transactionService.findAllByUserId(userId).map { TransactionResponse.toResponse(it) }
            .also {
                ctx.status(HttpStatus.OK_200)
            }

    }

}