package org.example.productcatalogservice.TableInheritanceExamples.joined;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity(name="jc_instructors")
//@PrimaryKeyJoinColumn(name="user_id")
public class Instructor extends User{
}
