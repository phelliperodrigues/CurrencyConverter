package domain.repository

/**
 * Repository interface
 */
interface Repository<T> {

    fun save(entity : T): T
    fun findAll(): List<T>
}