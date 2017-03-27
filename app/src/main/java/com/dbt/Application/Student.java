package com.dbt.Application;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;

/**
 * Created by admin on 3/14/2017.
 */
@ParseClassName("Students")
public class Student extends ParseObject {
    private static String StudName = "Stud_F_Name";

    private static String StudEmail = "Stud_Email";

    private static String StudContact = "Stud_Contact";

    private static String StudProfile = "Stud_Prof_Pic";

    public Student() {
    }

    public String getStudName() {
        return getString(StudName);
    }

    public void setStudName(String studName) {
        put(StudName, studName);
    }

    public String getStudEmail() {
        return getString(StudEmail);
    }

    public void setStudEmail(String studEmail) {
        put(StudEmail, studEmail);
    }

    public String getStudContact() {
        return getString(StudContact);
    }

    public void setStudContact(String studContact) {
        put(StudContact, studContact);
    }

    public ParseFile getStudProfile() {
        return getParseFile(StudProfile);
    }

    public void setStudProfile(ParseFile studProfile) {
        put(StudProfile, studProfile);
    }

}
