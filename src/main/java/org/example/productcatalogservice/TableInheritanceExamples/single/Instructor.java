package org.example.productcatalogservice.TableInheritanceExamples.single;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity(name="sc_instructors")
//@PrimaryKeyJoinColumn(name="user_id")
@DiscriminatorValue(value="instructor")
public class Instructor extends User {
    private String company;
}
