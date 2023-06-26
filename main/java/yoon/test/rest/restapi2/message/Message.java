package yoon.test.rest.restapi2.message;

import lombok.Data;

@Data
public class Message {

    private StatusEnum statusEnum;
    private String message;
    private Object data;

    public Message(){
        this.statusEnum = StatusEnum.BAD_REQUEST;
        this.data = null;
        this.message = null;
    }

}
