package content.models;


import ninja.Context;

public class Domain {

    String host;
    String domain;
    String path;

    public Domain(){

    }

    public Domain(Context context){
        this( context.getHostname(), context.getRequestPath() );
    }

    public Domain(String host, String path){
        this.host = parseHost( host );
        this.domain = parseDomain( host );
        this.path = path;
    }

    public String getDomain(){
        return domain;
    }

    public String getHost(){
        return host;
    }

    public String getPath(){
        return path;
    }

    protected String parseDomain(String host){
        String cleanHost = parseHost( host );
        String[] domainGroups = cleanHost.split("\\.");
        if( domainGroups.length > 1 ){
            String second = domainGroups[ domainGroups.length - 1 ];
            String first  = domainGroups[ domainGroups.length - 2 ];
            return first + "." + second;
        }

        return cleanHost;
    }

    protected String parseHost( String host ){
        return host.replace(":",".");
    }


    public boolean isSet(){
        return (host != null && !host.contains("localhost"));
    }

}

