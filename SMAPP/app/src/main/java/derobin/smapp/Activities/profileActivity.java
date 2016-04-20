package derobin.smapp.Activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.login.widget.ProfilePictureView;

import derobin.smapp.R;
import derobin.smapp.Tool.ToolbarDrawer;
import derobin.smapp.User.User;

public class profileActivity extends ToolbarDrawer {

    //file profile;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FacebookSdk.sdkInitialize(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        super.setupToolBar();

        ProfilePictureView ppv = (ProfilePictureView) findViewById(R.id.profile_picture);
        ppv.setProfileId(AccessToken.getCurrentAccessToken().getUserId());


        TextView divAccountInfo = (TextView) findViewById(R.id.div_account_info);
        divAccountInfo.setText("Account info:");


        user = new User(this);
    }
    public void infoPullComplete() {
        Log.d("[FB]", "User object: " + user.toString());
    }
}
