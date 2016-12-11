package com.thoughtworks.coops.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CreateUserRequestBean {
    @JsonProperty
    private String id;
    @JsonProperty
    private String name;
    @JsonProperty
    private String phone;
    @JsonProperty
    private String email;
    @JsonProperty
    private String password;
    @JsonProperty
    private String key_id;
    @JsonProperty
    private String created_at;

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getKey_id() {
        return key_id;
    }

    public String getCreated_at() {
        return created_at;
    }
}
