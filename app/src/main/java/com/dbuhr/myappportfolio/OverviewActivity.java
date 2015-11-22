package com.dbuhr.myappportfolio;

import android.app.Activity;
import android.content.Context;
import android.graphics.LightingColorFilter;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class OverviewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);
        customizeButtonBehavior(R.id.button_capstone_my_own_app);

    }
    private void customizeButtonBehavior(int id)
    {
        Button button = (Button) findViewById(id);
        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // 0x6D6D6D sets how much to darken - tweak as desired
                        setColorFilter(v, 0x6D6D6D);
                        break;
                    // remove the filter when moving off the button
                    // the same way a selector implementation would
                    case MotionEvent.ACTION_MOVE:
                        Rect r = new Rect();
                        v.getLocalVisibleRect(r);
                        if (!r.contains((int) event.getX(), (int) event.getY())) {
                            setColorFilter(v, null);
                        }
                        break;
                    case MotionEvent.ACTION_OUTSIDE:
                    case MotionEvent.ACTION_CANCEL:
                    case MotionEvent.ACTION_UP:
                        setColorFilter(v, null);
                        break;
                }
                return false;
            }

            private void setColorFilter(View v, Integer filter) {
                if (filter == null) v.getBackground().clearColorFilter();
                else {
                    // To lighten instead of darken, try this:
                    // LightingColorFilter lighten = new LightingColorFilter(0xFFFFFF, filter);
                    LightingColorFilter darken = new LightingColorFilter(filter, 0x000000);
                    v.getBackground().setColorFilter(darken);
                }
                // required on Android 2.3.7 for filter change to take effect (but not on 4.0.4)
                v.getBackground().invalidateSelf();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_overview, menu);
        return true;
    }

    public void showToast(View v){
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        CharSequence text = "";
        int id = v.getId();
        switch(id)
        {
            case R.id.button_spotify_streamer:
                text = "Spotify Streamer";
                break;
            case R.id.button_scores_app:
                text = "Scores App";
                break;
            case R.id.button_library_app:
                text = "Library App";
                break;
            case R.id.button_build_it_bigger:
                text = "Build It Bigger App";
                break;
            case R.id.button_xyz_reader:
                text = "XYZ Reader App";
                break;
            case R.id.button_capstone_my_own_app:
                text = "Capstone: My Own App";
                break;
            default:
                break;
        }

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
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
