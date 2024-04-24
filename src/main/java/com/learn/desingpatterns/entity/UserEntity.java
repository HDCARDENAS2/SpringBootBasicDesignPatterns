package com.learn.desingpatterns.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "user_app")
public class UserEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6659355453167373930L;
	
    @Id
	@GenericGenerator(name = "USER_GENERATOR_SEQ", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", 
	        parameters = {
			@Parameter(name = "sequence_name", value = "USER_SEQ"), @Parameter(name = "optimizer", value = "none") 
			}
	)
	@GeneratedValue(generator = "USER_GENERATOR_SEQ")
    @Column(name = "id", unique = true, nullable = false)
	private Integer id;
    @Column(name = "name", nullable = false)
	private String name;
    @Column(name = "email", nullable = false)
	private String email;
    @Column(name = "created_at", nullable = false, length = 35)
    private Date createdAt;
    @PrePersist
    public void onPrePersist() {
        this.setCreatedAt(new Date());
    }
}

