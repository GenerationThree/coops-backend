package com.thoughtworks.coops.api;

import com.thoughtworks.coops.domain.user.User;
import com.thoughtworks.coops.domain.user.UserRepository;
import com.thoughtworks.coops.support.ApiSupport;
import com.thoughtworks.coops.support.ApiTestRunner;
import com.thoughtworks.coops.support.TestHelper;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(ApiTestRunner.class)
public class UsersApiTest extends ApiSupport {
    @Inject
    UserRepository userRepository;

    @Test
    public void should_import_user_success() throws Exception {
        Map<String, Object> userInfo = new HashMap<String, Object>() {{
            put("id", "123");
            put("name", "scxu");
            put("phone", "13800000000");
            put("email", "scxu@thoughtworks.com");
            put("password", "123");
            put("key_id", "1");
        }};

        final Response post = post("/users", userInfo);
        assertThat(post.getStatus(), is(201));
    }

    @Test
    public void should_400_if_id_not_valid() throws Exception {
        Map<String, Object> userInfo = new HashMap<String, Object>() {{
            put("email", "scxu@thoughtworks.com");
            put("name", "Xu Shanchuan");
        }};

        final Response post = post("/users", userInfo);
        assertThat(post.getStatus(), is(400));
    }

    @Test
    public void should_400_if_email_not_valid() throws Exception {
        Map<String, Object> userInfo = new HashMap<String, Object>() {{
            put("id", "123");
            put("name", "Xu Shanchuan");
        }};

        final Response post = post("/users", userInfo);
        assertThat(post.getStatus(), is(400));
    }

    @Test
    public void should_400_when_create_user_if_user_exist() throws Exception {
        final User user = TestHelper.userForTest("123", "scxu");
        userRepository.save(user);

        Map<String, Object> userInfo = new HashMap<String, Object>() {{
            put("id", "123");
            put("name", "scxu");
            put("email", "scxu@tw.com");
        }};

        final Response post = post("/users", userInfo);
        assertThat(post.getStatus(), is(400));
    }

    @Test
    public void should_get_user_by_id() throws Exception {
        final User user = TestHelper.userForTest("123", "scxu");
        userRepository.save(user);

        final Response response = get("/users/" + user.getUserId().id());
        assertThat(response.getStatus(), is(200));
        final Map userMap = response.readEntity(Map.class);
        assertThat(userMap.get("id"), is(user.getUserId().id()));
        assertThat(userMap.get("name"), is(user.getName()));
        assertThat(userMap.get("email"), is(user.getEmail()));
        List urls = (List) userMap.get("links");
        assertThat(urls.size(), is(1));
        assertThat(canFindLink(urls, "self", "/users/123"), is(true));
    }

    @Test
    public void should_404_if_user_not_exist() throws Exception {
        final Response response = get("/users/abc");
        assertThat(response.getStatus(), is(404));
    }
}
