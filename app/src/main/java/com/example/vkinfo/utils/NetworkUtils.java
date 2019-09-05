package com.example.vkinfo.utils;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Scanner;

public class NetworkUtils {

    private static final String VK_API_BASE_URL = "https://api.vk.com/";
    private static final String VK_METHOD_USERS_GET = "method/users.get";
    private static final String VK_ACCESS_TOKEN_URL = "access_token";
    private static final String VK_API_TOKEN = "";
    private static final String PARAM_USER_ID = "user_ids";
    private static final String PARAM_VERSION_URL = "v";
    private static final String PARAM_VERSION = "5.8";

    private static final String FIELDS = "fields";
    private static final String VK_GET_PROFILE_PIC_100 = "photo_100";

    /* генерирует URL на основании id который пришел от пользователя */
    public static URL generateURL(String userIds) throws MalformedURLException {
        Uri builtUri = Uri.parse(VK_API_BASE_URL + VK_METHOD_USERS_GET)
                .buildUpon() //надстройка для добавления параметров к URI

                //добавление параметра в надстройку
                .appendQueryParameter(PARAM_USER_ID, userIds)
                .appendQueryParameter(FIELDS, VK_GET_PROFILE_PIC_100)
                .appendQueryParameter(PARAM_VERSION_URL, PARAM_VERSION)
                .appendQueryParameter(VK_ACCESS_TOKEN_URL, VK_API_TOKEN)
                .build();

        URL url = new URL(builtUri.toString());
        return url;
    }

    public static String getResponseFromUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        try {
            InputStream in = urlConnection.getInputStream();

            Scanner sc = new Scanner(in);
            sc.useDelimiter("\\A"); //позволяет выставить кастомный разделитель чтобы получить всю строку без подстрок

            //проверяет есть ли вообще входные данные
            boolean hasInput = sc.hasNext();
            if (hasInput) {
                return sc.next();
            } else {
                return null;
            }
        } catch (UnknownHostException e) {
            return null;
        } finally {
            urlConnection.disconnect();
        }
    }
}
