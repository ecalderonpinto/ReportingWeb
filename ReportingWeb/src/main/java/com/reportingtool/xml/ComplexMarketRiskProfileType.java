//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-520 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.02.19 at 04:23:50 PM CET 
//


package com.reportingtool.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ComplexMarketRiskProfileType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ComplexMarketRiskProfileType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AnnualInvestmentReturnRate" type="{}SignedRateNA15p2Type"/>
 *         &lt;element name="MarketRiskMeasures" type="{}ComplexMarketRiskMeasuresType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ComplexMarketRiskProfileType", propOrder = {
    "annualInvestmentReturnRate",
    "marketRiskMeasures"
})
public class ComplexMarketRiskProfileType {

    @XmlElement(name = "AnnualInvestmentReturnRate", required = true)
    protected String annualInvestmentReturnRate;
    @XmlElement(name = "MarketRiskMeasures")
    protected ComplexMarketRiskMeasuresType marketRiskMeasures;

    /**
     * Gets the value of the annualInvestmentReturnRate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAnnualInvestmentReturnRate() {
        return annualInvestmentReturnRate;
    }

    /**
     * Sets the value of the annualInvestmentReturnRate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAnnualInvestmentReturnRate(String value) {
        this.annualInvestmentReturnRate = value;
    }

    /**
     * Gets the value of the marketRiskMeasures property.
     * 
     * @return
     *     possible object is
     *     {@link ComplexMarketRiskMeasuresType }
     *     
     */
    public ComplexMarketRiskMeasuresType getMarketRiskMeasures() {
        return marketRiskMeasures;
    }

    /**
     * Sets the value of the marketRiskMeasures property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComplexMarketRiskMeasuresType }
     *     
     */
    public void setMarketRiskMeasures(ComplexMarketRiskMeasuresType value) {
        this.marketRiskMeasures = value;
    }

}
