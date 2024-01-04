package ua.kiev.prog;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="Contacts")
@JsonIgnoreProperties("contacts")
public class Contact {
    @Id
    @GeneratedValue
    private Long id;



    @ManyToOne
    @JoinColumn(name="group_id")
    @JsonBackReference
    private Group group;

    private String name;
    private String surname;
    private String phone;
    private String email;

    public Contact() {}

    public Contact(Group group, String name, String surname, String phone, String email) {
        this.group = group;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", group=" + group +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
