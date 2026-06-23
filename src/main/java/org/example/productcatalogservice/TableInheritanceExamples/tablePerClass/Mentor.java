package org.example.productcatalogservice.TableInheritanceExamples.tablePerClass;

import jakarta.persistence.Entity;

@Entity(name="tpc_mentors")
public class Mentor extends User{
    private Double ratings;
}
