package app.server;

public class Response {
    public final StatusCode statusCode;
    public final String responseData;

    public Response(StatusCode statusCode, String responseData) {
        this.statusCode = statusCode;
        this.responseData = responseData;
    }
    public static class ResponseBuilder {
        private StatusCode statusCode;
        private String responseData;

        public ResponseBuilder withStatusCode(StatusCode statusCode) {
            this.statusCode = statusCode;
            return this;
        }

        public ResponseBuilder withResponseData(String responseData) {
            this.responseData = responseData;
            return this;
        }

        public Response build() {
            return new Response(statusCode, responseData);
        }
    }
    public enum StatusCode{
        OK(200),
        CREATED(201),
        NO_CONTENT(204),

        BAD_REQUEST(400),
        UNAUTHORIZED(401),
        FORBIDDEN(403),
        NOT_FOUND(404),
        CONFLICT(409),
        UNPROCESSABLE_CONTENT(422),

        INTERNAL_SERVER_ERROR(500),
        SERVICE_UNAVAILABLE(503);

        private int statusCode;

        StatusCode(int statusCode){
            this.statusCode = statusCode;
        }

        public int getStatusCode() {
            return statusCode;
        }
    }


}
