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
 * <p>Java class for ComplexAIFMCompleteDescriptionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ComplexAIFMCompleteDescriptionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AIFMIdentifier" type="{}ComplexAIFMIdentifierType" minOccurs="0"/>
 *         &lt;element name="AIFMPrincipalMarkets" type="{}ComplexAIFMPrincipalMarketsType"/>
 *         &lt;element name="AIFMPrincipalInstruments" type="{}ComplexAIFMPrincipalInstrumentsType"/>
 *         &lt;element name="AUMAmountInEuro" type="{}UnsignedInteger15pType"/>
 *         &lt;element name="AIFMBaseCurrencyDescription" type="{}ComplexBaseCurrencyDescriptionType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ComplexAIFMCompleteDescriptionType", propOrder = {
    "aifmIdentifier",
    "aifmPrincipalMarkets",
    "aifmPrincipalInstruments",
    "aumAmountInEuro",
    "aifmBaseCurrencyDescription"
})
public class ComplexAIFMCompleteDescriptionType {

    @XmlElement(name = "AIFMIdentifier")
    protected ComplexAIFMIdentifierType aifmIdentifier;
    @XmlElement(name = "AIFMPrincipalMarkets", required = true)
    protected ComplexAIFMPrincipalMarketsType aifmPrincipalMarkets;
    @XmlElement(name = "AIFMPrincipalInstruments", required = true)
    protected ComplexAIFMPrincipalInstrumentsType aifmPrincipalInstruments;
    @XmlElement(name = "AUMAmountInEuro", required = true)
    protected BigInteger aumAmountInEuro;
    @XmlElement(name = "AIFMBaseCurrencyDescription")
    protected ComplexBaseCurrencyDescriptionType aifmBaseCurrencyDescription;

    /**
     * Gets the value of the aifmIdentifier property.
     * 
     * @return
     *     possible object is
     *     {@link ComplexAIFMIdentifierType }
     *     
     */
    public ComplexAIFMIdentifierType getAIFMIdentifier() {
        return aifmIdentifier;
    }

    /**
     * Sets the value of the aifmIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComplexAIFMIdentifierType }
     *     
     */
    public void setAIFMIdentifier(ComplexAIFMIdentifierType value) {
        this.aifmIdentifier = value;
    }

    /**
     * Gets the value of the aifmPrincipalMarkets property.
     * 
     * @return
     *     possible object is
     *     {@link ComplexAIFMPrincipalMarketsType }
     *     
     */
    public ComplexAIFMPrincipalMarketsType getAIFMPrincipalMarkets() {
        return aifmPrincipalMarkets;
    }

    /**
     * Sets the value of the aifmPrincipalMarkets property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComplexAIFMPrincipalMarketsType }
     *     
     */
    public void setAIFMPrincipalMarkets(ComplexAIFMPrincipalMarketsType value) {
        this.aifmPrincipalMarkets = value;
    }

    /**
     * Gets the value of the aifmPrincipalInstruments property.
     * 
     * @return
     *     possible object is
     *     {@link ComplexAIFMPrincipalInstrumentsType }
     *     
     */
    public ComplexAIFMPrincipalInstrumentsType getAIFMPrincipalInstruments() {
        return aifmPrincipalInstruments;
    }

    /**
     * Sets the value of the aifmPrincipalInstruments property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComplexAIFMPrincipalInstrumentsType }
     *     
     */
    public void setAIFMPrincipalInstruments(ComplexAIFMPrincipalInstrumentsType value) {
        this.aifmPrincipalInstruments = value;
    }

    /**
     * Gets the value of the aumAmountInEuro property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getAUMAmountInEuro() {
        return aumAmountInEuro;
    }

    /**
     * Sets the value of the aumAmountInEuro property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setAUMAmountInEuro(BigInteger value) {
        this.aumAmountInEuro = value;
    }

    /**
     * Gets the value of the aifmBaseCurrencyDescription property.
     * 
     * @return
     *     possible object is
     *     {@link ComplexBaseCurrencyDescriptionType }
     *     
     */
    public ComplexBaseCurrencyDescriptionType getAIFMBaseCurrencyDescription() {
        return aifmBaseCurrencyDescription;
    }

    /**
     * Sets the value of the aifmBaseCurrencyDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComplexBaseCurrencyDescriptionType }
     *     
     */
    public void setAIFMBaseCurrencyDescription(ComplexBaseCurrencyDescriptionType value) {
        this.aifmBaseCurrencyDescription = value;
    }

}
