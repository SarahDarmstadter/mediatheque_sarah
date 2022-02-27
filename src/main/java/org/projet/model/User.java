package org.projet.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Entity
@Table(name= "USERS")
@Data
public class User {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUser;
	
	@OneToMany(targetEntity = Loan.class, mappedBy= "User")
	@JoinColumn(name="idLoan")
	private Set <Loan> userLoans = new HashSet<>();
	
	
	
	
	@Column(name="EMAIL", unique =true)
	@NotNull
	private String email;
	
	@Column(name="PASSWORD")
	@NotNull
	@Pattern(regexp ="/^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[-+!*$@%_])([-+!*$@%_\\w]{8,15})$/gm;\r\n" + "")
	private String password;
	
	@Column(name="LASTNAME")
	@NotNull
	private String lastName;
	
	@Column(name="FIRST")
	@NotNull
	private String firstName;
	
	@OneToMany(cascade=CascadeType.ALL,orphanRemoval=true)
	@JoinColumn(name="reservation_id")
	private Loan loan;
	
	@Column(name="PENDING_LOANS")
	@Min(0)
	@Max(3)
	private int pendingLoan;

}
