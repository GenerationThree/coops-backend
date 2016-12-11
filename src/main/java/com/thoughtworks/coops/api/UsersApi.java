package com.thoughtworks.coops.api;

import com.thoughtworks.coops.domain.user.*;
import com.thoughtworks.coops.api.jersey.Routes;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("users")
public class UsersApi {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(CreateUserRequestBean info,
                               @Context UserRepository userRepository,
                               @Context Routes routes,
                               @Context EncryptionService encryptionService) {
        if (userRepository.ofId(new UserId(info.getId())).isPresent()) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        User user = new User(
                new UserId(info.getId()),
                info.getName(),
                info.getPhone(),
                info.getEmail(),
                encryptionService.encrypt(info.getPassword()),
                info.getKey_id());
        userRepository.save(user);
        return Response.created(routes.userUrl(user)).build();
    }

    @Path("{userId}")
    public UserApi getUser(@PathParam("userId") String userId,
                           @Context UserRepository userRepository) {
        return userRepository.ofId(new UserId(userId))
                .map(UserApi::new)
                .orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND));
    }
}
