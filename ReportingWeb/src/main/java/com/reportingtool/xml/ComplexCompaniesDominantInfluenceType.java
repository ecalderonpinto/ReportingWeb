//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-520 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.02.19 at 04:23:50 PM CET 
//


package com.reportingtool.xml;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ComplexCompaniesDominantInfluenceType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ComplexCompaniesDominantInfluenceType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CompanyDominantInfluence" type="{}ComplexCompanyDominantInfluenceType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ComplexCompaniesDominantInfluenceType", propOrder = {
    "companyDominantInfluence"
})
public class ComplexCompaniesDominantInfluenceType {

    @XmlElement(name = "CompanyDominantInfluence")
    protected List<ComplexCompanyDominantInfluenceType> companyDominantInfluence;

    /**
     * Gets the value of the companyDominantInfluence property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the companyDominantInfluence property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCompanyDominantInfluence().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComplexCompanyDominantInfluenceType }
     * 
     * 
     */
    public List<ComplexCompanyDominantInfluenceType> getCompanyDominantInfluence() {
        if (companyDominantInfluence == null) {
            companyDominantInfluence = new ArrayList<ComplexCompanyDominantInfluenceType>();
        }
        return this.companyDominantInfluence;
    }

}
