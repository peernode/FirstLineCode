package com.example.uiwidgettest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends Activity {

    private Button btn;
    private EditText editText;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText)findViewById(R.id.edit_text);
        progressBar = (ProgressBar)findViewById(R.id.progress_bar);
        btn = (Button)findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1.
//                Toast.makeText(MainActivity.this, "you click the button.", Toast.LENGTH_SHORT).show();

                // 2.
//                String input = editText.getText().toString();
//                Toast.makeText(MainActivity.this, input, Toast.LENGTH_SHORT).show();

                //3. progress bar
//                if(progressBar.getVisibility()==View.GONE){
//                    progressBar.setVisibility(View.VISIBLE);
//                }else{
//                    progressBar.setVisibility(View.GONE);
//                }

                // 4. horizontal bar
                int progress = progressBar.getProgress();
                progress += 10;
                progressBar.setProgress(progress);
            }
        });


    }
}
