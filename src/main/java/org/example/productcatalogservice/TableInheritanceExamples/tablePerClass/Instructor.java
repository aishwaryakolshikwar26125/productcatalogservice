package org.example.productcatalogservice.TableInheritanceExamples.tablePerClass;

import jakarta.persistence.Entity;

@Entity(name="tpc_instructors")
public class Instructor extends User{
    private String company;
}
