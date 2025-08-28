package in.co.rays.bean;

import java.sql.Timestamp;

/**
 * BaseBean is an abstract class that serves as a common parent 
 * for all beans in the application. It provides basic audit 
 * properties like id, createdBy, modifiedBy, createdDatetime, 
 * and modifiedDatetime.
 *
 * <p>
 * Other beans should extend this class to inherit these 
 * common attributes.
 * </p>
 *
 * @author   Aditya
 * @version  1.0
 * @since    2025
 */
public abstract class BaseBean implements DropdownListBean {

    protected long id;
    protected String createdBy;
    protected String modifiedBy;
    protected Timestamp createdDatetime;
    protected Timestamp modifiedDatetime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Timestamp getCreatedDatetime() {
        return createdDatetime;
    }

    public void setCreatedDatetime(Timestamp createdDatetime) {
        this.createdDatetime = createdDatetime;
    }

    public Timestamp getModifiedDatetime() {
        return modifiedDatetime;
    }

    public void setModifiedDatetime(Timestamp modifiedDatetime) {
        this.modifiedDatetime = modifiedDatetime;
    }

}
