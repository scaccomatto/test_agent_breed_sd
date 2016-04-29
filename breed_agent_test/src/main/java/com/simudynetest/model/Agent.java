package com.simudynetest.model;

public class Agent {
	
	private AgentBreed agentBreed;
	private long policyID;
	private int age;
	private int socialGrade;
	private double paymentAtPurchase;
	private double attributeBrand;	
	private double attributePrice;
	private double attributePromotions;
	private boolean autoRenew;	
	private int inertiaForSwitch;
	
	public Agent(){}
	
	public Agent(AgentBreed agentBreed, long policyID, int age, int socialGrade, double paymentAtPurchase, double attributeBrand, double attributePrice,
			double attributePromotions, boolean autoRenew, int inertiaForSwitch){
		
		this.agentBreed = agentBreed;
		this.policyID = policyID;
		this.age = age;
		this.socialGrade = socialGrade;
		this.paymentAtPurchase = paymentAtPurchase;
		this.attributeBrand = attributeBrand;
		this.attributePrice = attributePrice;
		this.attributePromotions = attributePromotions;
		this.autoRenew = autoRenew;
		this.inertiaForSwitch = inertiaForSwitch;
	}
	
	
	public AgentBreed getAgent_Breed() {
		return agentBreed;
	}
	public void setAgent_Breed(String agentBreed) {
		
		this.agentBreed = AgentBreed.valueOf(agentBreed);
	}
	public long getPolicy_ID() {
		return policyID;
	}
	public void setPolicy_ID(long policyID) {
		this.policyID = policyID;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getSocial_Grade() {
		return socialGrade;
	}
	public void setSocial_Grade(int socialGrade) {
		this.socialGrade = socialGrade;
	}
	public double getPayment_at_Purchase() {
		return paymentAtPurchase;
	}
	public void setPayment_at_Purchase(double paymentAtPurchase) {
		this.paymentAtPurchase = paymentAtPurchase;
	}
	public double getAttribute_Brand() {
		return attributeBrand;
	}
	public void setAttribute_Brand(double attributeBrand) {
		this.attributeBrand = attributeBrand;
	}
	public double getAttribute_Price() {
		return attributePrice;
	}
	public void setAttribute_Price(double attributePrice) {
		this.attributePrice = attributePrice;
	}
	public double getAttribute_Promotions() {
		return attributePromotions;
	}
	public void setAttribute_Promotions(double attributePromotions) {
		this.attributePromotions = attributePromotions;
	}
	public boolean isAuto_Renew() {
		return autoRenew;
	}
	public void setAuto_Renew(boolean autoRenew) {
		this.autoRenew = autoRenew;
	}
	public int getInertia_for_Switch() {
		return inertiaForSwitch;
	}
	public void setInertia_for_Switch(int inertiaForSwitch) {
		this.inertiaForSwitch = inertiaForSwitch;
	}


}
