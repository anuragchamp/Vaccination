package Entity;

import java.sql.Timestamp;

public class VaccineInfo {

    public int id;

    public int userid;

    public Timestamp timestamp;

    public int doseNo;

    public String doseName;


    public VaccineInfo() {
    }

    public VaccineInfo(int id, int userid, Timestamp timestamp, int doseNo, String doseName) {
        this.id = id;
        this.userid = userid;
        this.timestamp = timestamp;
        this.doseNo = doseNo;
        this.doseName = doseName;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public int getDoseNo() {
        return doseNo;
    }

    public void setDoseNo(int doseNo) {
        this.doseNo = doseNo;
    }

    public String getDoseName() {
        return doseName;
    }

    public void setDoseName(String doseName) {
        this.doseName = doseName;
    }


    @Override
    public String toString() {
        return "VaccineInfo{" +
                "id=" + id +
                ", userid=" + userid +
                ", timestamp=" + timestamp +
                ", doseNo=" + doseNo +
                ", doseName='" + doseName + '\'' +
                '}';
    }
}
