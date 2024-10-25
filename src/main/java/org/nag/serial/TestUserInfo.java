package org.nag.serial;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class TestUserInfo {
    public static void main(String[] args) throws IOException {
        UserInfo userInfo = new UserInfo();
        userInfo.buildUserID(100).buildUserName("Welcome to Netty");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(bos);
        os.writeObject(userInfo);
        os.flush();
        os.close();
        byte[] bytes = bos.toByteArray();
        System.out.println("The jdk serializable length is : " + bytes.length);
        bos.close();
        System.out.println("--------------------------------");
        System.out.println("The byte array serializable length is : " + userInfo.codec().length);
    }
}
