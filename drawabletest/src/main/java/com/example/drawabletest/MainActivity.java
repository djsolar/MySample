package com.example.drawabletest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView lv = (ListView) findViewById(R.id.lv);
        lv.setAdapter(new ExampleAdapter());

       /* Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.a);
        CircleDrawable circleDrawable = new CircleDrawable(bitmap);
        ImageView iv = (ImageView) findViewById(R.id.imageView);
        iv.setImageDrawable(circleDrawable);*/

       /* RotateDrawable rotateDrawable = (RotateDrawable) iv.getDrawable();
        rotateDrawable.setLevel(10000);*/
        //iv.setImageResource(R.drawable.animation1);
        /*AnimationDrawable animationDrawable = (AnimationDrawable) iv.getDrawable();
        animationDrawable.start();*/
        /*Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.a);
        TransitionDrawable transitionDrawable = new TransitionDrawable(new Drawable[] {new ColorDrawable(Color.parseColor("#fffcfcfc")), new BitmapDrawable(getResources(), bitmap)});
        iv.setImageDrawable(transitionDrawable);
        transitionDrawable.startTransition(1000);*/


       /* RectShape rectShape = new RectShape();
        ShapeDrawable shapeDrawable = new ShapeDrawable(rectShape);
        shapeDrawable.setBounds(0, 0, 100, 100);
        shapeDrawable.getPaint().setColor(Color.BLACK);
        iv.setImageDrawable(shapeDrawable);
        shapeDrawable.setLevel(10000);*/
    }

    private static class ExampleAdapter extends BaseAdapter {

        private Message[] messages;

        private ExampleAdapter() {
            messages = new Message[] {
                    new Message("Gas bill overdue", true),
                    new Message("Congratulations, you've won!", true),
                    new Message("I love you!", false),
                    new Message("Please reply!", false),
                    new Message("You ignoring me?", false),
                    new Message("Not heard from you", false),
                    new Message("Electricity bill", true),
                    new Message("Gas bill", true),
                    new Message("Holiday plans", false),
                    new Message("Marketing stuff", false),
            };
        }

        @Override
        public int getCount() {
            return messages.length;
        }

        @Override
        public Message getItem(int i) {
            return messages[i];
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            MessageListItem messageListItem = (MessageListItem) view;
            if (messageListItem == null) {
                messageListItem = new MessageListItem(viewGroup.getContext());
            }
            messageListItem.setMessageSubject(getItem(i).subject);
            messageListItem.setMessageReaded(getItem(i).unread);
            return messageListItem;
        }
    }

    private static class Message {

        private String subject;
        private boolean unread;

        private Message(String subject, boolean unread) {
            this.subject = subject;
            this.unread = unread;
        }

    }

}
