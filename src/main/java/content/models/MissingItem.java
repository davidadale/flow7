package content.models;


public class MissingItem implements RepositoryItem{
    @Override
    public byte[] getContent() {
        return new byte[0];
    }

    @Override
    public String getContentType() {
        return null;
    }

    @Override
    public boolean exists() {
        return false;
    }

    @Override
    public String getPath() {
        return null;
    }
}
