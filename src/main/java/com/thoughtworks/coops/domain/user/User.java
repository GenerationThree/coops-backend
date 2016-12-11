package com.thoughtworks.coops.domain.user;

import com.thoughtworks.coops.domain.AssertionConcern;
import com.thoughtworks.coops.infrastructure.records.Record;
import com.thoughtworks.coops.api.jersey.Routes;
import org.joda.time.DateTime;

import java.util.HashMap;
import java.util.Map;

import static java.util.Arrays.asList;

public class User extends AssertionConcern implements Record {
    private UserId userId;
    private String name;
    private String phone;
    private String email;
    private String password;
    private String key_id;
    private DateTime created_at;

    public User(UserId id, String name, String phone, String email, String password, String keyId) {
        setUserId(id);
        setName(name);
        setPhone(phone);
        setEmail(email);
        setPassword(password);
        setKeyId(keyId);
    }

    private User() {

    }

    public UserId getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    private void setUserId(UserId userId) {
        if (userId == null) {
            throw new IllegalArgumentException();
        }
        this.userId = userId;
    }

    private void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.name = name;
    }

    private void setPhone(String phone) {
        if (phone == null || phone.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.phone = phone;
    }


    private void setEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.email = email;
    }

    private void setKeyId(String keyId) {
        if (keyId == null || keyId.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.key_id = keyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        return getUserId().equals(user.getUserId());

    }

    @Override
    public int hashCode() {
        return getUserId().hashCode();
    }

    @Override
    public Map<String, Object> toRefJson(Routes routes) {
        return new HashMap<String, Object>() {{
            put("id", getUserId().id());
            put("name", getName());
            put("email", getEmail());
            put("links", asList(
                    new HashMap<String, Object>() {{
                        put("rel", "self");
                        put("uri", routes.userUrl(User.this));
                    }}
            ));
        }};
    }

    @Override
    public Map<String, Object> toJson(Routes routes) {
        return toRefJson(routes);
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
