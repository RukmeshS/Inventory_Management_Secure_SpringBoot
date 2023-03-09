package com.ty.ims.inventory_prject_boot.dto;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer implements UserDetails{
	@Id
	@GenericGenerator(name = "customer_Id", strategy = "com.ty.ims.inventory_prject_boot.util.Customer_IdGenerator")
	@GeneratedValue(generator = "customer_Id")
	@Column(name = "customer_Id")
	private String customerId;
	@NotNull
	private String customerName;
	@NotNull
	private String customerEmail;
	@NotNull
	private String customerPassword;
	@NotNull
	private long customerPhoneNo;
	
	private Date outwardDate;
	
	private int outwardQuantity;
	
	@Column
	@Enumerated(EnumType.STRING)
	@ElementCollection(fetch = FetchType.EAGER)
	private Set<Role> role = new HashSet<>();
	
	@Column
	@Builder.Default
	private boolean enabled= false;
	
	
	
//	@JsonIgnore
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(joinColumns = @JoinColumn, inverseJoinColumns = @JoinColumn)
	private List<Item> item;

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public long getCustomerPhoneNo() {
		return customerPhoneNo;
	}

	public void setCustomerPhoneNo(long customerPhoneNo) {
		this.customerPhoneNo = customerPhoneNo;
	}

	public Date getOutwardDate() {
		return outwardDate;
	}

	public void setOutwardDate(Date outwardDate) {
		this.outwardDate = outwardDate;
	}

	public int getOutwardQuantity() {
		return outwardQuantity;
	}

	public void setOutwardQuantity(int outwardQuantity) {
		this.outwardQuantity = outwardQuantity;
	}

	public List<Item> getItem() {
		return item;
	}

	public void setItem(List<Item> item) {
		this.item = item;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		Set<GrantedAuthority> authorities= new HashSet<>();
		
		for (var r : this.role) {
			var sga= new SimpleGrantedAuthority(r.name());
			authorities.add(sga);
		}
		
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

//	public Customer(String customerEmail2, @NotNull String customerPassword2, boolean enabled2, boolean b, boolean c,
//			boolean d, Collection<? extends GrantedAuthority> authorities) {
//		// TODO Auto-generated constructor stub
//	}

}
