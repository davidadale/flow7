package content.filters;

import content.CurrentDomain;
import content.models.Domain;
import ninja.*;


public class DomainFilter implements Filter{

    @Override
    public Result filter(FilterChain filterChain, Context context) {

        Result result = null;
        Domain domain = null;

        try{

            String host = context.getSession().get("_host");

            if( host!=null && !"".equals(host) ){
                domain = new Domain( host, context.getRequestPath() );
            }else{
                domain = new Domain( context );
            }

            if( "GET".equalsIgnoreCase( context.getMethod() ) &&
                    !domain.isSet() &&
                    !"/".equals(context.getRequestPath() )){
                return Results.redirect("/");
            }

            CurrentDomain.set( domain );

            System.out.println( "Domain " + CurrentDomain.get().getDomain() );
            result = filterChain.next(context);

        }finally{
            CurrentDomain.reset();
        }

        return result;

    }
}
