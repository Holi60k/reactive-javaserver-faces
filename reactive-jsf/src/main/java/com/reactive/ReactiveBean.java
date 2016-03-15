package com.reactive;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

@ManagedBean(name = "reactiveBean")
@RequestScoped
public class ReactiveBean implements Serializable {

    private Question question;
    private String answer;

    public void doReactive() {
        myObservable.subscribe(mySubscriber);
    }

    public ReactiveBean() {
        question = new Question();
    }

    public List<Question> getQuestionList() {
        List<Question> list = new ArrayList<>();
        Question q1 = new Question();
        q1.setId(1L);
        q1.setQuestion("What is the best development IDE?");
        list.add(q1);

        Question q2 = new Question();
        q2.setId(2L);
        q2.setQuestion("What is the best programming language?");
        list.add(q2);

        Question q3 = new Question();
        q3.setId(3L);
        q3.setQuestion("What is the best country in the World?");
        list.add(q3);
        return list;
    }

     Observable<String> myObservable = Observable.create(
            new Observable.OnSubscribe<String>() {
        @Override
        public void call(Subscriber<? super String> sub) {
            if (question.getId().equals(1L)) {
                sub.onNext("NetBeans");
                sub.onCompleted();
            }

            if (question.getId().equals(2L)) {
                sub.onNext("Java");
                sub.onCompleted();
            }

            if (question.getId().equals(3L)) {
                sub.onNext("Armenia");
                sub.onCompleted();
            }

        }
    }
    );

    Subscriber<String> mySubscriber = new Subscriber<String>() {
        @Override
        public void onNext(String s) {
            answer = s;
            System.out.println("Subscriber " + s);
        }

        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
        }
    };

    //////////////////////////////////////////////
    public static void hello(String... names) {
        Observable.from(names).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("Hello " + s + "!");
            }
        });
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

}
