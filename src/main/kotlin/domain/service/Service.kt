package domain.service

interface Service<T> {

    fun save(entity: T): T
    fun findAll(): List<T>

}