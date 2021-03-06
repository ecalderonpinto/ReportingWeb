//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-520 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.02.19 at 04:23:50 PM CET 
//


package com.reportingtool.xml;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ComplexQuantityMonthPeriodType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ComplexQuantityMonthPeriodType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="QuantityJanuary" type="{}UnsignedInteger15pType" minOccurs="0"/>
 *         &lt;element name="QuantityFebruary" type="{}UnsignedInteger15pType" minOccurs="0"/>
 *         &lt;element name="QuantityMarch" type="{}UnsignedInteger15pType" minOccurs="0"/>
 *         &lt;element name="QuantityApril" type="{}UnsignedInteger15pType" minOccurs="0"/>
 *         &lt;element name="QuantityMay" type="{}UnsignedInteger15pType" minOccurs="0"/>
 *         &lt;element name="QuantityJune" type="{}UnsignedInteger15pType" minOccurs="0"/>
 *         &lt;element name="QuantityJuly" type="{}UnsignedInteger15pType" minOccurs="0"/>
 *         &lt;element name="QuantityAugust" type="{}UnsignedInteger15pType" minOccurs="0"/>
 *         &lt;element name="QuantitySeptember" type="{}UnsignedInteger15pType" minOccurs="0"/>
 *         &lt;element name="QuantityOctober" type="{}UnsignedInteger15pType" minOccurs="0"/>
 *         &lt;element name="QuantityNovember" type="{}UnsignedInteger15pType" minOccurs="0"/>
 *         &lt;element name="QuantityDecember" type="{}UnsignedInteger15pType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ComplexQuantityMonthPeriodType", propOrder = {
    "quantityJanuary",
    "quantityFebruary",
    "quantityMarch",
    "quantityApril",
    "quantityMay",
    "quantityJune",
    "quantityJuly",
    "quantityAugust",
    "quantitySeptember",
    "quantityOctober",
    "quantityNovember",
    "quantityDecember"
})
public class ComplexQuantityMonthPeriodType {

    @XmlElement(name = "QuantityJanuary")
    protected BigInteger quantityJanuary;
    @XmlElement(name = "QuantityFebruary")
    protected BigInteger quantityFebruary;
    @XmlElement(name = "QuantityMarch")
    protected BigInteger quantityMarch;
    @XmlElement(name = "QuantityApril")
    protected BigInteger quantityApril;
    @XmlElement(name = "QuantityMay")
    protected BigInteger quantityMay;
    @XmlElement(name = "QuantityJune")
    protected BigInteger quantityJune;
    @XmlElement(name = "QuantityJuly")
    protected BigInteger quantityJuly;
    @XmlElement(name = "QuantityAugust")
    protected BigInteger quantityAugust;
    @XmlElement(name = "QuantitySeptember")
    protected BigInteger quantitySeptember;
    @XmlElement(name = "QuantityOctober")
    protected BigInteger quantityOctober;
    @XmlElement(name = "QuantityNovember")
    protected BigInteger quantityNovember;
    @XmlElement(name = "QuantityDecember")
    protected BigInteger quantityDecember;

    /**
     * Gets the value of the quantityJanuary property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getQuantityJanuary() {
        return quantityJanuary;
    }

    /**
     * Sets the value of the quantityJanuary property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setQuantityJanuary(BigInteger value) {
        this.quantityJanuary = value;
    }

    /**
     * Gets the value of the quantityFebruary property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getQuantityFebruary() {
        return quantityFebruary;
    }

    /**
     * Sets the value of the quantityFebruary property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setQuantityFebruary(BigInteger value) {
        this.quantityFebruary = value;
    }

    /**
     * Gets the value of the quantityMarch property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getQuantityMarch() {
        return quantityMarch;
    }

    /**
     * Sets the value of the quantityMarch property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setQuantityMarch(BigInteger value) {
        this.quantityMarch = value;
    }

    /**
     * Gets the value of the quantityApril property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getQuantityApril() {
        return quantityApril;
    }

    /**
     * Sets the value of the quantityApril property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setQuantityApril(BigInteger value) {
        this.quantityApril = value;
    }

    /**
     * Gets the value of the quantityMay property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getQuantityMay() {
        return quantityMay;
    }

    /**
     * Sets the value of the quantityMay property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setQuantityMay(BigInteger value) {
        this.quantityMay = value;
    }

    /**
     * Gets the value of the quantityJune property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getQuantityJune() {
        return quantityJune;
    }

    /**
     * Sets the value of the quantityJune property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setQuantityJune(BigInteger value) {
        this.quantityJune = value;
    }

    /**
     * Gets the value of the quantityJuly property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getQuantityJuly() {
        return quantityJuly;
    }

    /**
     * Sets the value of the quantityJuly property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setQuantityJuly(BigInteger value) {
        this.quantityJuly = value;
    }

    /**
     * Gets the value of the quantityAugust property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getQuantityAugust() {
        return quantityAugust;
    }

    /**
     * Sets the value of the quantityAugust property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setQuantityAugust(BigInteger value) {
        this.quantityAugust = value;
    }

    /**
     * Gets the value of the quantitySeptember property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getQuantitySeptember() {
        return quantitySeptember;
    }

    /**
     * Sets the value of the quantitySeptember property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setQuantitySeptember(BigInteger value) {
        this.quantitySeptember = value;
    }

    /**
     * Gets the value of the quantityOctober property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getQuantityOctober() {
        return quantityOctober;
    }

    /**
     * Sets the value of the quantityOctober property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setQuantityOctober(BigInteger value) {
        this.quantityOctober = value;
    }

    /**
     * Gets the value of the quantityNovember property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getQuantityNovember() {
        return quantityNovember;
    }

    /**
     * Sets the value of the quantityNovember property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setQuantityNovember(BigInteger value) {
        this.quantityNovember = value;
    }

    /**
     * Gets the value of the quantityDecember property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getQuantityDecember() {
        return quantityDecember;
    }

    /**
     * Sets the value of the quantityDecember property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setQuantityDecember(BigInteger value) {
        this.quantityDecember = value;
    }

}
