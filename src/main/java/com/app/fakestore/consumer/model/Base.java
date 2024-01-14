package com.app.fakestore.consumer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * @author Ashwani Kumar
 * Created on 13/01/24.
 */

@Getter
@Setter
@ToString
@MappedSuperclass
public class Base {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;
    @Column(name = "CREATE_TIMESTAMP")
    @JsonIgnore
    private String createTimeStamp;
    @Column(name = "UPDATE_TIMESTAMP")
    @JsonIgnore
    private String updateTimeStamp;
    @Column(name = "IS_DELETED")
    @JsonIgnore
    private boolean isDeleted;
}
