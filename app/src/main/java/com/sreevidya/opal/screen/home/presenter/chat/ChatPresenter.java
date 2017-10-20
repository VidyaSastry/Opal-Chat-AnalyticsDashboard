package com.sreevidya.opal.screen.home.presenter.chat;


import com.sreevidya.opal.model.auth.AuthInjector;
import com.sreevidya.opal.model.auth.AuthSource;
import com.sreevidya.opal.model.database.DatabaseInjector;
import com.sreevidya.opal.model.database.DatabaseSource;
import com.sreevidya.opal.model.database.Message;

public class ChatPresenter implements ChatContract.Presenter {

    private AuthSource authSource;
    private DatabaseSource databaseSource;

    private ChatContract.View view;

    public ChatPresenter(ChatContract.View view) {
        this.view = view;
        this.databaseSource = DatabaseInjector.getInstance();
        this.authSource = AuthInjector.getInstance();
        view.setPresenter(this);

    }


    @Override
    public void onSendClick() {
        String messageText = view.getMessageText();
//        FirebaseUser user = authSource.getUser();
        Message message = new Message("Vidya", messageText, null);
        databaseSource.createMessage(message, new DatabaseSource.DatabaseCallback<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                view.makeToast("Added to db");
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
