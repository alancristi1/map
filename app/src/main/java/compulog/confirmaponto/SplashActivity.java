package compulog.confirmaponto;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        SharedPreferences sharedPreferences = getSharedPreferences("splash", Context.MODE_PRIVATE);
        boolean splash = sharedPreferences.getBoolean("splash", false);

        if(splash){
            Intent intent = new Intent(SplashActivity.this, MapsActivity.class);
            startActivity(intent);
        }else{
            new Handler().postDelayed(new Runnable(){

                @Override
                public void run() {
                    finish();
                    Intent intent = new Intent(SplashActivity.this, MapsActivity.class);
                    intent.putExtra("splash", true);
                    startActivity(intent);
                }
            }, SPLASH_TIME_OUT);
        }
    }

    @Override
    protected void onPause(){
        super.onPause();
        finish();
    }
}
