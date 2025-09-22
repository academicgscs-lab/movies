package org.example.handler.xml.model;

import jakarta.xml.bind.annotation.*;

@XmlRootElement(name = "Movie")
@XmlAccessorType(XmlAccessType.FIELD)
public class XMovie {
    @XmlElement(name = "name")
    public String _name;

    @XmlElement(name = "priceCode")
    public double priceCode;

    public XMovie() {}

    public XMovie(String name, double priceCode) {
        this._name = name;
        this.priceCode = priceCode;
    }

    public String getName() {
        return _name;
    }

    public void setName(String _name) {
        this._name = _name;
    }

    public double getPriceCode() {
        return priceCode;
    }

    public void setPriceCode(double priceCode) {
        this.priceCode = priceCode;
    }
}
