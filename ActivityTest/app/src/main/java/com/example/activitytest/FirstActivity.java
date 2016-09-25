package com.example.activitytest;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by xujunyang on 16/9/25.
 */
public class FirstActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);

        Button button1 = (Button)findViewById(R.id.button_1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 1. toast
//                Toast.makeText(FirstActivity.this, "You click Button 1", Toast.LENGTH_SHORT).show();

                // 2. finish
//                finish();  //销毁一个活动

                // 3. Intent
//                Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
//                startActivity(intent);

                // 4. 隐式Intent
//                Intent intent = new Intent("com.example.activitytest.ACTION_START");
//                intent.addCategory("com.example.activitytest.MY_CATEGORY");
//                startActivity(intent);

                // 5. URI
//                Intent intent = new Intent("android.intent.action.VIEW");
//                intent.setData(Uri.parse("http://www.baidu.com"));
//                startActivity(intent);

                  //6. carry data
//                Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
//                String data = "Hello SecondActivity";
//                intent.putExtra("extra_data", data);
//                startActivity(intent);

                // 7. return data
                Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                String data = "Hello SecondActivity";
                intent.putExtra("extra_data", data);
                startActivityForResult(intent, 1);

            }

        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        switch (requestCode){
            case 1:
                if(resultCode==RESULT_OK){
                    String returnData = data.getStringExtra("data_return");
                    Toast.makeText(FirstActivity.this, returnData, Toast.LENGTH_SHORT).show();
                }
                break;

            default:

        }
    }
}
