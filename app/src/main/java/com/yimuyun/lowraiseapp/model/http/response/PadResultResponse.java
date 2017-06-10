package com.yimuyun.lowraiseapp.model.http.response;

/**
 * Api返回的统一格式
 * Created by pengweiqiang on 17/05/03.
 */

public class PadResultResponse<T> {

    private Head head;
    private T body;


    public Head getHead() {
        return head;
    }

    public void setHead(Head head) {
        this.head = head;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
