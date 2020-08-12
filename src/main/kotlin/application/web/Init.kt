package application.web

import application.web.controllers.TransactionController
import application.web.errors.HandlerError
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
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
        Database.connect(hikari())
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

    private fun hikari(): HikariDataSource {
        val config = HikariConfig()
        config.driverClassName = "org.h2.Driver"
        config.jdbcUrl = "jdbc:h2:mem:app"
        config.maximumPoolSize = 3
        config.isAutoCommit = false
        config.transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        config.validate()
        return HikariDataSource(config)
    }
}