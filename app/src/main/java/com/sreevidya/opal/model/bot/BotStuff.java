package com.sreevidya.opal.model.bot;


import android.os.AsyncTask;

import com.michaelwflaherty.cleverbotapi.CleverBotQuery;

import java.io.IOException;


public class BotStuff {

    private static final String API_KEY = "CC51wq65n3d09W6vnaxiL9jFCIw";

    public static void getResponse(String input, final BotCallback<String> callback) {
        final CleverBotQuery bot = new CleverBotQuery(API_KEY, input);

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                String response;
                try {
                    bot.sendRequest();
                    response = bot.getResponse();
                    callback.onSuccess(response);
                } catch (IOException e) {
                    response = e.getMessage();
                    callback.onFailure(response);
                }
            }
        });


    }

    public interface BotCallback<C> {
        void onSuccess(C c);

        void onFailure(C e);
    }


}
