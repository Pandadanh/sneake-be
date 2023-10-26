package com.example.project_springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_setting")
public class tbl_setting {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "logo")
    private String logo;
    @Column(name = "favicon")
    private String favicon;
    @Column(name = "contact_email")
    private String contactEmail;
    @Column(name = "contact_phone")
    private String contactPhone;
    @Column(name = "contact_map_iframe")
    private String contactMapIframe;
    @Column(name = "footer_copyright")
    private String footerCopyright;
    @Column(name = "contact_address")
    private String contactAddress;
    @Column(name = "footer_about")
    private String footerAbout;

    public tbl_setting() {

    }

    public tbl_setting(int id, String logo, String favicon, String contactEmail,
        String contactPhone,
        String contactMapIframe, String footerCopyright, String contactAddress,
        String footerAbout) {
        this.id = id;
        this.logo = logo;
        this.favicon = favicon;
        this.contactEmail = contactEmail;
        this.contactPhone = contactPhone;
        this.contactMapIframe = contactMapIframe;
        this.footerCopyright = footerCopyright;
        this.contactAddress = contactAddress;
        this.footerAbout = footerAbout;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getFavicon() {
        return favicon;
    }

    public void setFavicon(String favicon) {
        this.favicon = favicon;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactMapIframe() {
        return contactMapIframe;
    }

    public void setContactMapIframe(String contactMapIframe) {
        this.contactMapIframe = contactMapIframe;
    }

    public String getFooterCopyright() {
        return footerCopyright;
    }

    public void setFooterCopyright(String footerCopyright) {
        this.footerCopyright = footerCopyright;
    }

    public String getContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }

    public String getFooterAbout() {
        return footerAbout;
    }

    public void setFooterAbout(String footerAbout) {
        this.footerAbout = footerAbout;
    }
}
