package logger

/**
 * Interface for logging.
 */
interface ILogger {
    /**
     * Accesses the iterable log.
     */
    fun getLog(): List<String>

    /**
     * Logs a message.
     */
    fun log(message: String)
}
