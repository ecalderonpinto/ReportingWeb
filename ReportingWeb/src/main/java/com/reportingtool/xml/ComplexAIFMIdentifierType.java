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
 * <p>Java class for ComplexAIFMIdentifierType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ComplexAIFMIdentifierType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AIFMIdentifierLEI" type="{}LEICodeType" minOccurs="0"/>
 *         &lt;element name="AIFMIdentifierBIC" type="{}BICCodeType" minOccurs="0"/>
 *         &lt;element name="OldAIFMIdentifierNCA" type="{}ComplexAIFMNationalIdentifierType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ComplexAIFMIdentifierType", propOrder = {
    "aifmIdentifierLEI",
    "aifmIdentifierBIC",
    "oldAIFMIdentifierNCA"
})
public class ComplexAIFMIdentifierType {

    @XmlElement(name = "AIFMIdentifierLEI")
    protected String aifmIdentifierLEI;
    @XmlElement(name = "AIFMIdentifierBIC")
    protected String aifmIdentifierBIC;
    @XmlElement(name = "OldAIFMIdentifierNCA")
    protected ComplexAIFMNationalIdentifierType oldAIFMIdentifierNCA;

    /**
     * Gets the value of the aifmIdentifierLEI property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAIFMIdentifierLEI() {
        return aifmIdentifierLEI;
    }

    /**
     * Sets the value of the aifmIdentifierLEI property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAIFMIdentifierLEI(String value) {
        this.aifmIdentifierLEI = value;
    }

    /**
     * Gets the value of the aifmIdentifierBIC property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAIFMIdentifierBIC() {
        return aifmIdentifierBIC;
    }

    /**
     * Sets the value of the aifmIdentifierBIC property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAIFMIdentifierBIC(String value) {
        this.aifmIdentifierBIC = value;
    }

    /**
     * Gets the value of the oldAIFMIdentifierNCA property.
     * 
     * @return
     *     possible object is
     *     {@link ComplexAIFMNationalIdentifierType }
     *     
     */
    public ComplexAIFMNationalIdentifierType getOldAIFMIdentifierNCA() {
        return oldAIFMIdentifierNCA;
    }

    /**
     * Sets the value of the oldAIFMIdentifierNCA property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComplexAIFMNationalIdentifierType }
     *     
     */
    public void setOldAIFMIdentifierNCA(ComplexAIFMNationalIdentifierType value) {
        this.oldAIFMIdentifierNCA = value;
    }

}
