package com.udacity.jwdnd.course1.cloudstorage.models.forms;

public class CredentialForm {
    private Integer credentialId;
    private String url;
    private String username;
    private String password;
    private Integer userId;

    public CredentialForm() {
    }

    public CredentialForm(Integer credentialId, String url, String username, String password, Integer userId) {
        this.credentialId = credentialId;
        this.url = url;
        this.username = username;
        this.password = password;
        this.userId = userId;
    }

    public boolean isValid() {
        return url != null && !url.isBlank()
                && username != null && !username.isBlank()
                && password != null && !password.isBlank();
    }

    public Integer getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(Integer credentialId) {
        this.credentialId = credentialId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
