//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-833 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.07.30 at 10:25:27 下午 CST 
//


package cn.summer.bus_side.domainCES001;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TrdDir.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="TrdDir">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="TDD01"/>
 *     &lt;enumeration value="TDD02"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "TrdDir")
@XmlEnum
public enum TrdDir {

    @XmlEnumValue("TDD01")
    TDD_01("TDD01"),
    @XmlEnumValue("TDD02")
    TDD_02("TDD02");
    private final String value;

    TrdDir(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TrdDir fromValue(String v) {
        for (TrdDir c: TrdDir.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
