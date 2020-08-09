package application

import application.web.Init
import application.web.config.modulesAll
import io.javalin.Javalin
import org.koin.core.context.startKoin

object Main {

    @JvmStatic
    fun main(args: Array<String>) {
        startApplication()

    }

    private fun startApplication(): Javalin{
        startKoin {
            modules(modulesAll)
        }

        return Init.start()
    }
}
