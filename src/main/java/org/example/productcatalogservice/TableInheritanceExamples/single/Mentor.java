package org.example.productcatalogservice.TableInheritanceExamples.single;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;


@Entity(name="sc_mentors")
@DiscriminatorValue(value="mentor")
public class Mentor extends User{
    private Double rating;
}
