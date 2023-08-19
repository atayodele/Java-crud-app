package net.javaguides.sprintboot.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employees")
public class Employee {
    public Employee(String first_name, String last_name, String emailId){
        firstName = first_name;
        lastName = last_name;
        email = emailId;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;

    public void setFirstName(String firstname) {
        this.firstName = firstname;
    }
    public void setLastName(String lastname) {
        this.lastName = lastname;
    }
    public void setEmail(String emailid) {
        this.email = emailid;
    }
}
