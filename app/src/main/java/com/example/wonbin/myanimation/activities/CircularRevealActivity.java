package com.example.wonbin.myanimation.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.example.wonbin.myanimation.R;

public class CircularRevealActivity extends ActionBarActivity {

    private ImageView imageView;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circular_reveal);
        imageView = (ImageView) findViewById(R.id.image);
        button = (Button) findViewById(R.id.btn_press);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(imageView.getVisibility() == View.INVISIBLE) {
                    revealImageCircular();
                } else {
                    hideImageCircular();
                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_circular_reveal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void revealImageCircular() {
        int x = getX();
        int y = getX();
        int finalRaduis = getRadius();

        Animator animator =
                ViewAnimationUtils.createCircularReveal(imageView,x,y,0,finalRaduis);
        animator.setDuration(1000);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                imageView.setVisibility(View.VISIBLE);
            }
        });
        animator.start();
    }

    private void hideImageCircular() {

        int x = getX();
        int y = getY();
        int initialRadius = getRadius();

        Animator anim =
                ViewAnimationUtils.createCircularReveal(imageView,x,y,initialRadius,0);
        anim.setDuration(1000);
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                imageView.setVisibility(View.INVISIBLE);
            }
        });
        anim.start();
    }
    private int getX() {
        return (imageView.getLeft() + imageView .getRight()) / 2;
    }

    private int getY () {
        return (imageView.getTop() + imageView.getBottom()) / 2;
    }

    private int getRadius() {
        return  Math.max(imageView.getWidth(), imageView.getHeight());
    }
}
