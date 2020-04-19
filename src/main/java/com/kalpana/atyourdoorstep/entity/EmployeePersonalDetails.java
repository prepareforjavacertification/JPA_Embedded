
package com.kalpana.atyourdoorstep.entity;

import java.io.Serializable;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
@Access(AccessType.FIELD)
public class EmployeePersonalDetails implements Serializable {
    
    @Column(name = "first_name")
    private String firstName;
    
    @Column(name = "last_name")
    private String lastName;
    
    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;
    
    @Column(name = "middle_name", nullable = true)
    private String middleName;
    
    @Column(name = "full_name")
    private transient  String fullName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    @Access(AccessType.PROPERTY)
    public String getFullName() {
        middleName = middleName == null?"":middleName;
        String title = gender==Gender.FEMALE ?"Ms.":gender == Gender.MALE ? "Mr." :"";
        setFullName(String.join(" ", title, firstName,middleName,lastName));
        return fullName;
     }

    private void setFullName(String fullName) {
        this.fullName = fullName;
    }
    
    
    
}
