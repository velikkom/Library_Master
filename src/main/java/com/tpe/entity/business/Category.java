package com.tpe.entity.business;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Category {

   /* @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) //TODO min:2 max:80
    private String name;

    @Column(nullable = false) //TODO false
    private Boolean builtIn;

    @Column(nullable = false)//TODO default deger one more than the largest number in sequence fields
    private int sequence;

    @OneToMany(mappedBy = "category")//TODO kategori silindiğinde kitaplara ne olabilir sorabilirsin
    private List<Book> bookList;
*/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 80)
    private String name;

    @Column(nullable = false)
    private boolean builtIn = false;

    @Column(nullable = false)
    private int sequence;

    @OneToMany(mappedBy = "category")
    private Set<Book> books;



}
