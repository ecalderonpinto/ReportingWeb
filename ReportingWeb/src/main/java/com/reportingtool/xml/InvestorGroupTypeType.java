//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-520 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.02.19 at 04:23:50 PM CET 
//


package com.reportingtool.xml;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for InvestorGroupTypeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="InvestorGroupTypeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="NFCO"/>
 *     &lt;enumeration value="BANK"/>
 *     &lt;enumeration value="INSC"/>
 *     &lt;enumeration value="OFIN"/>
 *     &lt;enumeration value="PFND"/>
 *     &lt;enumeration value="GENG"/>
 *     &lt;enumeration value="OCIU"/>
 *     &lt;enumeration value="HHLD"/>
 *     &lt;enumeration value="UNKN"/>
 *     &lt;enumeration value="NONE"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "InvestorGroupTypeType")
@XmlEnum
public enum InvestorGroupTypeType {

    NFCO,
    BANK,
    INSC,
    OFIN,
    PFND,
    GENG,
    OCIU,
    HHLD,
    UNKN,
    NONE;

    public String value() {
        return name();
    }

    public static InvestorGroupTypeType fromValue(String v) {
        return valueOf(v);
    }

}
