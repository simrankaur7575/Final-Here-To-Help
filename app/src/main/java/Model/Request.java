package Model;

import java.util.List;

public class Request {
    private String phone;
    private String name;
    private String address;
    private String status;
//    private String total;
    private List<Order> categories; //list of categories

    public Request() {
    }

    public Request(String phone, String name, String address, List<Order> categories) {
        this.phone = phone;
        this.name = name;
        this.address = address;
        this.status = "0"; // Default is 0, 0: Placed, 1: Shipping, 2: Shipped
//        this.total = total;
        this.categories = categories;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

//    public String getTotal() {
//        return total;
//    }
//
//    public void setTotal(String total) {
//        this.total = total;
//    }

    public List<Order> getCategories() {
        return categories;
    }

    public void setCategories(List<Order> categories) {
        this.categories = categories;
    }
}

