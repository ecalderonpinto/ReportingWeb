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
 * <p>Java class for ComplexRealEstateFundStrategyType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ComplexRealEstateFundStrategyType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RealEstateFundStrategyType" type="{}RealEstateFundStrategyTypeType"/>
 *         &lt;element name="PrimaryStrategyFlag" type="{}BooleanType"/>
 *         &lt;element name="StrategyNAVRate" type="{}SignedRate15p2Type" minOccurs="0"/>
 *         &lt;element name="StrategyTypeOtherDescription" type="{}StringRestricted300Type" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ComplexRealEstateFundStrategyType", propOrder = {
    "realEstateFundStrategyType",
    "primaryStrategyFlag",
    "strategyNAVRate",
    "strategyTypeOtherDescription"
})
public class ComplexRealEstateFundStrategyType {

    @XmlElement(name = "RealEstateFundStrategyType", required = true)
    protected RealEstateFundStrategyTypeType realEstateFundStrategyType;
    @XmlElement(name = "PrimaryStrategyFlag")
    protected boolean primaryStrategyFlag;
    @XmlElement(name = "StrategyNAVRate")
    protected BigDecimal strategyNAVRate;
    @XmlElement(name = "StrategyTypeOtherDescription")
    protected String strategyTypeOtherDescription;

    /**
     * Gets the value of the realEstateFundStrategyType property.
     * 
     * @return
     *     possible object is
     *     {@link RealEstateFundStrategyTypeType }
     *     
     */
    public RealEstateFundStrategyTypeType getRealEstateFundStrategyType() {
        return realEstateFundStrategyType;
    }

    /**
     * Sets the value of the realEstateFundStrategyType property.
     * 
     * @param value
     *     allowed object is
     *     {@link RealEstateFundStrategyTypeType }
     *     
     */
    public void setRealEstateFundStrategyType(RealEstateFundStrategyTypeType value) {
        this.realEstateFundStrategyType = value;
    }

    /**
     * Gets the value of the primaryStrategyFlag property.
     * 
     */
    public boolean isPrimaryStrategyFlag() {
        return primaryStrategyFlag;
    }

    /**
     * Sets the value of the primaryStrategyFlag property.
     * 
     */
    public void setPrimaryStrategyFlag(boolean value) {
        this.primaryStrategyFlag = value;
    }

    /**
     * Gets the value of the strategyNAVRate property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getStrategyNAVRate() {
        return strategyNAVRate;
    }

    /**
     * Sets the value of the strategyNAVRate property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setStrategyNAVRate(BigDecimal value) {
        this.strategyNAVRate = value;
    }

    /**
     * Gets the value of the strategyTypeOtherDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStrategyTypeOtherDescription() {
        return strategyTypeOtherDescription;
    }

    /**
     * Sets the value of the strategyTypeOtherDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStrategyTypeOtherDescription(String value) {
        this.strategyTypeOtherDescription = value;
    }

}
