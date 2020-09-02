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
    private final EncryptionService encryptionService;

    public CredentialService(CredentialMapper credentialMapper, EncryptionService encryptionService) {
        this.credentialMapper = credentialMapper;
        this.encryptionService = encryptionService;
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

    public void insertCredential(CredentialForm credentialForm) {
        Credential credential = new Credential();
        createCredentialFromForm(credentialForm, credential);
        credentialMapper.insertCredential(credential);
    }

    public void updateCredential(CredentialForm credentialForm) {
        Credential credential = new Credential();
        credential.setCredentialId(credentialForm.getCredentialId());
        createCredentialFromForm(credentialForm, credential);
        credentialMapper.updateCredential(credential);
    }

    private void createCredentialFromForm(CredentialForm credentialForm, Credential credential) {
        credential.setUrl(credentialForm.getUrl());
        credential.setUsername(credentialForm.getUsername());
        String key = encryptionService.makeKey();
        String encryptedPassword = encryptionService.encryptValue(credentialForm.getPassword(), key);
        credential.setKey(key);
        credential.setPassword(encryptedPassword);
        credential.setUserId(credentialForm.getUserId());
    }

    public Integer deleteCredential(Integer credentialId) {
        return credentialMapper.deleteCredential(credentialId);
    }
}
