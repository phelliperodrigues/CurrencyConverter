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
        Database.connect("jdbc:h2:mem:api", "org.h2.Driver")
        transaction {
            addLogger(StdOutSqlLogger)
            SchemaUtils.create(TransactionSchema)
        }

        val port: Int = System.getenv("PORT")?.toIntOrNull() ?: 7000

        val app = Javalin.create().apply {
            exception(Exception::class.java) { e, _ -> e.printStackTrace() }
            error(404) { ctx -> ctx.result("Not found") }
        }.start(port)

        app.routes {
            registerController.router()
        }

        app.exception(Exception::class.java, HandlerError::handlerErrorException)

        return app
    }
}