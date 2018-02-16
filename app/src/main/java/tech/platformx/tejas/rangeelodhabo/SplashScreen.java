package tech.platformx.tejas.rangeelodhabo;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

/**
 * Created by Carl_johnson on 8/3/2017.
 */

public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 3000;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splashscreen);

            // if connected with internet
            new Handler().postDelayed(new Runnable()
            {
                @Override
                public void run()
                {
                    //Toast.makeText(getApplication(),"hello.....",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplication(),MainActivity.class);
                    startActivity(i);
                    // close this activity
                    finish();
                }
            }, SPLASH_TIME_OUT);

            //Toast.makeText(this, " Welcome To platformx... ", Toast.LENGTH_LONG).show();
    }
}
