package com.example.wonbin.myanimation.activities;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import com.example.wonbin.myanimation.R;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class SharedElementAnimationActivity extends ActionBarActivity {

    private ImageView imageView;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_element_animation);

        imageView = (ImageView) findViewById(R.id.image);
        button = (Button) findViewById(R.id.btn_down);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SharedElementAnimationActivity.this,
                        SharedElementAnimationSecondActivity.class);
                ((ViewGroup) imageView.getParent()).setTransitionGroup(false);

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                ((BitmapDrawable)imageView.getDrawable()).getBitmap().compress(Bitmap.CompressFormat.PNG,100,stream);

                intent.putExtra("image",stream.toByteArray());

                ActivityOptions options =
                        ActivityOptions.makeSceneTransitionAnimation(SharedElementAnimationActivity.this,
                                imageView,"image");

                if(options != null) {
                    startActivity(intent,options.toBundle());
                } else {
                    Log.e("SharedElementAnimation","option is null ,did you set?");
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_shared_element_animation, menu);
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
}
