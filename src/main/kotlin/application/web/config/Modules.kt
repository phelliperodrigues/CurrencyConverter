package application.web.config

import application.web.controllers.TransactionController
import domain.entities.Transaction
import domain.repository.Repository
import domain.service.Service
import domain.service.TransactionService
import org.koin.core.qualifier.named
import org.koin.dsl.module
import resources.repositories.TransactionRepository

val modulesAll = module {

    single<Repository<Transaction>>(named("transaction")) { TransactionRepository() }

    single<Service<Transaction>> { TransactionService(get(named("transaction"))) }

    single { TransactionController(get()) }

}