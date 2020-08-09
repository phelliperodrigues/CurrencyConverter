package application.web.exceptions

class InvalidTransaction (
    val type: String,
    override val message: String
) : Exception(message)