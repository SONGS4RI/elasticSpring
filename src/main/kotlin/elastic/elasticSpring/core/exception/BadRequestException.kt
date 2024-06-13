package elastic.elasticSpring.core.exception

class BadRequestException(errorCode: ErrorCode): RuntimeException(errorCode.message) {
    val statusCode: Int = 400
}