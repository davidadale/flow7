package content.models;

import ninja.Context;
import ninja.NinjaTest;
import org.junit.Test;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class DomainTest extends NinjaTest {


    @Test
    public void test_simple_domain(){


        Context context = mock( Context.class );
        when(context.getHostname()).thenReturn("www.flow7.net");

        Domain domain = new Domain( context );
        assertEquals(domain.getDomain(), "flow7.net");

    }

    @Test
    public void test_multi_sub_domain(){


        Context context = mock( Context.class );
        when(context.getHostname()).thenReturn("www1.w2.flow7.net");

        Domain domain = new Domain( context );
        assertEquals(domain.getDomain(), "flow7.net");

    }


    @Test
    public void test_localhost(){


        Context context = mock( Context.class );
        when(context.getHostname()).thenReturn("localhost");

        Domain domain = new Domain( context );
        assertEquals(domain.getDomain(), "localhost");

    }

    @Test
    public void test_localhost_with_port(){
        Context context = mock( Context.class );
        when(context.getHostname()).thenReturn("localhost:3000");

        Domain domain = new Domain( context );
        assertEquals(domain.getDomain(), "localhost.3000");

    }

    @Test
    public void test_path(){


        Context context = mock( Context.class );
        when(context.getHostname()).thenReturn("www.flow7.net");
        when( context.getRequestPath() ).thenReturn("/index.html");

        Domain domain = new Domain( context );
        assertEquals(domain.getDomain(), "flow7.net");
        assertEquals(domain.getPath(), "/index.html");

    }


}
