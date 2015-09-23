package com.example.mhr19.jobportle.OnlineDb;

import android.app.Application;

/**
 * Created by Mughees on 9/18/2015.
 */
public class SignUpUser extends Application {

    User sUser= new User();

    public void setUserData1(String username , String password , String email)
    {

        sUser.username=username;
        sUser.password=password;
        sUser.email=email;
    }
    public void setUserData2(String streetAd, String city,String country )
    {

        sUser.streetAddress=streetAd;
        sUser.city=city;
        sUser.country=country;

    }
    public void setUserData3(String college, String degree)
    {

        sUser.college=college;
        sUser.degree=degree;
    }

    public User getUser()
    {
        return sUser;
    }
}
