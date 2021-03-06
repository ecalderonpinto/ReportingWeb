//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-520 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.02.19 at 04:23:50 PM CET 
//


package com.reportingtool.xml;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ComplexMarketRiskMeasureType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ComplexMarketRiskMeasureType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RiskMeasureType" type="{}RiskMeasureTypeType"/>
 *         &lt;choice>
 *           &lt;element name="RiskMeasureValue" type="{}SignedRate15p2Type"/>
 *           &lt;element name="BucketRiskMeasureValues" type="{}ComplexBucketRiskMeasureValuesType"/>
 *           &lt;element name="VegaRiskMeasureValues" type="{}ComplexVegaRiskMeasureValuesType"/>
 *           &lt;element name="VARRiskMeasureValues" type="{}ComplexVARRiskMeasureValuesType"/>
 *         &lt;/choice>
 *         &lt;element name="RiskMeasureDescription" type="{}StringRestricted300Type" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ComplexMarketRiskMeasureType", propOrder = {
    "riskMeasureType",
    "riskMeasureValue",
    "bucketRiskMeasureValues",
    "vegaRiskMeasureValues",
    "varRiskMeasureValues",
    "riskMeasureDescription"
})
public class ComplexMarketRiskMeasureType {

    @XmlElement(name = "RiskMeasureType", required = true)
    protected RiskMeasureTypeType riskMeasureType;
    @XmlElement(name = "RiskMeasureValue")
    protected BigDecimal riskMeasureValue;
    @XmlElement(name = "BucketRiskMeasureValues")
    protected ComplexBucketRiskMeasureValuesType bucketRiskMeasureValues;
    @XmlElement(name = "VegaRiskMeasureValues")
    protected ComplexVegaRiskMeasureValuesType vegaRiskMeasureValues;
    @XmlElement(name = "VARRiskMeasureValues")
    protected ComplexVARRiskMeasureValuesType varRiskMeasureValues;
    @XmlElement(name = "RiskMeasureDescription")
    protected String riskMeasureDescription;

    /**
     * Gets the value of the riskMeasureType property.
     * 
     * @return
     *     possible object is
     *     {@link RiskMeasureTypeType }
     *     
     */
    public RiskMeasureTypeType getRiskMeasureType() {
        return riskMeasureType;
    }

    /**
     * Sets the value of the riskMeasureType property.
     * 
     * @param value
     *     allowed object is
     *     {@link RiskMeasureTypeType }
     *     
     */
    public void setRiskMeasureType(RiskMeasureTypeType value) {
        this.riskMeasureType = value;
    }

    /**
     * Gets the value of the riskMeasureValue property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getRiskMeasureValue() {
        return riskMeasureValue;
    }

    /**
     * Sets the value of the riskMeasureValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setRiskMeasureValue(BigDecimal value) {
        this.riskMeasureValue = value;
    }

    /**
     * Gets the value of the bucketRiskMeasureValues property.
     * 
     * @return
     *     possible object is
     *     {@link ComplexBucketRiskMeasureValuesType }
     *     
     */
    public ComplexBucketRiskMeasureValuesType getBucketRiskMeasureValues() {
        return bucketRiskMeasureValues;
    }

    /**
     * Sets the value of the bucketRiskMeasureValues property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComplexBucketRiskMeasureValuesType }
     *     
     */
    public void setBucketRiskMeasureValues(ComplexBucketRiskMeasureValuesType value) {
        this.bucketRiskMeasureValues = value;
    }

    /**
     * Gets the value of the vegaRiskMeasureValues property.
     * 
     * @return
     *     possible object is
     *     {@link ComplexVegaRiskMeasureValuesType }
     *     
     */
    public ComplexVegaRiskMeasureValuesType getVegaRiskMeasureValues() {
        return vegaRiskMeasureValues;
    }

    /**
     * Sets the value of the vegaRiskMeasureValues property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComplexVegaRiskMeasureValuesType }
     *     
     */
    public void setVegaRiskMeasureValues(ComplexVegaRiskMeasureValuesType value) {
        this.vegaRiskMeasureValues = value;
    }

    /**
     * Gets the value of the varRiskMeasureValues property.
     * 
     * @return
     *     possible object is
     *     {@link ComplexVARRiskMeasureValuesType }
     *     
     */
    public ComplexVARRiskMeasureValuesType getVARRiskMeasureValues() {
        return varRiskMeasureValues;
    }

    /**
     * Sets the value of the varRiskMeasureValues property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComplexVARRiskMeasureValuesType }
     *     
     */
    public void setVARRiskMeasureValues(ComplexVARRiskMeasureValuesType value) {
        this.varRiskMeasureValues = value;
    }

    /**
     * Gets the value of the riskMeasureDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRiskMeasureDescription() {
        return riskMeasureDescription;
    }

    /**
     * Sets the value of the riskMeasureDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRiskMeasureDescription(String value) {
        this.riskMeasureDescription = value;
    }

}
