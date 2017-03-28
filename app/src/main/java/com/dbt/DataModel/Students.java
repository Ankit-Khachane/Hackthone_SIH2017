package com.dbt.DataModel;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;

/**
 * Created by admin on 3/14/2017.
 */
@ParseClassName("Students")
public class Students extends ParseObject {
    private static String StudUid = "Stud_uid";
    private static String StudRollNo = "Stud_Roll_no";
    private static String StudFName = "Stud_F_Name";
    private static String StudLName = "Stud_L_Name";
    private static String StudEmail = "Stud_Email";
    private static String StudContact = "Stud_Contact";
    private static String StudDepartment = "Stud_Department";
    private static String StudProfile = "Stud_Prof_Pic";
    private static String StudThesis = "Stud_Thesis";
    private static String StudAttendance = "Stud_Attendance";
    private static String StudAdminPointer = "Admin_uid";
    private static String StudProfessorPointer = "Proff_uid";

    public Students() {
    }

    public ParseObject getStudAdminPointer() {
        return getParseObject(StudAdminPointer);
    }

    public void setStudAdminPointer(String studAdminPointer) {
        put(StudAdminPointer, studAdminPointer);
    }

    public ParseObject getStudProfessorPointer() {
        return getParseObject(StudProfessorPointer);
    }

    public void setStudProfessorPointer(String studProfessorPointer) {
        put(StudProfessorPointer, studProfessorPointer);
    }

    public ParseObject getStudAttendance() {
        return getParseObject(StudAttendance);
    }

    public void setStudAttendance(String studAttendance) {
        put(StudAttendance, studAttendance);
    }

    public String getStudRollNo() {
        return getString(StudRollNo);
    }

    public void setStudRollNo(String studRollNo) {
        put(StudRollNo, studRollNo);
    }

    public String getStudDepartment() {
        return getString(StudDepartment);
    }

    public void setStudDepartment(String studDepartment) {
        put(StudDepartment, studDepartment);
    }

    public ParseObject getStudThesis() {
        return getParseObject(StudThesis);
    }

    public void setStudThesis(String studThesis) {
        put(StudThesis, studThesis);
    }

    public String getStudUid() {
        return getString(StudUid);
    }

    public void setStudUid(String studUid) {
        put(StudUid, studUid);
    }

    public String getStudFName() {
        return getString(StudFName);
    }

    public void setStudFName(String studfName) {
        put(StudFName, studfName);
    }

    public String getStudLName() {
        return getString(StudLName);
    }

    public void setStudLName(String studlName) {
        put(StudLName, studlName);
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
