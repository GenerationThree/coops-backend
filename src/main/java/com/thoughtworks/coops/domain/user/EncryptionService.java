package com.thoughtworks.coops.domain.user;

public interface EncryptionService {
    String encrypt(String password);
    boolean check(String checkPassword, String realPassword);
}
