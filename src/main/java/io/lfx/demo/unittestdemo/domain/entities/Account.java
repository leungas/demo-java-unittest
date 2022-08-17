package io.lfx.demo.unittestdemo.domain.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "account")
@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Builder
public class Account {  

    
    @Id
    @GeneratedValue
    private long id;

    @Column
    private String name;

    @Column
    @Builder.Default
    private Boolean enabled = true;

    @CreationTimestamp
    private Date createdOn;
    
    @UpdateTimestamp
    private Date lastUpdatedOn;

}
