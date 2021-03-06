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
 * <p>Java class for ComplexAssetTypeExposureType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ComplexAssetTypeExposureType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SubAssetType" type="{}SubAssetTypeType"/>
 *         &lt;element name="GrossValue" type="{}UnsignedInteger15pType" minOccurs="0"/>
 *         &lt;element name="LongValue" type="{}UnsignedInteger15pType" minOccurs="0"/>
 *         &lt;element name="ShortValue" type="{}UnsignedInteger15pType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ComplexAssetTypeExposureType", propOrder = {
    "subAssetType",
    "grossValue",
    "longValue",
    "shortValue"
})
public class ComplexAssetTypeExposureType {

    @XmlElement(name = "SubAssetType", required = true)
    protected SubAssetTypeType subAssetType;
    @XmlElement(name = "GrossValue")
    protected BigInteger grossValue;
    @XmlElement(name = "LongValue")
    protected BigInteger longValue;
    @XmlElement(name = "ShortValue")
    protected BigInteger shortValue;

    /**
     * Gets the value of the subAssetType property.
     * 
     * @return
     *     possible object is
     *     {@link SubAssetTypeType }
     *     
     */
    public SubAssetTypeType getSubAssetType() {
        return subAssetType;
    }

    /**
     * Sets the value of the subAssetType property.
     * 
     * @param value
     *     allowed object is
     *     {@link SubAssetTypeType }
     *     
     */
    public void setSubAssetType(SubAssetTypeType value) {
        this.subAssetType = value;
    }

    /**
     * Gets the value of the grossValue property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getGrossValue() {
        return grossValue;
    }

    /**
     * Sets the value of the grossValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setGrossValue(BigInteger value) {
        this.grossValue = value;
    }

    /**
     * Gets the value of the longValue property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getLongValue() {
        return longValue;
    }

    /**
     * Sets the value of the longValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setLongValue(BigInteger value) {
        this.longValue = value;
    }

    /**
     * Gets the value of the shortValue property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getShortValue() {
        return shortValue;
    }

    /**
     * Sets the value of the shortValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setShortValue(BigInteger value) {
        this.shortValue = value;
    }

}
