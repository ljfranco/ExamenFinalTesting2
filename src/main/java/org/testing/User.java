package org.testing;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Random;

@Getter
@Setter
public class User {
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private String phoneNumber;
    private String ssn;
   // private String username;



    public User(String firstName, String lastName, String address, String city, String state, String zipCode, String phoneNumber, String ssn) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.phoneNumber = phoneNumber;
        this.ssn = ssn;
        //this.username = firstName + lastName;
    }


    public User() {
    }

    /**
     *Metodo para obtener un usuario Random de un archivo JSON
     * @return User
     */
    public static User randomUser() {
        ObjectMapper objectMapper = new ObjectMapper();
        try (InputStream inputStream = User.class.getClassLoader().getResourceAsStream("usersData.json")) {
            List<User> users = objectMapper.readValue(inputStream, new TypeReference<List<User>>() {});
            return users.get(new Random().nextInt(users.size()));

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     *Metodo para obtener el primer usuario de un archivo JSON
     * @return User
     */
    public static User firstUser() {
        ObjectMapper objectMapper = new ObjectMapper();
        try (InputStream inputStream = User.class.getClassLoader().getResourceAsStream("usersData.json")) {
            List<User> users = objectMapper.readValue(inputStream, new TypeReference<List<User>>() {
            });
            return users.get(0);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
