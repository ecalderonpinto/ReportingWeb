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
 * <p>Java class for ComplexAIFNationalIdentifierType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ComplexAIFNationalIdentifierType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ReportingMemberState" type="{}CountryCodeType"/>
 *         &lt;element name="AIFNationalCode" type="{}AIFNationalCodeType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ComplexAIFNationalIdentifierType", propOrder = {
    "reportingMemberState",
    "aifNationalCode"
})
public class ComplexAIFNationalIdentifierType {

    @XmlElement(name = "ReportingMemberState", required = true)
    protected String reportingMemberState;
    @XmlElement(name = "AIFNationalCode", required = true)
    protected String aifNationalCode;

    /**
     * Gets the value of the reportingMemberState property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReportingMemberState() {
        return reportingMemberState;
    }

    /**
     * Sets the value of the reportingMemberState property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReportingMemberState(String value) {
        this.reportingMemberState = value;
    }

    /**
     * Gets the value of the aifNationalCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAIFNationalCode() {
        return aifNationalCode;
    }

    /**
     * Sets the value of the aifNationalCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAIFNationalCode(String value) {
        this.aifNationalCode = value;
    }

}