package com.example.banking.model;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "customers")
@SQLDelete(sql = "UPDATE banking.customers SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Customer extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "full_name", nullable = false)
    @Pattern(regexp = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$", message = "Full name is not valid")
    private String fullName;
    @Column(name = "email", nullable = false)
    @Pattern(regexp="^[\\w]+@([\\w-]+\\.)+[\\w-]{2,6}$", message = "Email address is not valid")
    private String email;
    @Column(name = "phone", nullable = false)
    @Pattern(regexp = "^0[3798][0-9]{8}", message = "Phone number is not valid")
    private String phone;
    @Column(name = "address", nullable = false)
    @NotEmpty(message = "Address cannot be blank")
    private String address;
    private boolean deleted = Boolean.FALSE;
    @Column(precision = 10, scale = 0, nullable = false, columnDefinition = "decimal(12,0) default 0")
    private BigDecimal balance;
    @OneToMany(targetEntity = Deposit.class)
    private List<Deposit> deposits;

    @OneToMany(targetEntity = Transfer.class)
    private List<Transfer> senders;

    @OneToMany(targetEntity = Transfer.class)
    private List<Transfer> recipients;
    public Customer() {
    }

    public Customer(long id, String fullName, String email, String phone, String address, BigDecimal balance) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.balance = balance;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

}
