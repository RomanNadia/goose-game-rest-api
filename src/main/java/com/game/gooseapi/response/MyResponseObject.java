package com.game.gooseapi.response;

public class MyResponseObject<T> {
    private T responseObject;
    private OperationStatus status;

    public MyResponseObject(T responseObject, OperationStatus status) {
        this.responseObject = responseObject;
        this.status = status;
    }

    public MyResponseObject(OperationStatus status) {
        this.status = status;
    }

    public T getResponseObject() {
        return responseObject;
    }

    public void setResponseObject(T responseObject) {
        this.responseObject = responseObject;
    }

    public OperationStatus getStatus() {
        return status;
    }

    public void setStatus(OperationStatus status) {
        this.status = status;
    }
}
