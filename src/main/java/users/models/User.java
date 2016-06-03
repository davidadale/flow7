package users.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "account")
public class User {


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public Long id;
    public String username;
    public String password;
    public String fullName;
    public Long domainId;
    public Role role;


    public static User createSystemAdmin(String username, String password, String fullname ){
        User admin = new User();
        admin.username = username;
        admin.password = password;
        admin.fullName = fullname;
        admin.role = Role.SYSTEM_ADMIN;
        return admin;
    }

    public static User createSiteAdmin(String username, String password, String fullname ){
        User admin = new User();
        admin.username = username;
        admin.password = password;
        admin.fullName = fullname;
        admin.role = Role.SITE_ADMIN;
        return admin;
    }

    public static User createSiteUser(String username, String password, String fullname ){
        User user = new User();
        user.username = username;
        user.password = password;
        user.fullName = fullname;
        user.role = Role.SITE_USER;
        return user;
    }

    public static User createGuest(String username, String password, String fullname ){
        User guest = new User();
        guest.username = username;
        guest.password = password;
        guest.fullName = fullname;
        guest.role = Role.GUEST;
        return guest;
    }

}
