package models;


import com.avaje.ebean.validation.Length;
import com.avaje.ebean.validation.NotNull;
import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.List;


@Entity
public class User extends Model {
    /**
     *
     */
    private static final long serialVersionUID = 1L;


    @Id
    @Constraints.Email
    @Constraints.Required
    public String email;

    @NotNull
    @Length(min = 7, max = 30)
    // Using only ebean validators causes Play not to validate
    // those constraints.
    @Constraints.Required
    @Constraints.MinLength(value = 7)
    @Constraints.MaxLength(value = 30)
    public String name;

    @Length(max = 30)
    @Constraints.MaxLength(value = 30)
    public String company;

    @NotNull
    @Length(max = 30)
    @Constraints.Required
    @Constraints.MaxLength(value = 30)
    public String phoneNumber;

    @Length(max = 50)
    @Constraints.MaxLength(value = 50)
    public String street;

    @Length(max = 8)
    @Constraints.MaxLength(value = 8)
    public String postalCode;

    @Length(max = 30)
    @Constraints.MaxLength(value = 30)
    public String city;


    public String gpsLatPos;

    @NotNull
    @Length(min = 6)
    @Constraints.Required
    @Constraints.MinLength(value = 6)
    public String password = String.valueOf(System.nanoTime());

    @NotNull
    @Constraints.Required
    public boolean active = false;

    @NotNull
    @Constraints.Required
    public boolean powerUser = false;

    public Timestamp registryDate;

    public Timestamp activeDate;

    public Timestamp lastStatusDate;

    public String comments;

//-- Queries

    public static Model.Finder<String, User> find = new Model.Finder<String, User>(String.class, User.class);

    /**
     * Retrieve all users.
     */
    public static List<User> findAll() {
        return find.all();
    }

    /**
     * Retrieve a User from email.
     */
    public static User findByEmail(String email) {
        return find.where().eq("email", email).findUnique();
    }

    public static User authenticate(String email, String password) {
            return find.where()
                    .eq("email", email)
                    .eq("password", password)
                    .eq("active", true)
                    .findUnique();
    }
    
    // --

    public String toString() {
        return "User(" + email + ")";
    }
}