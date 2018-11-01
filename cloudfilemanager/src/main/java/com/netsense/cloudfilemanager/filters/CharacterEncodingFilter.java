package com.netsense.cloudfilemanager.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

@WebFilter(urlPatterns = "/*",filterName="CharacterEncodeFilter",
        initParams={
                @WebInitParam(name="encoding",value="UTF-8"),
                @WebInitParam(name = "forceEncoding", value = "true")
        })
//@Singleton
public class CharacterEncodingFilter implements Filter {

    private String encoding = "UTF-8";
    private boolean forceEncoding = false;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.encoding = filterConfig.getInitParameter("encoding");
        String force = filterConfig.getInitParameter("forceEncoding");
        this.forceEncoding = (force == null) || Boolean.valueOf(force);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (this.forceEncoding || request.getCharacterEncoding() == null) {
            request.setCharacterEncoding(this.encoding);
            response.setCharacterEncoding(this.encoding);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public void setForceEncoding(boolean forceEncoding) {
        this.forceEncoding = forceEncoding;
    }

}
