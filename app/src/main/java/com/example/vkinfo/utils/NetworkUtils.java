package com.example.vkinfo.utils;

import android.net.Uri;


import java.net.MalformedURLException;
import java.net.URL;

public class NetworkUtils {

    private static final String VK_API_BASE_URL = "https://api.vk.com/";
    private static final String VK_USERS_GET = "method/users.get";
    private static final String VK_API_TOKEN = "";
    private static final String PARAM_USER_ID = "user_ids";
    private static final String PARAM_VERSION = "v";

    /* генерирует URL на основании id который пришел от пользователя */
    public static URL generateURL(String userId) throws MalformedURLException {
        Uri builtUri = Uri.parse(VK_API_BASE_URL + VK_USERS_GET) //TODO: add VK_API_TOKEN constant to concatenate string.
                .buildUpon() //надстройка для добавления параметров к URI
                //добавление параметра в надстройку
                .appendQueryParameter(PARAM_USER_ID, userId)
                .appendQueryParameter(PARAM_VERSION, "5.8")
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }
}
