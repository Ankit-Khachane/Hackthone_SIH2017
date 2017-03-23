package com.dbt.Application;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by admin on 3/23/2017.
 */

public class PreferenceManager {
    private static final String PrefName = "User_Details";
    private static final String UserName = "UserName";
    private static final String UserEmail = "UserEmail";
    private static final String EmailVerified = "EmailVerified";
    private static final String isLoggedIn = "isLoggedIn";
    private static final String SessionStatus = "SessionStatus";
    private static final String UserType = "UserType";
    private static final String UID = "UID";

    static SharedPreferences sp;
    SharedPreferences.Editor edtr;
    Context p_ctx;

    //TODO :add get set preference for student "batch"
    public PreferenceManager(Context ctx) {
        this.p_ctx = ctx;
        sp = p_ctx.getSharedPreferences(PrefName, Context.MODE_PRIVATE);
        edtr = sp.edit();
    }

    public static String getUserName() {
        return sp.getString(UserName, null);
    }

    public void setUserName(String userName) {
        edtr.putString(UserName, userName);
        edtr.commit();
    }

    public static String getUserEmail() {
        return sp.getString(UserEmail, null);
    }

    public void setUserEmail(String userEmail) {
        edtr.putString(UserEmail, userEmail);
        edtr.commit();
    }

    public static String getUserType() {
        return sp.getString(UserType, null);
    }

    public void setUserType(String userType) {
        edtr.putString(UserType, userType);
        edtr.commit();
    }

    public static boolean getEmailVerified() {
        return sp.getBoolean(EmailVerified, false);
    }

    public void setEmailVerified(boolean emailVerified) {
        edtr.putBoolean(EmailVerified, emailVerified);
        edtr.commit();
    }

    public static boolean getIsLoggedIn() {
        return sp.getBoolean(isLoggedIn, false);
    }

    public void setIsLoggedIn(boolean isLoggedIn1) {
        edtr.putBoolean(isLoggedIn, isLoggedIn1);
        edtr.commit();
    }

    public static boolean getSessionStatus() {
        return sp.getBoolean(SessionStatus, false);
    }

    public void setSessionStatus(boolean sessionStatus) {
        edtr.putBoolean(SessionStatus, sessionStatus);
        edtr.commit();
    }

    public static String getUID() {
        return sp.getString(UID, null);
    }

    public void setSession(String uname, String uemail, String utype, boolean emailverified, boolean isLoggedin, boolean sessionstatus, String uid) {
        edtr.putString(UserName, uname);
        edtr.putString(UserEmail, uemail);
        edtr.putString(UserType, utype);
        edtr.putBoolean(EmailVerified, emailverified);
        edtr.putBoolean(isLoggedIn, isLoggedin);
        edtr.putBoolean(SessionStatus, sessionstatus);
        edtr.putString(UID, uid);
        edtr.commit();
    }

    public void logout() {
        edtr.clear();
        edtr.commit();
    }

}
