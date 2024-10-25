package org.nag.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;

import java.util.ArrayList;
import java.util.List;

public class TestSubscribeReqProto {
    private static byte[] encode(SubscribeReqProto req) {
        return req.toByteArray();
    }

    private static SubscribeReqProto decode(byte[] body) throws InvalidProtocolBufferException {
        return SubscribeReqProto.parseFrom(body);
    }

    private static SubscribeReqProto createSubscribeReq() {
        SubscribeReqProto.Builder builder = SubscribeReqProto.newBuilder();
        builder.setSubReqID(1);
        builder.setUserName("Lilinfeng");
        builder.setProductName("Netty Book");
        List<String> address = new ArrayList<String>();
        address.add("NanJing YuHuaTai");
        address.add("BeiJing LiuLiChang");
        address.add("ShenZhen HongShuLin");
        return builder.build();
    }

    public static void main(String[] args) throws InvalidProtocolBufferException {
        SubscribeReqProto req = createSubscribeReq();
        System.out.println("Before encode: " + req.toString());
        SubscribeReqProto decode = decode(encode(req));
        System.out.println("After encode: " + decode.toString());
        System.out.println("Assert equal : " + req.equals(decode));
    }
}
