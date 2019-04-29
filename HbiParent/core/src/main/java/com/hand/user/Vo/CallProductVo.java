package com.hand.user.Vo;

import java.io.Serializable;

public class CallProductVo  implements Serializable {
    private static final long serialVersionUID = 1L;


    private String result;
    private String code;//S000A000
    private String message;
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getResult() {
        return result;
    }
    public void setResult(String result) {
        this.result = result;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }


}
