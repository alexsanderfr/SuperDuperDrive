package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.models.forms.CredentialForm;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialService {
    private final CredentialMapper credentialMapper;

    public CredentialService(CredentialMapper credentialMapper) {
        this.credentialMapper = credentialMapper;
    }

    public void insetCredential(CredentialForm credentialForm) {
        Credential credential = new Credential();
        credential.setUrl(credentialForm.getUrl());
        credential.setUsername(credentialForm.getUsername());
        credential.setPassword(credentialForm.getPassword());
        credential.setUserId(credentialForm.getUserId());
        credentialMapper.insertCredential(credential);
    }

    public Credential getCredential(String username) {
        return credentialMapper.selectCredential(username);
    }

    public List<Credential> getCredentials() {
        return credentialMapper.selectAllCredentials();
    }
}
