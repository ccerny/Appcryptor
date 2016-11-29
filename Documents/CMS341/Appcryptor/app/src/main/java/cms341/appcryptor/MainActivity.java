package cms341.appcryptor;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class MainActivity extends AppCompatActivity {
    Cryptor cryptor;
    EditText message;
    TextView response;
    Button reset;

    private VideoView vView;
    private MediaController vMediaController;
    private Button encrypt, decrypt;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vView = (VideoView)findViewById(R.id.video_view);
        reset = (Button)findViewById(R.id.new_message_button);

        vView.setVisibility(View.INVISIBLE);
        reset.setVisibility(View.INVISIBLE);

        configureVideo( ); //configuration of the animation

        message = (EditText) findViewById(R.id.text_entry);
        try {
            cryptor = new Cryptor();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

    }

    public void encryptor(View v) throws BadPaddingException, InvalidKeyException, IllegalBlockSizeException {

        vView = (VideoView)findViewById(R.id.video_view);
        encrypt = (Button)findViewById(R.id.encrypt_button);
        decrypt = (Button)findViewById(R.id.decrypt_button);
        response = (TextView)findViewById(R.id.text);
        reset = (Button)findViewById(R.id.new_message_button);


        encrypt.setVisibility(View.INVISIBLE);
        decrypt.setVisibility(View.INVISIBLE);
        message.setVisibility(View.INVISIBLE);

        response.setVisibility(View.VISIBLE);
        reset.setVisibility(View.VISIBLE);
        vView.setVisibility(View.VISIBLE);

        vView.start();                              // starts showing the encoding animation
        startAnimation(v);

        String encryptedText = cryptor.encryptText(message.getText().toString(), "thisIsAnEncryptionKeyForThisApp1");
        message.setText(encryptedText);
    }

    public void decryptor(View v) throws BadPaddingException, InvalidKeyException, IllegalBlockSizeException {

        vView = (VideoView)findViewById(R.id.video_view);
        encrypt = (Button)findViewById(R.id.encrypt_button);
        decrypt = (Button)findViewById(R.id.decrypt_button);
        response = (TextView)findViewById(R.id.text);
        reset = (Button)findViewById(R.id.new_message_button);



        encrypt.setVisibility(View.INVISIBLE);
        decrypt.setVisibility(View.INVISIBLE);
        message.setVisibility(View.INVISIBLE);

        response.setVisibility(View.VISIBLE);
        reset.setVisibility(View.VISIBLE);
        vView.setVisibility(View.VISIBLE);

        vView.start();                              // starts showing the encoding animation
        startAnimation(v);

        String decryptedText = cryptor.decryptText(message.getText().toString(), "thisIsAnEncryptionKeyForThisApp1");
        message.setText(decryptedText);
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    public void reset(View v){

        vView = (VideoView)findViewById(R.id.video_view);
        encrypt = (Button)findViewById(R.id.encrypt_button);
        decrypt = (Button)findViewById(R.id.decrypt_button);
        response = (TextView)findViewById(R.id.text);
        reset = (Button)findViewById(R.id.new_message_button);



        encrypt.setVisibility(View.VISIBLE);
        decrypt.setVisibility(View.VISIBLE);
        message.setVisibility(View.VISIBLE);
        message.setText("Enter message");
        response.setVisibility(View.INVISIBLE);
        reset.setVisibility(View.INVISIBLE);
        vView.setVisibility(View.INVISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu( Menu menu ) {
        // Inflate the menu; adds items to the action bar
        // if it is present
        getMenuInflater( ).inflate(
                R.menu.menu, menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item) {
        switch ( item.getItemId( )) {
            case R.id.menu_key_generator:
                loadAudioRecording(null);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void loadAudioRecording( View v ) {
        startActivity( new Intent( getApplicationContext( ),
                AudioRecording.class));
    }


    public void configureVideo( ) {

        vView = (VideoView)findViewById(R.id.video_view);

        String uriPath = "android.resource://cms341.appcryptor/" + R.raw.crypt;
        Uri uri = Uri.parse(uriPath);
        vView.setVideoURI(uri);

        vMediaController = new MediaController(this);
        vMediaController.setAnchorView(vView);
        vView.setMediaController(vMediaController);

        /*DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        android.widget.LinearLayout.LayoutParams params = (android.widget.LinearLayout.LayoutParams) vView.getLayoutParams();
        params.width =  metrics.widthPixels;
        params.height = metrics.heightPixels;
        params.leftMargin = 0;

        vView.setLayoutParams(params);
        */

    }

    public void startAnimation( View v ) {

        performAnimation(R.anim.fade_out_in);
    }


    private void performAnimation(int animationResourceID) {

        Animation an = AnimationUtils.loadAnimation(this, animationResourceID);
        an.setAnimationListener(new TweenAnimationListener());
        VideoView item = (VideoView) findViewById(R.id.video_view);
        item.startAnimation(an);

    }

    class TweenAnimationListener implements Animation.AnimationListener {

        public void onAnimationStart(Animation animation) {
            // Disable all buttons while animation is running
            enableButtons(false);
        }
        public void onAnimationEnd(Animation animation) {
            // Enable all buttons once animation is over
            enableButtons(true);
        }

        public void onAnimationRepeat(Animation animation) {

        }


        private void enableButtons(boolean enabledState) {
            // Fade-out, fade-in
            final Button fadeButton = (Button) findViewById(R.id.encrypt_button);
            fadeButton.setEnabled(enabledState);

        }

    }
}
