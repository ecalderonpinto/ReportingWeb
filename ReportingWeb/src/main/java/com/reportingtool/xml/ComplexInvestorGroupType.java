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
 * <p>Java class for ComplexInvestorGroupType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ComplexInvestorGroupType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="InvestorGroupType" type="{}InvestorGroupTypeType"/>
 *         &lt;element name="InvestorGroupRate" type="{}UnsignedPercentType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ComplexInvestorGroupType", propOrder = {
    "investorGroupType",
    "investorGroupRate"
})
public class ComplexInvestorGroupType {

    @XmlElement(name = "InvestorGroupType", required = true)
    protected InvestorGroupTypeType investorGroupType;
    @XmlElement(name = "InvestorGroupRate", required = true)
    protected BigDecimal investorGroupRate;

    /**
     * Gets the value of the investorGroupType property.
     * 
     * @return
     *     possible object is
     *     {@link InvestorGroupTypeType }
     *     
     */
    public InvestorGroupTypeType getInvestorGroupType() {
        return investorGroupType;
    }

    /**
     * Sets the value of the investorGroupType property.
     * 
     * @param value
     *     allowed object is
     *     {@link InvestorGroupTypeType }
     *     
     */
    public void setInvestorGroupType(InvestorGroupTypeType value) {
        this.investorGroupType = value;
    }

    /**
     * Gets the value of the investorGroupRate property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getInvestorGroupRate() {
        return investorGroupRate;
    }

    /**
     * Sets the value of the investorGroupRate property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setInvestorGroupRate(BigDecimal value) {
        this.investorGroupRate = value;
    }

}
