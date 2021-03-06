//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-520 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.02.19 at 04:23:50 PM CET 
//


package com.reportingtool.xml;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ComplexFinancingLiquidityProfileType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ComplexFinancingLiquidityProfileType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TotalFinancingAmount" type="{}UnsignedInteger15pType"/>
 *         &lt;element name="TotalFinancingInDays0to1Rate" type="{}UnsignedDecimal15p4Type"/>
 *         &lt;element name="TotalFinancingInDays2to7Rate" type="{}UnsignedDecimal15p4Type"/>
 *         &lt;element name="TotalFinancingInDays8to30Rate" type="{}UnsignedDecimal15p4Type"/>
 *         &lt;element name="TotalFinancingInDays31to90Rate" type="{}UnsignedDecimal15p4Type"/>
 *         &lt;element name="TotalFinancingInDays91to180Rate" type="{}UnsignedDecimal15p4Type"/>
 *         &lt;element name="TotalFinancingInDays181to365Rate" type="{}UnsignedDecimal15p4Type"/>
 *         &lt;element name="TotalFinancingInDays365MoreRate" type="{}UnsignedDecimal15p4Type"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ComplexFinancingLiquidityProfileType", propOrder = {
    "totalFinancingAmount",
    "totalFinancingInDays0To1Rate",
    "totalFinancingInDays2To7Rate",
    "totalFinancingInDays8To30Rate",
    "totalFinancingInDays31To90Rate",
    "totalFinancingInDays91To180Rate",
    "totalFinancingInDays181To365Rate",
    "totalFinancingInDays365MoreRate"
})
public class ComplexFinancingLiquidityProfileType {

    @XmlElement(name = "TotalFinancingAmount", required = true)
    protected BigInteger totalFinancingAmount;
    @XmlElement(name = "TotalFinancingInDays0to1Rate", required = true)
    protected BigDecimal totalFinancingInDays0To1Rate;
    @XmlElement(name = "TotalFinancingInDays2to7Rate", required = true)
    protected BigDecimal totalFinancingInDays2To7Rate;
    @XmlElement(name = "TotalFinancingInDays8to30Rate", required = true)
    protected BigDecimal totalFinancingInDays8To30Rate;
    @XmlElement(name = "TotalFinancingInDays31to90Rate", required = true)
    protected BigDecimal totalFinancingInDays31To90Rate;
    @XmlElement(name = "TotalFinancingInDays91to180Rate", required = true)
    protected BigDecimal totalFinancingInDays91To180Rate;
    @XmlElement(name = "TotalFinancingInDays181to365Rate", required = true)
    protected BigDecimal totalFinancingInDays181To365Rate;
    @XmlElement(name = "TotalFinancingInDays365MoreRate", required = true)
    protected BigDecimal totalFinancingInDays365MoreRate;

    /**
     * Gets the value of the totalFinancingAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getTotalFinancingAmount() {
        return totalFinancingAmount;
    }

    /**
     * Sets the value of the totalFinancingAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setTotalFinancingAmount(BigInteger value) {
        this.totalFinancingAmount = value;
    }

    /**
     * Gets the value of the totalFinancingInDays0To1Rate property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTotalFinancingInDays0To1Rate() {
        return totalFinancingInDays0To1Rate;
    }

    /**
     * Sets the value of the totalFinancingInDays0To1Rate property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTotalFinancingInDays0To1Rate(BigDecimal value) {
        this.totalFinancingInDays0To1Rate = value;
    }

    /**
     * Gets the value of the totalFinancingInDays2To7Rate property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTotalFinancingInDays2To7Rate() {
        return totalFinancingInDays2To7Rate;
    }

    /**
     * Sets the value of the totalFinancingInDays2To7Rate property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTotalFinancingInDays2To7Rate(BigDecimal value) {
        this.totalFinancingInDays2To7Rate = value;
    }

    /**
     * Gets the value of the totalFinancingInDays8To30Rate property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTotalFinancingInDays8To30Rate() {
        return totalFinancingInDays8To30Rate;
    }

    /**
     * Sets the value of the totalFinancingInDays8To30Rate property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTotalFinancingInDays8To30Rate(BigDecimal value) {
        this.totalFinancingInDays8To30Rate = value;
    }

    /**
     * Gets the value of the totalFinancingInDays31To90Rate property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTotalFinancingInDays31To90Rate() {
        return totalFinancingInDays31To90Rate;
    }

    /**
     * Sets the value of the totalFinancingInDays31To90Rate property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTotalFinancingInDays31To90Rate(BigDecimal value) {
        this.totalFinancingInDays31To90Rate = value;
    }

    /**
     * Gets the value of the totalFinancingInDays91To180Rate property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTotalFinancingInDays91To180Rate() {
        return totalFinancingInDays91To180Rate;
    }

    /**
     * Sets the value of the totalFinancingInDays91To180Rate property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTotalFinancingInDays91To180Rate(BigDecimal value) {
        this.totalFinancingInDays91To180Rate = value;
    }

    /**
     * Gets the value of the totalFinancingInDays181To365Rate property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTotalFinancingInDays181To365Rate() {
        return totalFinancingInDays181To365Rate;
    }

    /**
     * Sets the value of the totalFinancingInDays181To365Rate property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTotalFinancingInDays181To365Rate(BigDecimal value) {
        this.totalFinancingInDays181To365Rate = value;
    }

    /**
     * Gets the value of the totalFinancingInDays365MoreRate property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTotalFinancingInDays365MoreRate() {
        return totalFinancingInDays365MoreRate;
    }

    /**
     * Sets the value of the totalFinancingInDays365MoreRate property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTotalFinancingInDays365MoreRate(BigDecimal value) {
        this.totalFinancingInDays365MoreRate = value;
    }

}
