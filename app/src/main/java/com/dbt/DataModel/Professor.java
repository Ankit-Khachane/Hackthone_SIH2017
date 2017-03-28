package com.dbt.DataModel;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;

/**
 * Created by khach on 28-03-2017.
 */
@ParseClassName("Students")
public class Professor extends ParseObject {
    private static String Profuid = "Proff_uid";
    private static String ProfDesignation = "Prof_Designation";
    private static String ProfEmail = "Prof_Email";
    private static String ProfFName = "Prof_F_name";
    private static String ProfLName = "Prof_L_name";
    private static String ProfContact = "Prof_Contact";
    private static String ProfPassword = "Prof_Password";
    private static String ProfDepartment = "Prof_Department";
    private static String ProfAdminPointer = "Admin_uid";
    private static String ProfPic = "Prof_pic";


    public Professor() {
    }

    public String getProfuid() {
        return getString(Profuid);
    }

    public void setProfuid(String profuid) {
        put(Profuid, profuid);
    }

    public String getProfDesignation() {
        return getString(ProfDesignation);
    }

    public void setProfDesignation(String profDesignation) {
        put(ProfDesignation, profDesignation);
    }

    public String getProfEmail() {
        return getString(ProfEmail);
    }

    public void setProfEmail(String profEmail) {
        put(ProfEmail, profEmail);
    }

    public String getProfFName() {
        return getString(ProfFName);
    }

    public void setProfFName(String profFName) {
        put(ProfFName, profFName);
    }

    public String getProfLName() {
        return getString(ProfLName);
    }

    public void setProfLName(String profLName) {
        put(ProfLName, profLName);
    }

    public String getProfContact() {
        return getString(ProfContact);
    }

    public void setProfContact(String profContact) {
        put(ProfContact, profContact);
    }

    public String getProfPassword() {
        return getString(ProfPassword);
    }

    public void setProfPassword(String profPassword) {
        put(ProfPassword, profPassword);
    }

    public String getProfDepartment() {
        return getString(ProfDepartment);
    }

    public void setProfDepartment(String profDepartment) {
        put(ProfDepartment, profDepartment);
    }

    public ParseObject getProfAdminPointer() {
        return getParseObject(ProfAdminPointer);
    }

    public void setProfAdminPointer(String profAdminPointer) {
        put(ProfAdminPointer, profAdminPointer);
    }

    public ParseFile getProfPic() {
        return getParseFile(ProfPic);
    }

    public void setProfPic(String profPic) {
        put(ProfPic, profPic);
    }
}
