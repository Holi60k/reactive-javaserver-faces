package com.reactive;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import rx.Observable;
import rx.Subscriber;

@ManagedBean(name = "reactiveChat")
@ViewScoped
public class ReactiveChat implements Serializable {

    private ChatUser chatUser;
    private String answer = null;
    private boolean renderChat = false;
    private List<ChatUser> answersList;

    public void doReactive() {
        System.out.println("doReactive called ");
        myObservable.subscribe(mySubscriber);
        renderChat = true;
    }

    public void openChat() {
        renderChat = true;
    }

    public ReactiveChat() {
        answersList = new ArrayList<>();
        ChatUser q1 = new ChatUser();
        q1.setUsername("Alen Doll");
        q1.setMessage("Hey who is the best player Ronaldo or Messi?");
        answersList.add(q1);
        chatUser = new ChatUser();
    }

    public void doAction() {

        System.out.println("Answer passed " + answer);
        myObservable.subscribe(mySubscriber);
    }

    public List<ChatUser> getAnswersList() {

        return answersList;
    }

    Observable<String> myObservable = Observable.create(
            new Observable.OnSubscribe<String>() {
        @Override
        public void call(Subscriber<? super String> sub) {
            System.out.println("Answer " + answer);
            if (answer != null) {
                sub.onNext("ans");
                sub.onCompleted();
            }

        }
    }
    );

    Subscriber<String> mySubscriber = new Subscriber<String>() {
        @Override
        public void onNext(String s) {

            if (s.equalsIgnoreCase("ans")) {
                ChatUser q1 = new ChatUser();
                q1.setUsername("RealMadrid Fun");
                q1.setMessage(answer);
                getAnswersList().add(q1);
                ChatUser a = new ChatUser();
                a.setUsername("Alen Doll");
                a.setMessage("Are you Kidding?");
                getAnswersList().add(a);

            }
            System.out.println("Subscriber " + s);
answer = null;
        }

        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
        }
    };

    public boolean isRenderChat() {
        return renderChat;
    }

    public void setRenderChat(boolean renderChat) {
        this.renderChat = renderChat;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public ChatUser getChatUser() {
        return chatUser;
    }

    public void setChatUser(ChatUser chatUser) {
        this.chatUser = chatUser;
    }

}
