package com.medical.exam.bean.po;

import com.medical.exam.bean.BaseEntity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author xingfudeshi@gmail.com
 * @date 2019/07/09
 */
@Table(name = "sys_commodity")
public class Commodity extends BaseEntity {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long commodity_id;
    private String title;
    private BigDecimal price;

    public Long getCommodity_id() {
        return commodity_id;
    }

    public Commodity setCommodity_id(Long commodity_id) {
        this.commodity_id = commodity_id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Commodity setTitle(String title) {
        this.title = title;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Commodity setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    @Override
    public String toString() {
        return "Commodity{" +
            "commodity_id=" + commodity_id +
            ", title='" + title + '\'' +
            ", price=" + price +
            "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Commodity)) return false;
        Commodity commodity = (Commodity) o;
        return commodity_id.equals(commodity.commodity_id) &&
            title.equals(commodity.title) &&
            price.equals(commodity.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commodity_id, title, price);
    }
}
