package com.example.sdkdemo;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

//import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.EditText;

import com.im.sdk.ImsdkCallback;
import com.im.sdk.ImsdkNative;

import com.example.sdkdemo.NetBroadcastReceiver;
import com.example.sdkdemo.NetBroadcastReceiver.netEventHandler;

public class MainActivity extends AppCompatActivity implements netEventHandler {

    private Button clearBtn_;
    private Button loginBtn_;
    private Button logoutBtn_;
    private Button nextBtn_;
    private Button resetBtn_;
    private Button sendBtn_;
    private TextView outputView_;
    private ScrollView scollView_;
    private EditText editText_;

    private Command command_;

    public void addText(String text) {
        System.out.println(text);
        outputView_.setText(outputView_.getText() + "\r\n" +text);
        scollView_.fullScroll(ScrollView.FOCUS_DOWN);
    }

    public void setText(String text) {
        System.out.println(text);
        outputView_.setText(text);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        {//mycode
            NetBroadcastReceiver.mListeners.add(this);

            clearBtn_ = (Button) findViewById(R.id.button_clear);
            loginBtn_= (Button) findViewById(R.id.button_login);
            logoutBtn_ = (Button) findViewById(R.id.button_logout);
            nextBtn_ = (Button) findViewById(R.id.button_next);
            resetBtn_ = (Button) findViewById(R.id.button_reset);
            outputView_ = (TextView) findViewById(R.id.textview_output);
            scollView_ = (ScrollView) findViewById(R.id.scrollView_output);
            sendBtn_ = (Button) findViewById(R.id.button_send);
            editText_ = (EditText) findViewById(R.id.editText_emoji);

            TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
            String sim_name = telephonyManager.getSimOperatorName();//直接获取到运营商的名字
            System.out.println("sim_name: " + sim_name);

            final ImsdkNative imsdk_native = new ImsdkNative();
            imsdk_native.InitImClient(new ImsdkCallback(imsdk_native, this), getApplicationContext().getFilesDir().getAbsolutePath());
            imsdk_native.ShowLog(true, 0);

            command_ = new Command(this, imsdk_native);
            command_.init();

            loginBtn_.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    command_.login();
                }
            });

            logoutBtn_.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    command_.logout();
                }
            });

            clearBtn_.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setText("");
                }
            });

            nextBtn_.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    command_.next();
                }
            });

            resetBtn_.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    command_.reset();
                }
            });

            sendBtn_.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    command_.send(editText_.getText().toString());
                }
            });
        }//end my code

//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    @Override
    public void onNetChange(int netWorkState) {
//        // TODO Auto-generated method stub
//        if (NetUtil.getNetworkState(this) == NetUtil.NETWORN_NONE) {
//
//        }else {
//
//        }

        final String log = "netWorkState: " + netWorkState;
        runOnUiThread(new Runnable() {
            public void run() {
                addText(log);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("--------- onDestroy ---------");
    }
}
