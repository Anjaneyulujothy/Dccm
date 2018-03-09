package i.atomic77.com.dccm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView messag,m1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LocalBroadcastManager.getInstance(this).registerReceiver(mhandler,new IntentFilter("i.atomic77.com.dccm_FCM-MESSAGE"));
        setContentView(R.layout.activity_main);
        messag=(TextView)findViewById(R.id.message1);
        m1=(TextView)findViewById(R.id.message);
        if(getIntent().getExtras()!=null)
        for(String key:getIntent().getExtras().keySet())
        {
           if(key.equals("title"))
           {

               m1.setText(getIntent().getExtras().getString(key));
           }
           else if(key.equals("message"))
           {

               messag.setText(getIntent().getExtras().getString(key));
           }

        }

    }

    private BroadcastReceiver mhandler=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            String title=intent.getStringExtra("title");

            String message=intent.getStringExtra("message");
            m1.setText(title);
            messag.setText(message);

        }
    };

    @Override
    protected void onPause() {
        super.onPause();

        LocalBroadcastManager.getInstance(this).unregisterReceiver(mhandler);
    }
}
