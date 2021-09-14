package br.com.brunadelmouro.cursospringboot.domain;

import br.com.brunadelmouro.cursospringboot.domain.enums.StatusPayment;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Payment implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;
    private StatusPayment status;

    @OneToOne
    @JoinColumn(name="request_id") //id payment = id request
    @MapsId
    private Request request;

    public Payment() {
    }

    public Payment(Integer id, StatusPayment status, Request request) {
        this.id = id;
        this.status = status;
        this.request = request;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public StatusPayment getStatus() {
        return status;
    }

    public void setStatus(StatusPayment status) {
        this.status = status;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Objects.equals(id, payment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
