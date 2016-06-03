package content.models;


import java.io.Serializable;

public interface RepositoryItem extends Serializable{

    public String getPath();
    public byte[] getContent();
    public String getContentType();
    public boolean exists();
}
