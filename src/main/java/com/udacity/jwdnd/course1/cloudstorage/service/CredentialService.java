package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.form.CredentialForm;
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

    public Integer insertCredential(CredentialForm credentialForm, Integer userId) {
        boolean usernameAlreadyExists = credentialMapper.selectCredential(credentialForm.getUsername()) != null;
        if (!credentialForm.isValid() || userId == null || usernameAlreadyExists) return -1;
        Credential credential = new Credential();
        credential.setUserId(userId);
        createCredentialFromForm(credentialForm, credential);
        return credentialMapper.insertCredential(credential);

    }

    public Integer updateCredential(CredentialForm credentialForm, Integer userId) {
        if (!credentialForm.isValid() || userId == null) return -1;
        Credential credential = new Credential();
        credential.setCredentialId(credentialForm.getCredentialId());
        credential.setUserId(userId);
        createCredentialFromForm(credentialForm, credential);
        return credentialMapper.updateCredential(credential);
    }

    private void createCredentialFromForm(CredentialForm credentialForm, Credential credential) {
        credential.setUrl(credentialForm.getUrl());
        credential.setUsername(credentialForm.getUsername());
        String key = encryptionService.makeKey();
        String encryptedPassword = encryptionService.encryptValue(credentialForm.getPassword(), key);
        credential.setKey(key);
        credential.setPassword(encryptedPassword);
    }

    public Integer deleteCredential(Integer credentialId) {
        return credentialMapper.deleteCredential(credentialId);
    }
}
