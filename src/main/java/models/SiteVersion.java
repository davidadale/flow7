package models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


public class SiteVersion {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public Long id;
    public String label;
    public String host;
    // naked domain or www
    public Boolean active = false;

}
