package org.example.productcatalogservice.TableInheritanceExamples.joined;

import jakarta.persistence.*;

@Entity(name="jc_users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
//    @Column(name="user_id")
    private Long id;
    private String name;
}
