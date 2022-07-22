package com.shop.order.exception;

public class OrderNotFound extends Exception{
    private static final long serialVersionUID = 1L;

	public OrderNotFound(String msg){
        super(msg);
    }
}
