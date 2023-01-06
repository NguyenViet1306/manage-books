package com.bezkoder.springjwt.models.DTO;

public class ChangePassword {
    private String passwordOld;
    private String passwordNew;

    public ChangePassword(String passwordOld, String passwordNew) {
        this.passwordOld = passwordOld;
        this.passwordNew = passwordNew;
    }

    public ChangePassword() {
    }

    public String getPasswordOld() {
        return passwordOld;
    }

    public void setPasswordOld(String passwordOld) {
        this.passwordOld = passwordOld;
    }

    public String getPasswordNew() {
        return passwordNew;
    }

    public void setPasswordNew(String passwordNew) {
        this.passwordNew = passwordNew;
    }

}
