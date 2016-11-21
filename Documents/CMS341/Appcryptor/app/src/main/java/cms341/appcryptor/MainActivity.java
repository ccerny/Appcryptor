package cms341.appcryptor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class MainActivity extends AppCompatActivity {
    Cryptor cryptor;
    EditText message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        message = (EditText)findViewById(R.id.text_entry);
        try {
            cryptor = new Cryptor();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public void encryptor(View v) throws BadPaddingException, InvalidKeyException, IllegalBlockSizeException {
        String encryptedText = cryptor.encryptText(message.getText().toString(), "thisIsAnEncryptionKeyForThisApp1");
        message.setText(encryptedText);
    }

    public void decryptor(View v) throws BadPaddingException, InvalidKeyException, IllegalBlockSizeException {
        String decryptedText = cryptor.decryptText(message.getText().toString(), "thisIsAnEncryptionKeyForThisApp1");
        message.setText(decryptedText);
    }
}
