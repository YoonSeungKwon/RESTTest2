package yoon.test.rest.restapi2.message;

public enum StatusEnum {

    OK(200,"OK"),
    BAD_REQUEST(400,"BAD_REQUEST"),
    NOT_FOUND(404,"NOT_FOUND"),
    INTERNAL_SERVER_ERROR(500,"INTERNAL_SERVER_ERROR");

    int StatusCode;
    String code;

    StatusEnum(int statusCode, String code){

        this.StatusCode=statusCode;

        this.code=code;

    }



}
