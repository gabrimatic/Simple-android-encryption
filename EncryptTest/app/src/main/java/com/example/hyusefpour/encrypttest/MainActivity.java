package com.example.hyusefpour.encrypttest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.security.MessageDigest;
import java.util.Arrays;

import javax.crypto.spec.SecretKeySpec;

public class MainActivity extends AppCompatActivity {

    private static final String KEY = "5k8RYjhs78jkmV7*i)h#5tar43N3M0,k";
    private Button cryptBtn, deCryptBtn;
    private TextView cryptTV, deCryptTV, keyTV, cKeyTV;
    private EditText cryptET, deCryptET;
    private byte[] bts = null;
    private SecretKeySpec secretKeySpec;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cryptBtn = findViewById(R.id.btnCryptID);
        deCryptBtn = findViewById(R.id.btnDecryptID);
        cryptTV = findViewById(R.id.tvCryptID);
        deCryptTV = findViewById(R.id.tvDecryptID);
        cryptET = findViewById(R.id.etCryptID);
        deCryptET = findViewById(R.id.etDecryptID);
        keyTV = findViewById(R.id.keyTvID);
        cKeyTV = findViewById(R.id.cryptedKeyTvID);

        // key
        try {
            keyTV.setText(keyTV.getText() + KEY);
            byte[] key = (KEY).getBytes("UTF-8");
            MessageDigest sha = MessageDigest.getInstance("SHA-512");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 32);
            secretKeySpec = new SecretKeySpec(key, "AES");

            String s = cKeyTV.getText() + new String(key, "UTF-8");
            cKeyTV.setText(s);

        } catch (Exception e) {
        }
        // key

        cryptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    bts = Cryptogram.Encrypt(cryptET.getText().toString(), secretKeySpec);
                    String s = new String(bts,"UTF-8");
                    cryptTV.setText(s);
                    deCryptET.setText(s);
                }
                catch (Exception e){
                    cryptTV.setText(e.toString());
                    deCryptET.setText(e.toString());
                }
            }
        }); // crypt btn

        deCryptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = Cryptogram.Decrypt(bts, secretKeySpec);
                deCryptTV.setText(s);
            }
        }); // decrypt btn

    }
}
