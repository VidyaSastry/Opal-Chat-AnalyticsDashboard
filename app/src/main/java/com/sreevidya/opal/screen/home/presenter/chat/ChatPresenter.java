package com.sreevidya.opal.screen.home.presenter.chat;


import android.os.Handler;

import com.sreevidya.opal.model.auth.AuthInjector;
import com.sreevidya.opal.model.auth.AuthSource;
import com.sreevidya.opal.model.auth.User;
import com.sreevidya.opal.model.bot.BotStuff;
import com.sreevidya.opal.model.database.DatabaseInjector;
import com.sreevidya.opal.model.database.DatabaseSource;
import com.sreevidya.opal.model.database.Message;

import java.util.List;

public class ChatPresenter implements ChatContract.Presenter {

    private static Handler handler = new Handler();
    private AuthSource authSource;
    private DatabaseSource databaseSource;
    private ChatContract.View view;
    private User user;

    public ChatPresenter(ChatContract.View view) {
        this.view = view;
        this.databaseSource = DatabaseInjector.getInstance();
        this.authSource = AuthInjector.getInstance();
        view.setPresenter(this);
    }

    @Override
    public void onSendClick() {
        final String messageText = view.getMessageText();
        view.clearField();
        // send req to clbot
        // add res to db
        addMessage(messageText, true);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                BotStuff.getResponse(messageText,
                        new BotStuff.BotCallback<String>() {
                            @Override
                            public void onSuccess(String s) {
                                addMessage(s, false);
                            }

                            @Override
                            public void onFailure(String e) {
                                view.makeToast(e);
                            }
                        });
            }
        }, 1000);

    }

    private void addMessage(final String messageText, final boolean isUser) {

        Message message = new Message(messageText, isUser);
        databaseSource.createMessage(
                user.getUid(),
                message,
                new DatabaseSource.DatabaseCallback<Message>() {
                    @Override
                    public void onSuccess(Message m) {
                        view.makeToast("Added to db");
                        view.addMessage(m);
                    }

                    @Override
                    public void onFailure(Exception e) {
                        view.makeToast(e.getMessage());
                    }
                });

    }

    @Override
    public void subscribe(ChatContract.View view) {
        this.view = view;
        authSource.getUser(new AuthSource.AuthCallback<User>() {
            @Override
            public void onSuccess(User user) {
                ChatPresenter.this.user = user;
                getPreviousMessages();
            }

            @Override
            public void onFailure(Exception e) {
                ChatPresenter.this.view.makeToast("Failed to get user");
            }
        });
    }

    private void getPreviousMessages() {
        databaseSource.getMessages(user,
                new DatabaseSource.DatabaseCallback<List<Message>>() {
                    @Override
                    public void onSuccess(List<Message> messages) {
                        view.setMessages(messages);
                    }

                    @Override
                    public void onFailure(Exception e) {
                        view.makeToast("Failed to get previous msgs");
                    }
                });
    }

    @Override
    public void unsubscribe() {
        this.view = null;
    }

    @Override
    public boolean isViewVisible() {
        return view != null;
    }


}
