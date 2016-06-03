package content;


import content.models.Domain;

public class CurrentDomain {

    static final ThreadLocal<Domain> DOMAIN_HOLDER = new ThreadLocal<>();

    public static void set( Domain domain ){
        DOMAIN_HOLDER.set( domain );
    }

    public static Domain get(){
        return DOMAIN_HOLDER.get();
    }

    public static void reset(){
        DOMAIN_HOLDER.set( null );
    }

}
