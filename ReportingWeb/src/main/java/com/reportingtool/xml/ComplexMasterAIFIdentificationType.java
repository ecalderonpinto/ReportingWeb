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
 * <p>Java class for ComplexMasterAIFIdentificationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ComplexMasterAIFIdentificationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AIFName" type="{}StringRestricted300Type"/>
 *         &lt;element name="AIFIdentifierNCA" type="{}ComplexAIFNationalIdentifierType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ComplexMasterAIFIdentificationType", propOrder = {
    "aifName",
    "aifIdentifierNCA"
})
public class ComplexMasterAIFIdentificationType {

    @XmlElement(name = "AIFName", required = true)
    protected String aifName;
    @XmlElement(name = "AIFIdentifierNCA")
    protected ComplexAIFNationalIdentifierType aifIdentifierNCA;

    /**
     * Gets the value of the aifName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAIFName() {
        return aifName;
    }

    /**
     * Sets the value of the aifName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAIFName(String value) {
        this.aifName = value;
    }

    /**
     * Gets the value of the aifIdentifierNCA property.
     * 
     * @return
     *     possible object is
     *     {@link ComplexAIFNationalIdentifierType }
     *     
     */
    public ComplexAIFNationalIdentifierType getAIFIdentifierNCA() {
        return aifIdentifierNCA;
    }

    /**
     * Sets the value of the aifIdentifierNCA property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComplexAIFNationalIdentifierType }
     *     
     */
    public void setAIFIdentifierNCA(ComplexAIFNationalIdentifierType value) {
        this.aifIdentifierNCA = value;
    }

}
