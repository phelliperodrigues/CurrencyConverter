package repository

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import resources.schemas.TransactionSchema

open class RepositoryBase {

    @BeforeEach
    fun setup() {
        Database.connect("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1", "org.h2.Driver")

        transaction {
            SchemaUtils.create(TransactionSchema)
        }
    }

    @AfterEach
    fun tearDown(){
        transaction {
            SchemaUtils.drop(TransactionSchema)
        }
    }
}