package com.app.fakestore.consumer.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

/**
 * @author Ashwani Kumar
 * Created on 04/01/24.
 */

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Category extends Base {
    @Column(name = "NAME")
    private String name;

}
