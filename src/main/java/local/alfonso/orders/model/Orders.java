package local.alfonso.orders.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Orders
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ordnum;

    private double ordamount;
    private double advanceamount;
    private String ordersescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "custcode",
            nullable = false)
    @JsonIgnore
    private Customers customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agentcode",
            nullable = false)
    @JsonIgnore
    private Agents agent;

    public Orders()
    {
    }

    public Orders(double ordamount, double advanceamount, Customers customer, Agents agents, String ordersescription)
    {
        this.ordamount = ordamount;
        this.advanceamount = advanceamount;
        this.ordersescription = ordersescription;
        this.customer = customer;
        this.agent = agents;
    }

    public long getOrdnum()
    {
        return ordnum;
    }

    public void setOrdnum(long ordnum)
    {
        this.ordnum = ordnum;
    }

    public double getOrdamount()
    {
        return ordamount;
    }

    public void setOrdamount(double ordamount)
    {
        this.ordamount = ordamount;
    }

    public double getAdvanceamount()
    {
        return advanceamount;
    }

    public void setAdvanceamount(double advanceamount)
    {
        this.advanceamount = advanceamount;
    }

    public String getOrdersescription()
    {
        return ordersescription;
    }

    public void setOrdersescription(String ordersescription)
    {
        this.ordersescription = ordersescription;
    }

    public Customers getCustomer()
    {
        return customer;
    }

    public void setCustomer(Customers customer)
    {
        this.customer = customer;
    }

    public Agents getAgent()
    {
        return agent;
    }

    public void setAgent(Agents agent)
    {
        this.agent = agent;
    }
}
