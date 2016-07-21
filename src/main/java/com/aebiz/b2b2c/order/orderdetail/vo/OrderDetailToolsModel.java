package com.aebiz.b2b2c.order.orderdetail.vo;

public class OrderDetailToolsModel {

  /*厨房*/
  private int kitchen;
  
  /*卫生间*/
  private int toilet;
  
  /*其他房间*/
  private int otherRooms;
  
  /*抽油烟机*/
  private int kitchenVentilator;
  
  /*冰箱*/
  private int freezer;

public int getKitchen() {
	return kitchen;
}

public void setKitchen(int kitchen) {
	this.kitchen = kitchen;
}

public int getToilet() {
	return toilet;
}

public void setToilet(int toilet) {
	this.toilet = toilet;
}

public int getOtherRooms() {
	return otherRooms;
}

public void setOtherRooms(int otherRooms) {
	this.otherRooms = otherRooms;
}

public int getKitchenVentilator() {
	return kitchenVentilator;
}

public void setKitchenVentilator(int kitchenVentilator) {
	this.kitchenVentilator = kitchenVentilator;
}

public int getFreezer() {
	return freezer;
}

public void setFreezer(int freezer) {
	this.freezer = freezer;
}


  
}
