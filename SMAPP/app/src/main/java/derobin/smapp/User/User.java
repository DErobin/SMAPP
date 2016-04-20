package derobin.smapp.User;

import android.os.Bundle;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;

import org.json.JSONException;
import org.json.JSONObject;

import derobin.smapp.Activities.profileActivity;

/**
 * Created by Robin on 13-4-2016.
 */


public class User {
    private String token;
    private String name;
    private String email;
    private String gender;
    private String birthday;
    public User(final profileActivity act) {
        FacebookSdk.sdkInitialize(act);
        if(AccessToken.getCurrentAccessToken() == null)
            return;
        token = AccessToken.getCurrentAccessToken().getToken();
        GraphRequest request = GraphRequest.newMeRequest( AccessToken.getCurrentAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Log.v("[FB] Pulling info", response.toString());

                        // Application code
                        try {
                            name = object.getString("name");
                            email = object.getString("email");
                            gender = object.getString("gender");
                            birthday = object.getString("birthday");
                            act.infoPullComplete();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        // 01/31/1980 format
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,email,gender,birthday");
        request.setParameters(parameters);
        request.executeAsync();
    }

    @Override
    public String toString() {
        return name + " " + email + " " + gender + " " + birthday;
    }
}
