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
import com.example.mhr19.jobportle.OnlineDb.SignUpUser;
import com.example.mhr19.jobportle.OnlineDb.User;

import org.json.JSONException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;


public class signUpActivity extends Activity {


    EditText username ;
    EditText password;
    EditText emailId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        username=(EditText)findViewById(R.id.username);
        password=(EditText)findViewById(R.id.password);
        emailId=(EditText)findViewById(R.id.email);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sign_up, menu);
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

    public void SignUpClick(View view) {

        String u=username.getText().toString();
        String p = password.getText().toString();
        String e=emailId.getText().toString();


        ((SignUpUser)this.getApplication()).setUserData1(u,p,e);
        final User s = ((SignUpUser) this.getApplication()).getUser();

        new AsyncTask<Void, Void, String>(){
            protected String doInBackground(Void[] params) {
                String response="";
                try {
                    insert(s);

                } catch (Exception e) {

                    e.printStackTrace();

                }
                return response;
            }
            protected void onPostExecute(String result) {


                //do something with response
            }
        }.execute();



        Intent i = new Intent(this,MainMenu.class);
        startActivity(i);
    }
    public String insert(User u)
    {
        String response="";

        try {
            HttpRequest req=new HttpRequest("http://mydb.netai.net/insert.php");
            HashMap<String, String> params=new HashMap<>();
            params.put("userId","0");
            params.put("username", u.getUsername());
            params.put("Password", u.getPassword());
            params.put("email", u.getEmail());
            params.put("degree", u.getDegree());
            params.put("country", u.getCountry());
            params.put("city", u.getCity());
            params.put("streetAddress", u.getStreetAddress());
            try {
                response=req.preparePost().withData(params).sendAndReadJSON().toString();
            } catch (JSONException e) {
                Toast.makeText(this, "JSON exception", Toast.LENGTH_SHORT);
            } catch (IOException e) {
                Toast.makeText(this, "IO exception", Toast.LENGTH_SHORT);
            }

        } catch (MalformedURLException e) {

            Toast.makeText(this,"Couldn't Make Request",Toast.LENGTH_SHORT);

        }


        return response;
    }
}
