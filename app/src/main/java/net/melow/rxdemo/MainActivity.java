package net.melow.rxdemo;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

public class MainActivity extends AppCompatActivity {
    private ImageView mIv01;

   /* @BindView(R.id.iv_01)
    ImageView mIv01;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*ButterKnife.bind(this);*/
        mIv01 = (ImageView) findViewById(R.id.iv_01);

        //case1();
        //case2();
        case3();
    }

    private void case3() {
        Observable.just(R.mipmap.ic_launcher)
                //.subscribeOn(Schedulers.io())
                .map(new Func1<Integer, Drawable>() {
                    @Override
                    public Drawable call(Integer integer) {
                        return getResources().getDrawable(integer);
                    }
                })
                //.observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Drawable>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(MainActivity.this, "出错了:" + e.getMessage(), Toast.LENGTH_LONG).show();
                        Log.d("Moke", e.getMessage());
                    }

                    @Override
                    public void onNext(Drawable drawable) {
                        mIv01.setImageDrawable(drawable);
                    }
                });

    }

    private void case2() {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("hello world");
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        });
    }

    private void case1() {
        String[] arr = new String[]{"arrti", "afafuis", "bdfeee", "ceeees"};
        Observable.from(arr)
                .filter(new Func1<String, Boolean>() {
                    @Override
                    public Boolean call(String s) {
                        return s.startsWith("a");
                    }
                })
                .map(new Func1<String, String>() {

                    @Override
                    public String call(String s) {
                        return s + "----";
                    }
                })
                .subscribe(new Observer<String>() {
                               @Override
                               public void onCompleted() {

                               }

                               @Override
                               public void onError(Throwable e) {

                               }

                               @Override
                               public void onNext(String s) {
                                   System.out.println(s);

                               }
                           }
                );

        /*.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        });*/
    }
}
