package application.web.errors

import application.web.exceptions.InvalidTransaction
import io.javalin.http.Context
import org.eclipse.jetty.http.HttpStatus

object HandlerError {
    fun handlerErrorException(e: Exception, ctx: Context) {

        val ( httpError, httpStatus) = when(e){

            is InvalidTransaction -> {
                HttpError(e.type, e.message) to HttpStatus.BAD_REQUEST_400
            }

            else -> {
                HttpError("Unknown error","error not identified") to  HttpStatus.INTERNAL_SERVER_ERROR_500
            }
        }

        ctx.status(httpStatus)
        ctx.json(httpError)
    }
}