public enum StatusResponse {
    OK (200),
    BAD_REQUEST (400),
    INTERNAL_SERVER_ERROR (500);

    private int status;

    StatusResponse(final int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
