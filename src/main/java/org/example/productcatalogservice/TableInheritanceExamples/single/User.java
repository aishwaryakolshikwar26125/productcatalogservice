package org.example.productcatalogservice.TableInheritanceExamples.single;

import jakarta.persistence.*;

@Entity(name="sc_users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING)
public class User {
    @Id
//    @Column(name="user_id")
    private Long id;
    private String name;
}
