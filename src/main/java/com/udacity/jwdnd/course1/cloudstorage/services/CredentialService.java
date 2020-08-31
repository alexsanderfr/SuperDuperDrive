package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.models.forms.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.models.forms.CredentialForm;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialService {
    private final CredentialMapper credentialMapper;

    public CredentialService(CredentialMapper credentialMapper) {
        this.credentialMapper = credentialMapper;
    }

    public Credential getCredential(String username) {
        return credentialMapper.selectCredential(username);
    }

    public List<Credential> getCredentialsFromUser(Integer userId) {
        return credentialMapper.selectCredentialsFromUser(userId);
    }

    public List<Credential> getAllCredentials() {
        return credentialMapper.selectAllCredentials();
    }

    public void insetCredential(CredentialForm credentialForm) {
        Credential credential = new Credential();
        credential.setUrl(credentialForm.getUrl());
        credential.setUsername(credentialForm.getUsername());
        credential.setPassword(credentialForm.getPassword());
        credential.setUserId(credentialForm.getUserId());
        credentialMapper.insertCredential(credential);
    }

    public void updateCredential(Credential credential, CredentialForm credentialForm) {
        credential.setUrl(credentialForm.getUrl());
        credential.setUsername(credentialForm.getUsername());
        credential.setPassword(credentialForm.getPassword());
        credential.setUserId(credentialForm.getUserId());
        credentialMapper.insertCredential(credential);
    }

    public Integer deleteCredential(Credential credential) {
        Integer credentialId = credential.getCredentialId();
        return credentialMapper.deleteCredential(credentialId);
    }
}
