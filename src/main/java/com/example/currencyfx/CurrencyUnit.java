package com.example.currencyfx;

import java.util.Objects;

public class CurrencyUnit
{
    private String name;
    private float value;
    private int unit;
    CurrencyUnit(String name,float value,int unit)
    {
        this.name=name;
        this.value=value;
        this.unit=unit;
    }
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof CurrencyUnit)) return false;
        CurrencyUnit that = (CurrencyUnit) o;
        return that.getName()==this.getName();
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getName());
    }

    public float getValue()
    {
        return value/unit;
    }
    public String getName()
    {
        return name;
    }
    @Override
    public String toString()
    {
        return name;
    }
}
