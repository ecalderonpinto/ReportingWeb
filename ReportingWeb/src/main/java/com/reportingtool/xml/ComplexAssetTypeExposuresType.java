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
 * <p>Java class for ComplexAssetTypeExposuresType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ComplexAssetTypeExposuresType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AssetTypeExposure" type="{}ComplexAssetTypeExposureType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ComplexAssetTypeExposuresType", propOrder = {
    "assetTypeExposure"
})
public class ComplexAssetTypeExposuresType {

    @XmlElement(name = "AssetTypeExposure", required = true)
    protected List<ComplexAssetTypeExposureType> assetTypeExposure;

    /**
     * Gets the value of the assetTypeExposure property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the assetTypeExposure property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAssetTypeExposure().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComplexAssetTypeExposureType }
     * 
     * 
     */
    public List<ComplexAssetTypeExposureType> getAssetTypeExposure() {
        if (assetTypeExposure == null) {
            assetTypeExposure = new ArrayList<ComplexAssetTypeExposureType>();
        }
        return this.assetTypeExposure;
    }

}
