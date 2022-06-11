package com.shop.order.model;



import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "order")
public class Order {

	@Transient
	public static final String SEQUENCE_NAME = "users_sequence";
	
    @Id
	private int id;
    
	private String orderDate;
	private int customerId;
	private Double amountPaid;
	private String modeOfPayment;
	private String orderStatus;
	private int quantity;
	private Address address;
	private Product product;

    public Order(int id, String orderDate, int customerId, Double amountPaid, String modeOfPayment, String orderStatus,
			int quantity, Address address, Product product) {
		this.id = id;
		this.orderDate = orderDate;
		this.customerId = customerId;
		this.amountPaid = amountPaid;
		this.modeOfPayment = modeOfPayment;
		this.orderStatus = orderStatus;
		this.quantity = quantity;
		this.address = address;
		this.product = product;
	}

	public Order(){

    }

	public static String getSequenceName() {
		return SEQUENCE_NAME;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public Double getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(Double amountPaid) {
		this.amountPaid = amountPaid;
	}

	public String getModeOfPayment() {
		return modeOfPayment;
	}

	public void setModeOfPayment(String modeOfPayment) {
		this.modeOfPayment = modeOfPayment;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "Order [address=" + address + ", amountPaid=" + amountPaid + ", customerId=" + customerId + ", id=" + id
				+ ", modeOfPayment=" + modeOfPayment + ", orderDate=" + orderDate + ", orderStatus=" + orderStatus
				+ ", product=" + product + ", quantity=" + quantity + "]";
	}

	
}
