package content;

import content.models.Domain;
import content.models.FileRepositoryItem;
import content.models.MissingItem;
import content.models.RepositoryItem;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.LinkedList;
import java.util.List;

public class FileRepository implements Repository{

    File rootFileSystem;

    public FileRepository(){
        rootFileSystem = new File(System.getProperty("user.home"),"flow7");
        if( !rootFileSystem.exists() ){
            rootFileSystem.mkdirs();
        }
    }

    @Override
    public RepositoryItem getResource( String path ) {

        Domain domain = CurrentDomain.get();

        if( !domain.isSet() ){
            return new MissingItem();
        }

        FileRepositoryItem item = new FileRepositoryItem( domain.getHost(), path );
        item.setRootFolder( rootFileSystem.getAbsolutePath() );

        if( !item.getSiteFolder().exists() ){
            item.getSiteFolder().mkdirs();
        }

        if( !item.exists() && "/".equals( path ) ){
            item = new FileRepositoryItem( domain.getHost(), "/index.html");
            if( !item.exists() ){
                item = new FileRepositoryItem( domain.getHost(), "/index.htm" );
            }
        }

        return item;

    }

    public List<RepositoryItem> getSharedLibraries(){


        File libraryDirectory = new File( rootFileSystem, "libraries" );
        if( !libraryDirectory.exists() ){
            libraryDirectory.mkdirs();
        }

        Path libraries = Paths.get( new File( rootFileSystem, "libraries" ).toURI() );

        final List<RepositoryItem> results = new LinkedList<>();
        FileVisitor<Path> fv = new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
                    throws IOException {
                System.out.println(file);
                results.add( new FileRepositoryItem( file.toFile() ) );
                return FileVisitResult.CONTINUE;
            }
        };


        try {
            Files.walkFileTree(libraries, fv);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return results;

    }

    public RepositoryItem getSharedLibrary(String path ){
        File file = new File( rootFileSystem, path );
        return new FileRepositoryItem( file );
    }

}
