package org.nag.serial;

import lombok.Data;

import java.io.Serializable;

@Data
public class SubscribeReq implements Serializable {
    private static final long serialVersionUID = 1L;
    private int subReqID;
    private String userName;
    private String productName;
    private String phoneNumber;
    private String address;

    @Override
    public String toString() {
        return "SubscribeReq [subReqID=" + subReqID + ", userName=" + userName +
                ", productName=" + productName + ", phoneNumber=" + phoneNumber +
                ", address=" + address + "]";
    }
}
