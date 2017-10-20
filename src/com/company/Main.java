package com.company;

import jdk.nashorn.internal.parser.JSONParser;
import sun.security.ssl.SSLSocketImpl;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.net.Socket;
import java.net.URLEncoder;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String phrase = reader.readLine();
        System.out.println(phrase);
        System.out.println(URLEncoder.encode(phrase, "UTF-8"));
        String data = "text=" + phrase + "\r\n";

        Socket socket = SSLSocketFactory.getDefault().createSocket("translate.yandex.net", 443);

        System.out.println("socket ok");

        String path = "/api/v1.5/tr.json/translate";
        String key = "trnsl.1.1.20171020T140902Z.10f6991ce44764b9.23e848d74e20f95c0d5fce8c6dcb20ebf947a303";
        String lang = "en-ru";

        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("POST " + path + "?" + "lang=" + lang + "&" + "key=" + key + " HTTP/1.1\r\n");
        stringBuilder.append("Host: translate.yandex.net" + "\r\n");
        stringBuilder.append("Accept: */*" + "\r\n");
        stringBuilder.append("Content-Length: " + data.length() + "\r\n");
        stringBuilder.append("Content-Type: application/x-www-form-urlencoded\r\n");
        stringBuilder.append("\r\n");
        stringBuilder.append(data);


        System.out.println(stringBuilder);
        wr.write(stringBuilder.toString());
        wr.flush();
        System.out.println("flush ok");

        BufferedReader rd = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String line;
        while (rd != null) {
           // System.out.println(line.toString());
            line = rd.readLine();
            System.out.println(line.toString());
        }
        wr.close();
        rd.close();
    }
}
