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
 * <p>Java class for ComplexAIFLeverageInfoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ComplexAIFLeverageInfoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AIFLeverageArticle24-2" type="{}ComplexAIFLeverageArticle24-2Type"/>
 *         &lt;element name="AIFLeverageArticle24-4" type="{}ComplexAIFLeverageArticle24-4Type" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ComplexAIFLeverageInfoType", propOrder = {
    "aifLeverageArticle242",
    "aifLeverageArticle244"
})
public class ComplexAIFLeverageInfoType {

    @XmlElement(name = "AIFLeverageArticle24-2", required = true)
    protected ComplexAIFLeverageArticle242Type aifLeverageArticle242;
    @XmlElement(name = "AIFLeverageArticle24-4")
    protected ComplexAIFLeverageArticle244Type aifLeverageArticle244;

    /**
     * Gets the value of the aifLeverageArticle242 property.
     * 
     * @return
     *     possible object is
     *     {@link ComplexAIFLeverageArticle242Type }
     *     
     */
    public ComplexAIFLeverageArticle242Type getAIFLeverageArticle242() {
        return aifLeverageArticle242;
    }

    /**
     * Sets the value of the aifLeverageArticle242 property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComplexAIFLeverageArticle242Type }
     *     
     */
    public void setAIFLeverageArticle242(ComplexAIFLeverageArticle242Type value) {
        this.aifLeverageArticle242 = value;
    }

    /**
     * Gets the value of the aifLeverageArticle244 property.
     * 
     * @return
     *     possible object is
     *     {@link ComplexAIFLeverageArticle244Type }
     *     
     */
    public ComplexAIFLeverageArticle244Type getAIFLeverageArticle244() {
        return aifLeverageArticle244;
    }

    /**
     * Sets the value of the aifLeverageArticle244 property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComplexAIFLeverageArticle244Type }
     *     
     */
    public void setAIFLeverageArticle244(ComplexAIFLeverageArticle244Type value) {
        this.aifLeverageArticle244 = value;
    }

}
