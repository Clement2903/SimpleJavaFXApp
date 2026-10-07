package com.example.customermanager;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Customer {
    private final StringProperty name;
    private final StringProperty province;

    public Customer(String name, String province) {
        this.name = new SimpleStringProperty(name);
        this.province = new SimpleStringProperty(province);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getProvince() {
        return province.get();
    }

    public StringProperty provinceProperty() {
        return province;
    }

    public void setProvince(String province) {
        this.province.set(province);
    }
}