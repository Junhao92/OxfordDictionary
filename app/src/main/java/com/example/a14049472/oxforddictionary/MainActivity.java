package com.example.a14049472.oxforddictionary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView t1;
    EditText e1;

    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1=(TextView)findViewById(R.id.textView);
        e1=(EditText)findViewById(R.id.editText);
        url=dictionaryEntries();

    }

    public void sendReq(View view){
        MyRequestAsync myRequestAsync=new MyRequestAsync(this,t1);
        myRequestAsync.execute(e1.getText().toString());
    }

    private String dictionaryEntries() {
        final String language = "en";
        final String word = "Ace";
        final String word_id = word.toLowerCase(); //word id is case sensitive and lowercase is required
        return "https://od-api.oxforddictionaries.com:443/api/v1/entries/" + language + "/" + word_id;
    }

}
