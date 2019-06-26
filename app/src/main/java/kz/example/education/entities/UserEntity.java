package kz.example.education.entities;

public class UserEntity {

    private int user_id = -1;

    private String name = "";

    private String surname;

    private int mark;

    private Float GPA;

    private String Faculty;

    private String University;

    private String Address;

    private String Country;

    private String mBannerImage;

    public String getmBannerImage() {
        return mBannerImage;
    }

    public void setmBannerImage(String mBannerImage) {
        this.mBannerImage = mBannerImage;
    }

    public String getmBannerTitle() {
        return mBannerTitle;
    }

    public void setmBannerTitle(String mBannerTitle) {
        this.mBannerTitle = mBannerTitle;
    }

    private String mBannerTitle;

    public Boolean getChecked() {
        return Checked;
    }

    public void setChecked(Boolean checked) {
        Checked = checked;
    }

    private Boolean Checked = false;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public Float getGPA() {
        return GPA;
    }

    public void setGPA(Float GPA) {
        this.GPA = GPA;
    }

    public String getFaculty() {
        return Faculty;
    }

    public void setFaculty(String faculty) {
        Faculty = faculty;
    }

    public String getUniversity() {
        return University;
    }

    public void setUniversity(String university) {
        University = university;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", mark=" + mark +
                ", GPA=" + GPA +
                ", Faculty='" + Faculty + '\'' +
                ", University='" + University + '\'' +
                ", Address='" + Address + '\'' +
                ", Country='" + Country + '\'' +
                '}';
    }
}
