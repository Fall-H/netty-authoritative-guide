package org.nag.serial;

import lombok.Data;

import java.io.Serializable;

@Data
public class SubscribeResp implements Serializable {
    private static final long serialVersionUID = 1L;
    private int subReqID;
    private int respCode;
    private String desc;

    @Override
    public String toString() {
        return "SubscribeReq [subReqID=" + subReqID + ", respCode=" + respCode +
                ", desc=" + desc + "]";
    }
}
