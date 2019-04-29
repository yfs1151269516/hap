
package com.map;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>callProductVo complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="callProductVo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="activeStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="activities" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="activityAmount" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="activityId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="clientName" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="createTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="createdByBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="eventName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="managingEmployees" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="privilege" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="releaseDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="releaseEndDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="ruleName" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="tushi" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "callProductVo", propOrder = {
    "activeStatus",
    "activities",
    "activityAmount",
    "activityId",
    "clientName",
    "createTime",
    "createdByBy",
    "eventName",
    "managingEmployees",
    "privilege",
    "releaseDate",
    "releaseEndDate",
    "ruleName",
    "tushi"
})
public class CallProductVo {

    protected String activeStatus;
    protected String activities;
    protected double activityAmount;
    protected Long activityId;
    @XmlElement(nillable = true)
    protected List<String> clientName;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createTime;
    protected String createdByBy;
    protected String eventName;
    protected String managingEmployees;
    protected String privilege;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar releaseDate;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar releaseEndDate;
    @XmlElement(nillable = true)
    protected List<String> ruleName;
    protected String tushi;

    /**
     * ��ȡactiveStatus���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActiveStatus() {
        return activeStatus;
    }

    /**
     * ����activeStatus���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActiveStatus(String value) {
        this.activeStatus = value;
    }

    /**
     * ��ȡactivities���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActivities() {
        return activities;
    }

    /**
     * ����activities���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActivities(String value) {
        this.activities = value;
    }

    /**
     * ��ȡactivityAmount���Ե�ֵ��
     * 
     */
    public double getActivityAmount() {
        return activityAmount;
    }

    /**
     * ����activityAmount���Ե�ֵ��
     * 
     */
    public void setActivityAmount(double value) {
        this.activityAmount = value;
    }

    /**
     * ��ȡactivityId���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getActivityId() {
        return activityId;
    }

    /**
     * ����activityId���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setActivityId(Long value) {
        this.activityId = value;
    }

    /**
     * Gets the value of the clientName property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the clientName property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getClientName().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getClientName() {
        if (clientName == null) {
            clientName = new ArrayList<String>();
        }
        return this.clientName;
    }

    /**
     * ��ȡcreateTime���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreateTime() {
        return createTime;
    }

    /**
     * ����createTime���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreateTime(XMLGregorianCalendar value) {
        this.createTime = value;
    }

    /**
     * ��ȡcreatedByBy���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreatedByBy() {
        return createdByBy;
    }

    /**
     * ����createdByBy���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreatedByBy(String value) {
        this.createdByBy = value;
    }

    /**
     * ��ȡeventName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEventName() {
        return eventName;
    }

    /**
     * ����eventName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEventName(String value) {
        this.eventName = value;
    }

    /**
     * ��ȡmanagingEmployees���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getManagingEmployees() {
        return managingEmployees;
    }

    /**
     * ����managingEmployees���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setManagingEmployees(String value) {
        this.managingEmployees = value;
    }

    /**
     * ��ȡprivilege���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrivilege() {
        return privilege;
    }

    /**
     * ����privilege���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrivilege(String value) {
        this.privilege = value;
    }

    /**
     * ��ȡreleaseDate���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getReleaseDate() {
        return releaseDate;
    }

    /**
     * ����releaseDate���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setReleaseDate(XMLGregorianCalendar value) {
        this.releaseDate = value;
    }

    /**
     * ��ȡreleaseEndDate���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getReleaseEndDate() {
        return releaseEndDate;
    }

    /**
     * ����releaseEndDate���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setReleaseEndDate(XMLGregorianCalendar value) {
        this.releaseEndDate = value;
    }

    /**
     * Gets the value of the ruleName property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ruleName property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRuleName().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getRuleName() {
        if (ruleName == null) {
            ruleName = new ArrayList<String>();
        }
        return this.ruleName;
    }

    /**
     * ��ȡtushi���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTushi() {
        return tushi;
    }

    /**
     * ����tushi���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTushi(String value) {
        this.tushi = value;
    }

}
