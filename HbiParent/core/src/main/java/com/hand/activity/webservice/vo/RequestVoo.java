package com.hand.activity.webservice.vo;

import java.io.Serializable;

public class RequestVoo implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Long activity_id;

    public Long getActivity_id() {
        return activity_id;
    }

    public void setActivity_id(Long activity_id) {
        this.activity_id = activity_id;
    }
}
