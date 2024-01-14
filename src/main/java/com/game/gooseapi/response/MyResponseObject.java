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

//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) return true;
//        if (obj == null || getClass() != obj.getClass()) return false;
//        if(this.responseObject.equals(((MyResponseObject<?>) obj).responseObject)
//                && this.status.equals(((MyResponseObject<?>) obj).status)) {
//            return true;
//        } else {
//            return false;
//        }
//    }


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
