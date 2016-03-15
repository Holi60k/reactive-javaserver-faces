package learnrxjava.examples;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

/**
 *
 * @author armen arzumanyan
 */
public class FirstExample {

    private Observable<String> myObservable;
    private Subscriber<String> mySubscriber;

    public static void main(String args[]) {
        FirstExample example = new FirstExample();

        example.observer().subscribe(example.subscriber());

        example.observer1().subscribe(example.action());
        // myObservable.subscribe(onNextAction, onErrorAction, onCompleteAction);
        example.observer2();
        example.observer3();
        example.observer4();

    }

    public void observer4() {
        Observable.just("Hello, America!")
                .subscribe(s -> System.out.println(s + " -Dan"));
    }

    public void observer3() {
        Observable.just("Hello, JSF!").subscribe(s -> System.out.println(s));
    }

    public void observer2() {
        Observable.just("Hello, java World!")
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println(s);
                    }
                });
    }

//////////////////////////////////////////////////////////////
    public Observable observer1() {
        Observable<String> myObservable1 = Observable.just("Aloha, world!");
        return myObservable1;
    }

    public Action1 action() {
        Action1<String> onNextAction = new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("Action called " + s);
            }
        };
        return onNextAction;
    }
/////////////////////////////////////////////////////////////////////////////

    public Observable observer() {

        this.myObservable = Observable.create(
                new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> sub) {
                        sub.onNext("Hello, world!");
                        sub.onCompleted();
                    }

                }
        );
        return myObservable;
    }

    public Subscriber subscriber() {
        mySubscriber = new Subscriber<String>() {
            @Override
            public void onNext(String s) {
                System.out.println(s);
            }

            @Override
            public void onCompleted() {
                System.out.println("onCompleted called");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError called " + e.getMessage());
            }
        };
        return mySubscriber;
    }
}
