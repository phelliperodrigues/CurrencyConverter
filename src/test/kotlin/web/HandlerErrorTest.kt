package web

import application.web.errors.HandlerError
import application.web.errors.HttpError
import application.web.exceptions.InvalidTransaction
import io.javalin.http.Context
import io.mockk.mockk
import io.mockk.verify
import org.eclipse.jetty.http.HttpStatus
import org.junit.jupiter.api.Test

class HandlerErrorTest {
    private var ctxMock = mockk<Context>(relaxed = true)

    @Test
    fun should_return_invalid_transaction() {
        val exception = InvalidTransaction(
            type = "Invalid Transaction",
            message = "Transaction not Processable"
        )
        HandlerError.handlerErrorException(exception, ctxMock)

        verify { ctxMock.status(HttpStatus.BAD_REQUEST_400) }
        verify { ctxMock.json(HttpError(exception.type,exception.message))}

    }

    @Test
    fun should_return_internal_server_error(){
        val exception = Exception("Unknown error")
        HandlerError.handlerErrorException(exception, ctxMock)

        verify { ctxMock.status(HttpStatus.INTERNAL_SERVER_ERROR_500) }
        verify { ctxMock.json(HttpError("Unknown error","error not identified"))}
    }
}