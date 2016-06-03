package content.models;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileRepositoryItem implements RepositoryItem {


    String rootFolder;
    String path;
    String host;
    File file;

    public FileRepositoryItem( String host, String path ){
        this.host = host;
        this.path = path;
    }

    public FileRepositoryItem( File file ){
        this.file = file;
    }

    @Override
    public byte[] getContent() {

        try {

            return IOUtils.toByteArray( new FileInputStream( file ) );

        } catch (IOException e) {
            e.printStackTrace();
        }

        return new byte[0];
    }

    @Override
    public String getContentType() {
        return null;
    }

    public String getRootFolder() {

        if( rootFolder == null ){
            rootFolder = new File(
                    System.getProperty("user.home"),
                    "flow7").getAbsolutePath();
        }

        return rootFolder;
    }

    public File getRootFolderAsFile(){
        return new File( getRootFolder() );
    }

    public void setRootFolder(String rootFolder) {
        this.rootFolder = rootFolder;
    }

    @Override
    public String getPath() {
        return getRootFolderAsFile().toURI().relativize( file.toURI() ).getPath();
        //return file.toURI().relativize( getRootFolderAsFile().toURI() ).getPath();
    }

    @Override
    public boolean exists(){
        if( file == null){
            file = new File( getSiteFolder(), path );
        }

        System.out.println("File path exists " + file.getAbsolutePath() );

        return file.exists() && !file.isDirectory();
    }


    public File getSiteFolder(){
        return new File( new File(getRootFolder()), host );
    }

}
