package com.example;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class Test {

    public static void main(String[] args) {
        /*List<Student> students = new ArrayList<>();
        for(int i = 0; i < 5; i++) {
            Student student = new Student();
            student.setName("student" + i);
            List<Course> courses = new ArrayList<>();
            for(int j = 0; j < 4; j++) {
                Course course = new Course();
                course.setName("course" + i + "-" + j);
                courses.add(course);
            }
            student.setCourses(courses);
            students.add(student);
        }

        Flowable.fromIterable(students).flatMap(new Function<Student, Publisher<Course>>() {
            @Override
            public Publisher<Course> apply(@NonNull Student student) throws Exception {
                return Flowable.fromIterable(student.getCourses());
            }
        }).subscribe(new Consumer<Course>() {
            @Override
            public void accept(@NonNull Course course) throws Exception {
                System.out.println(course.getName());
            }
        });*/
        /*List<String[]> list = new ArrayList<>();
        String[] word1 = new String[] {"Hello", "I am", "China"};
        String[] word2 = new String[] {"Hello", "I am", "Beijing"};
        list.add(word1);
        list.add(word2);
        Flowable.fromIterable(list).flatMap(new Function<String[], Publisher<String>>() {
            @Override
            public Publisher<String> apply(@NonNull String[] strings) throws Exception {
                return Flowable.fromArray(strings);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(@NonNull String s) throws Exception {
                System.out.println(s);
            }
        });*/

       /*Flowable.just("Hello, I am China").subscribeWith(new Subscriber<String>() {
           @Override
           public void onSubscribe(Subscription s) {
               s.request(1);
           }

           @Override
           public void onNext(String s) {
               System.out.println(s);
           }

           @Override
           public void onError(Throwable t) {

           }

           @Override
           public void onComplete() {

           }
       });*/
        Flowable.just("Hello, I am China").map(new Function<String, String>() {

            @Override
            public String apply(@NonNull String s) throws Exception {
                return s + "s";
            }
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onSubscribe(Subscription s) {
                s.request(1);
            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
