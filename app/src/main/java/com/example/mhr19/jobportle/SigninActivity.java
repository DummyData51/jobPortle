package com.example.mhr19.jobportle;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mhr19.jobportle.OnlineDb.HttpRequest;
import com.example.mhr19.jobportle.OnlineDb.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;


public class SigninActivity extends Activity {


    EditText username ;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        username=(EditText)findViewById(R.id.loginUsername);
        password=(EditText)findViewById(R.id.loginPassword);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_signin, menu);
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

    public void SignInClick(View view)
    {
        final Boolean Status ;

        new AsyncTask<String , Void, Boolean>(){
            protected Boolean doInBackground(String...user) {

                 Boolean verified=false;
                try {

                   verified= VerifyUser(user[0],user[1]);
                 //   verified=true;

                } catch (Exception e) {

                    e.printStackTrace();

                }
                return verified;
            }
            protected void onPostExecute(Boolean verified) {
                //do something with response
                if(verified) {
                    Intent sui = new Intent(getApplicationContext(), MainMenu.class);
                    startActivity(sui);
                }
                else
                {
                    makeToast("Invalid User.Try Again");
                }


            }
        }.execute(username.getText().toString(), password.getText().toString());



    }
    void makeToast(String txt)
    {
        Toast.makeText(this,txt,Toast.LENGTH_SHORT).show();
    }

    public Boolean VerifyUser(String username , String password)
    {
        HttpRequest req= null;
        String s ="";
        try {
            req = new HttpRequest("http://mydb.netai.net/read.php");
            HashMap<String, String> params=new HashMap<>();

            params.put("username", username);
            params.put("password", password);

            String data =req.preparePost().withData(params).sendAndReadString();

            if(!data.contains("id")) {
                return false;
            }


            JSONArray jArray = new JSONArray(data);

            for(int i=0; i<jArray.length();i++){

                JSONObject json = jArray.getJSONObject(i);

                User u = new User();
                u.setUsername(json.getString("username"));
                u.setEmail(json.getString("email"));
                u.setPassword(json.getString("password"));

                return true;
            }


        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }
}
