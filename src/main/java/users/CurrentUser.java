package users;

import users.models.User;

public class CurrentUser {

    //static final ThreadLocal<List<Role>> CURRENT_ROLES_HOLDER = new ThreadLocal<>();
    static final ThreadLocal<User> USER_HOLDER = new ThreadLocal<>();

    public static User get(){
        return USER_HOLDER.get();
    }

    public static void set(User user ){
        USER_HOLDER.set( user );
    }

    public static void reset(){
        USER_HOLDER.remove();
    }

}
