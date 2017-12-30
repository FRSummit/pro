package com.frsummit.hr2.model;

import javax.persistence.*;

@Entity
@Table(name = "payroll")
public class Payroll {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pay_id")
    private int payId;

//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "user_id")
//    //@JoinTable(name = "user_payroll", joinColumns = @JoinColumn(name = "pay_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
//    private User user;

    @Column(name = "user_email")
    private String payUserEmail;

    @Column(name = "basic")
    private double basicPay;

    @Column(name = "medical")
    private double madicalPay;

    @Column(name = "hra")
    private double hraPay;

    @Column(name = "ta")
    private double taPay;

    @Column(name = "da")
    private double daPay;

    @Column(name = "incentive")
    private double incentivePay;

    public Payroll() {
    }

    public Payroll(String payUserEmail, double basicPay, double madicalPay, double hraPay, double taPay, double daPay, double incentivePay) {
        this.payUserEmail = payUserEmail;
        this.basicPay = basicPay;
        this.madicalPay = madicalPay;
        this.hraPay = hraPay;
        this.taPay = taPay;
        this.daPay = daPay;
        this.incentivePay = incentivePay;
    }

    public Payroll(double basicPay, double madicalPay, double hraPay, double taPay, double daPay, double incentivePay) {
        this.basicPay = basicPay;
        this.madicalPay = madicalPay;
        this.hraPay = hraPay;
        this.taPay = taPay;
        this.daPay = daPay;
        this.incentivePay = incentivePay;
    }

    public int getPayId() {
        return payId;
    }

    public void setPayId(int payId) {
        this.payId = payId;
    }

//    public User getUserId() {
//        return user;
//    }
//
//    public void setUserId(User user) {
//        this.user = user;
//    }


    public String getPayUserEmail() {
        return payUserEmail;
    }

    public void setPayUserEmail(String payUserEmail) {
        this.payUserEmail = payUserEmail;
    }

    public double getBasicPay() {
        return basicPay;
    }

    public void setBasicPay(double basicPay) {
        this.basicPay = basicPay;
    }

    public double getMadicalPay() {
        return madicalPay;
    }

    public void setMadicalPay(double madicalPay) {
        this.madicalPay = madicalPay;
    }

    public double getHraPay() {
        return hraPay;
    }

    public void setHraPay(double hraPay) {
        this.hraPay = hraPay;
    }

    public double getTaPay() {
        return taPay;
    }

    public void setTaPay(double taPay) {
        this.taPay = taPay;
    }

    public double getDaPay() {
        return daPay;
    }

    public void setDaPay(double daPay) {
        this.daPay = daPay;
    }

    public double getIncentivePay() {
        return incentivePay;
    }

    public void setIncentivePay(double incentivePay) {
        this.incentivePay = incentivePay;
    }
}
