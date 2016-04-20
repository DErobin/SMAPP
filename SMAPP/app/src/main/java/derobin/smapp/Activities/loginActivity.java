package derobin.smapp.Activities;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import derobin.smapp.R;
import derobin.smapp.Tool.ToolbarDrawer;

public class loginActivity extends ToolbarDrawer {

    LoginButton btnLogin;
    CallbackManager cbm;
    //SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FacebookSdk.sdkInitialize(getApplicationContext());

        cbm = CallbackManager.Factory.create();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        super.setupToolBar();

        //getAppKeyHash();

        btnLogin = (LoginButton) this.findViewById(R.id.login_button);
        //btnLogin.setLoginBehavior(LoginBehavior.WEB_ONLY);
        btnLogin.setReadPermissions(Arrays.asList("public_profile", "email", "user_birthday", "user_friends"));

        /*
        if(AccessToken.getCurrentAccessToken() != null) {
            Intent profile = new Intent(getInstance(), profileActivity.class);
            startActivity(profile);
            finish();
        }*/
        //LoginManager.getInstance().logOut();
        LoginManager.getInstance().registerCallback(cbm, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d("[FB]", "Logged in with token: " + loginResult.getAccessToken().getToken());
                Intent profile = new Intent(getInstance(), profileActivity.class);
                startActivity(profile);
            }

            @Override
            public void onCancel() {
                // App code
                Log.d("[FB]", "Login cancelled");
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                Log.d("[FB]", "Error while logging in");
            }
        });
        //Log.d("MSG","hallo");
    }

    private Activity getInstance() {
        return this;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        cbm.onActivityResult(requestCode, resultCode, data);
    }

    //Facebook related, for generating the app keyhash.
    private void getAppKeyHash() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;

                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = new String(Base64.encode(md.digest(), 0));
                Log.d("Hash key", something);
            }
        }
        catch (PackageManager.NameNotFoundException e1) {
            // TODO Auto-generated catch block
            Log.e("name not found", e1.toString());
        }

        catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            Log.e("no such an algorithm", e.toString());
        }
        catch (Exception e){
            Log.e("exception", e.toString());
        }

    }

}

