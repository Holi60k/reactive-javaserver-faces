package com.reactive;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import rx.Observable;
import rx.Subscriber;

@ManagedBean(name = "reactiveChat")
@ViewScoped
public class ReactiveChat implements Serializable {

    private static final long serialVersionUID = 1L;

    private ChatUser chatUser;
    private String answer = null;
    private boolean renderChat = false;
    private final List<ChatUser> answersList;
    private List<String> messagesList = new ArrayList<>();
   // private String link = "<a href=\"https://www.youtube.com/watch?v=vCwwba9SgIE\">Click</a>";
//
    //String withURL = link.replaceAll("(?:https?|ftps?)://[\\w/%.-]+", "<a href='$0'>$0</a>");

    public void doReactive() {
        System.out.println("doReactive called ");
        myObservable.subscribe(mySubscriber);
        renderChat = true;
    }

    public void openChat() {
        renderChat = true;
    }

    public ReactiveChat() {
        messagesList.add(0, "Do you like only Real Madrid? ");
        messagesList.add(0, "I think you are far from football as Armenia from China! ");
        messagesList.add(0, "I hope so");
        messagesList.add(0, "Shut down your PC!");
        messagesList.add(0, "Just eat your apple!");
        messagesList.add(0, "Restart your computer");
        messagesList.add(0, "Mamma mia!!!  ");
        messagesList.add(0, "Your iphone is broken?");
        messagesList.add(0, "REAL CHAMPION AGAIN!!!");
        messagesList.add(0, "Hey Looser, learn English!!!!!");
        messagesList.add(0, "Reset or shutdown your mind!!!!!!");
        messagesList.add(0, "Did you have good flight!");
        messagesList.add(0, "Ronaldo the Best!  ");
        messagesList.add(0, "JSF kill any JavaScript!  ");
        messagesList.add(0, "Yerevan is one of the oldest citiest!  ");
        messagesList.add(0, "What did you mean? ");
        messagesList.add(0, "Have you had eny bad?");
        messagesList.add(0, "Not much , but more, I think  ");
        messagesList.add(0, "Are you sure?");
        messagesList.add(0, "What do think about Game Over?");
        messagesList.add(0, "Good morning Papa");
        messagesList.add(0, "Do Re Me?");
        messagesList.add(0, "Hey, who is the best player: Ronaldo or Messi?????");
        answersList = new ArrayList<>();
        ChatUser q1 = new ChatUser();
        q1.setUsername("John Smith");
        q1.setMessage("Welcome to my personal chat! I hope you are like Real Madrid!!!");
        answersList.add(0, q1);
        chatUser = new ChatUser();
    }

    public void doAction() {
        //System.out.println("Answer passed " + answer);
        myObservable.subscribe(mySubscriber);
    }

    public List<ChatUser> getAnswersList() {
        //  Collections.reverse(answersList);
        return answersList;
    }

    @SuppressWarnings("deprecation")
    Observable<String> myObservable = Observable.create(
            new Observable.OnSubscribe<String>() {
        @Override
        public void call(Subscriber<? super String> sub) {
            //System.out.println("Answer " + answer);
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
                q1.setUsername("Guest");
                q1.setMessage(answer);
                getAnswersList().add(0, q1);
                ChatUser a = new ChatUser();
                a.setUsername("John Smith");
                Random r = new Random();
                a.setMessage(messagesList.get(r.nextInt(messagesList.size())));
                getAnswersList().add(0, a);
            }
            //System.out.println("Subscriber " + s);
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
