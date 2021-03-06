package br.com.brunadelmouro.cursospringboot.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Entity
public class Request implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date date;

    @OneToOne(cascade = CascadeType.ALL, mappedBy="request")
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "address_delivery_id")
    private Address deliveryAddress;

    @OneToMany(mappedBy = "id.request")
    private Set<RequestItem> items = new HashSet<>();

    public Request() {
    }

    public Request(Integer id, Date date, Customer customer, Address deliveryAddress) {
        super();
        this.id = id;
        this.date = date;
        this.customer = customer;
        this.deliveryAddress = deliveryAddress;
    }

    //total
    public double getTotalPrice(){
        double sum = 0.0;

        for (RequestItem item :items) {
            sum = sum + item.getSubTotal();
        }

        return sum;
    }

    public Set<RequestItem> getItems() {
        return items;
    }

    public void setItems(Set<RequestItem> items) {
        this.items = items;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(Address deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return Objects.equals(id, request.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        final StringBuilder sb = new StringBuilder();

        sb.append("Order number: ");
        sb.append(getId());
        sb.append(", Instant: ");
        sb.append(sdf.format(getDate()));
        sb.append(", Customer: ");
        sb.append(getCustomer().getName());
        sb.append(", Payment status: ");
        sb.append(getPayment().getStatus().getDescription());

        sb.append("\nDetails: \n");
        for (RequestItem ri: this.getItems()) {
            sb.append(ri.toString());
        }

        sb.append("Total price: ");
        sb.append(nf.format(getTotalPrice()));

        return sb.toString();
    }
}
