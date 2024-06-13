package elastic.elasticSpring.core.exception

enum class ErrorCode(
    val statusCode: Int,
    val message: String,
) {
    BAD_REQUEST(400, "Bad Request"),
    INVALID_PARAMETER(400, "Invalid Parameter"),
    NOT_FOUND(404, "Not found"),
    INTERNAL_SERVER_ERROR(500, "Internal server error")
}