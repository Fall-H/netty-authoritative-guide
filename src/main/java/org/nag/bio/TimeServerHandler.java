package org.nag.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

public class TimeServerHandler implements Runnable {
    private Socket socket;

    public TimeServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(this.socket.getOutputStream(), true);
            String currentTime = null;
            String body = null;

            while (true) {
                body = in.readLine();
                if (body == null) {
                    break;
                }
                System.out.println("Server received: " + body);
                currentTime = "Query Time Order".equalsIgnoreCase(body) ? new Date(System.currentTimeMillis()).toString() : "Bad order";
                out.println(currentTime);
            }
        } catch (Exception e) {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

            if (out != null) {
                out.close();
                out = null;
            }

            if (socket != null) {
                try {
                    this.socket.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                this.socket = null;
            }
        }
    }
}
