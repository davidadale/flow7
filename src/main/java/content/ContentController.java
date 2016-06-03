package content;

import com.google.inject.Inject;
import content.filters.DomainFilter;
import content.models.RepositoryItem;
import ninja.Context;
import ninja.FilterWith;
import ninja.Result;
import ninja.Results;
import ninja.utils.NinjaProperties;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@FilterWith( DomainFilter.class )
public class ContentController {

    @Inject
    NinjaProperties ninjaProperties;

    FileRepository repo = new FileRepository();

    public Result changeDomain(Context context){
        String host = context.getParameter("host");
        System.out.println ("Domain is " + host + "_______ " + host );
        context.getSession().put("_host", host );
        return Results.redirect("/");
    }

    public Result clearDomain(Context context){
        context.getSession().remove( "_host" );
        Map<String,String> data = new HashMap<>();
        data.put("location","/");
        return Results.json().render( data  );
    }

    public Result render(Context context ){

        RepositoryItem item = repo.getResource( context.getRequestPath() );

        if( !item.exists() ){
            item = repo.getResource( "/_404.html");
        }

        if( item.exists() ){
            return Results.ok().renderRaw(item.getContent()).contentType("text/html");
        }

        if( !item.exists() && "/".equals( context.getRequestPath() ) ){
            return Results.html().template("content/views/render.ftl.html");
        }else{
            return Results.html().template("views/system/404notFound.ftl.html");
        }

    }

    public Result libraries(Context context ){
        RepositoryItem item = repo.getSharedLibrary( context.getRequestPath() );
        return Results.ok().renderRaw(item.getContent()).contentType("text/html");
    }

    //TODO: This should be secured down to site admins and system administrators
    public Result dashboard( Context context ){
        List<RepositoryItem> libraries = repo.getSharedLibraries();
        return Results.html().template("content/views/dashboard.ftl.html").render("libraries",libraries );
    }


}
