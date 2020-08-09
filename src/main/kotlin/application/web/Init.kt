package application.web

import application.web.controllers.TransactionController
import application.web.errors.HandlerError
import io.javalin.Javalin
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction
import org.koin.core.KoinComponent
import org.koin.core.inject
import resources.schemas.TransactionSchema

object Init: KoinComponent {
    private val registerController: TransactionController by inject()

    fun start(): Javalin {
        Database.connect("jdbc:h2:mem:regular;DB_CLOSE_DELAY=-1", "org.h2.Driver")
        transaction {
            addLogger(StdOutSqlLogger)
            SchemaUtils.create(TransactionSchema)
        }

        val app = Javalin.create().start(7000)

        app.routes {
            registerController.router()
        }

        app.exception(Exception::class.java, HandlerError::handlerErrorException)

        return app
    }
}