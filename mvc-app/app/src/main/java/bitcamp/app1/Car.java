package bitcamp.app1;

import java.sql.Date;

public class Car {
  String model;
  String maker;
  int capacity;
  boolean auto;
  Date createdDate;
  Engine engine;

  @Override
  public String toString() {
    return "Car{" +
        "model='" + model + '\'' +
        ", maker='" + maker + '\'' +
        ", capacity=" + capacity +
        ", auto=" + auto +
        ", createdDate=" + createdDate +
        ", engine=" + engine +
        '}';
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public Engine getEngine() {
    return engine;
  }

  public void setEngine(Engine engine) {
    this.engine = engine;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public String getMaker() {
    return maker;
  }

  public void setMaker(String maker) {
    this.maker = maker;
  }

  public int getCapacity() {
    return capacity;
  }

  public void setCapacity(int capacity) {
    this.capacity = capacity;
  }

  public boolean isAuto() {
    return auto;
  }

  public void setAuto(boolean auto) {
    this.auto = auto;
  }
}
