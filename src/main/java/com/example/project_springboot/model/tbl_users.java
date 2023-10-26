package com.example.project_springboot.model;
import javax.persistence.*;


@Entity
@Table(name = "tbl_users")
public class tbl_users {


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_user")
  private int idUser;

  @Column(name = "matkhau")
  private String matKhau;

  @Column(name = "ten_user")
  private String tenUser;

  @Column(name = "email")
  private String email;

  @Column(name = "sodth")
  private String soDienThoai;

  @Column(name = "diachi")
  private String diaChi;

  @Column(name = "avatar")
  private String avatar;

  @Column(name = "trangthai")
  private int trangThai;

  @ManyToOne
  @JoinColumn(name = "nhomquyen")
  private tbl_nhomquyen nhomQuyen;

  @Column(name = "is_active")
  private int isActive;

  @Column(name = "active_token")
  private String activeToken;

  @Column(name = "reset_token")
  private String resetToken;

  @Column(name = "daxoa")
  private int daxoa;

  public tbl_users() {
  }

  public tbl_users(String email, String matKhau) {
    this.matKhau = matKhau;
    this.email = email;
  }

  public int getDaxoa() {
    return daxoa;
  }

  public void setDaxoa(int daxoa) {
    this.daxoa = daxoa;
  }

  public tbl_users(int idUser, String matKhau, String tenUser, String email, String soDienThoai,
      String diaChi, String avatar, int trangThai, tbl_nhomquyen nhomQuyen, int isActive,
      String activeToken, String resetToken, int daxoa) {
    this.idUser = idUser;
    this.matKhau = matKhau;
    this.tenUser = tenUser;
    this.email = email;
    this.soDienThoai = soDienThoai;
    this.diaChi = diaChi;
    this.avatar = avatar;
    this.trangThai = trangThai;
    this.nhomQuyen = nhomQuyen;
    this.isActive = isActive;
    this.activeToken = activeToken;
    this.resetToken = resetToken;
    this.daxoa = daxoa;
  }

  public tbl_users(int idUser, String matKhau, String tenUser, String email, String soDienThoai,
      String diaChi, String avatar, int trangThai, tbl_nhomquyen nhomQuyen, int isActive,
      String activeToken, String resetToken) {
    this.idUser = idUser;
    this.matKhau = matKhau;
    this.tenUser = tenUser;
    this.email = email;
    this.soDienThoai = soDienThoai;
    this.diaChi = diaChi;
    this.avatar = avatar;
    this.trangThai = trangThai;
    this.nhomQuyen = nhomQuyen;
    this.isActive = isActive;
    this.activeToken = activeToken;
    this.resetToken = resetToken;
  }

  public int getIdUser() {
    return idUser;
  }

  public void setIdUser(int idUser) {
    this.idUser = idUser;
  }

  public String getMatKhau() {
    return matKhau;
  }

  public void setMatKhau(String matKhau) {
    this.matKhau = matKhau;
  }

  public String getTenUser() {
    return tenUser;
  }

  public void setTenUser(String tenUser) {
    this.tenUser = tenUser;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getSoDienThoai() {
    return soDienThoai;
  }

  public void setSoDienThoai(String soDienThoai) {
    this.soDienThoai = soDienThoai;
  }

  public String getDiaChi() {
    return diaChi;
  }

  public void setDiaChi(String diaChi) {
    this.diaChi = diaChi;
  }

  public String getAvatar() {
    return avatar;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }

  public int getTrangThai() {
    return trangThai;
  }

  public void setTrangThai(int trangThai) {
    this.trangThai = trangThai;
  }

  public tbl_nhomquyen getNhomQuyen() {
    return nhomQuyen;
  }

  public void setNhomQuyen(tbl_nhomquyen nhomQuyen) {
    this.nhomQuyen = nhomQuyen;
  }

  public int getIsActive() {
    return isActive;
  }

  public void setIsActive(int isActive) {
    this.isActive = isActive;
  }

  public String getActiveToken() {
    return activeToken;
  }

  public void setActiveToken(String activeToken) {
    this.activeToken = activeToken;
  }

  public String getResetToken() {
    return resetToken;
  }

  public void setResetToken(String resetToken) {
    this.resetToken = resetToken;
  }

  @Override
  public String toString() {
    return "tbl_users{" +
        "idUser=" + idUser +
        ", matKhau='" + matKhau + '\'' +
        ", tenUser='" + tenUser + '\'' +
        ", email='" + email + '\'' +
        ", soDienThoai='" + soDienThoai + '\'' +
        ", diaChi='" + diaChi + '\'' +
        ", avatar='" + avatar + '\'' +
        ", trangThai=" + trangThai +
        ", nhomQuyen='" + nhomQuyen + '\'' +
        ", isActive=" + isActive +
        ", activeToken='" + activeToken + '\'' +
        ", resetToken='" + resetToken + '\'' +
        '}';
  }
}
