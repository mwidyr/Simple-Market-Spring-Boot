package com.example.simplemarket.util;

public class CommonResponse<T> {
    private Boolean status;
    private String message;
    private T object;

    public CommonResponse() {
    }

    public CommonResponse(Boolean status, String message, T object) {
        this.status = status;
        this.message = message;
        this.object = object;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CommonResponse{");
        sb.append("status=").append(status);
        sb.append(", message='").append(message).append('\'');
        sb.append(", object=").append(object);
        sb.append('}');
        return sb.toString();
    }
}
