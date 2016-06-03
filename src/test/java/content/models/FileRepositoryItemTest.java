package content.models;


import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class FileRepositoryItemTest {

    @Test
    public void test_default_site_folder(){
        File siteFolder = new File( new File(System.getProperty("user.home"),"flow7") , "www.drive-cleaners.com"  );
        FileRepositoryItem fri = new FileRepositoryItem("www.drive-cleaners.com","");
        assertEquals( fri.getSiteFolder(),  siteFolder );
    }


}
