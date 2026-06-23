package org.example.productcatalogservice.TableInheritanceExamples.joined;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity(name="jc_mentors")

public class Mentor extends User{
    private Double rating;
}
