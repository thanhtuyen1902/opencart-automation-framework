package com.opencart.automation.models;


//import lombok.Builder;
//import lombok.Getter;
//
//@Getter
//@Builder
public class User {

    private String firstName;
    private String lastName;
    private String telephone;
    private String email;
    private String password;
    private String confirmPassword;

    //Getter & setter
    // Builder Pattern
    // Lombok generates Builder pattern, getters, and constructor at compile time

    //Không dùng lombok
//    // Private constructor để dùng với Builder
//    private User(Builder builder) {
//        this.firstName = builder.firstName;
//        this.lastName = builder.lastName;
//        this.email = builder.email;
//        this.telephone = builder.telephone;
//        this.password = builder.password;
//        this.confirmPassword = builder.confirmPassword;
//    }

    // Getters & setters
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() { return email; }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public String getPassword() { return password; }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getConfirmPassword() { return confirmPassword; }
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
//    // Builder Pattern
//    public static class Builder {
//        private String firstName;
//        private String lastName;
//        private String email;
//        private String telephone;
//        private String password;
//        public String confirmPassword;
//
//        public Builder firstName(String firstName) {
//            this.firstName = firstName;
//            return this;
//        }
//
//        public Builder lastName(String lastName) {
//            this.lastName = lastName;
//            return this;
//        }
//
//        public Builder email(String email) {
//            this.email = email;
//            return this;
//        }
//
//        public Builder telephone(String telephone) {
//            this.telephone = telephone;
//            return this;
//        }
//
//        public Builder password(String password) {
//            this.password = password;
//            return this;
//        }
//
//        public User build() {
//            return new User(this);
//        }
//    }
//
//    public static Builder builder() {
//        return new Builder();
//    }

}
