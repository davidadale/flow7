package users;


import com.google.inject.Inject;
import content.CurrentDomain;
import content.filters.DomainFilter;
import content.models.Domain;
import ninja.FilterWith;
import ninja.Result;
import ninja.Results;
import ninja.params.PathParam;
import users.dao.UserDao;
import users.models.User;

import java.util.List;

@FilterWith( DomainFilter.class )
public class UserController {

    @Inject
    UserDao userDao;

    public Result list(){
        Domain domain = CurrentDomain.get();
        List<User> users = userDao.retrieveUsers( domain );
        return Results.html().template("users/views/list.ftl.html").render( "users", users );
    }

    public Result editUser( @PathParam("id") Long id ){
        User user = userDao.retrieve( id );
        return null;
    }

    public Result saveUser(@PathParam("id") Long id){
        User user = userDao.retrieve( id );
        // update user
        userDao.save( user );
        return null;
    }

    public Result deleteUser(){
        return null;
    }




}
