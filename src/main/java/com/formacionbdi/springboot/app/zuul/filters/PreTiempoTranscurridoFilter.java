package com.formacionbdi.springboot.app.zuul.filters;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/*
 * @Component: registrar como componente
 * Y heredar de ZuulFilter
 */
@Component
public class PreTiempoTranscurridoFilter extends ZuulFilter {
	
	private Logger log = LoggerFactory.getLogger(PreTiempoTranscurridoFilter.class);
	
	/*
	 * Validar si se ejecuta el filtro
	 */
	@Override
	public boolean shouldFilter() {
		return true;
	}

	/*
	 * Cuando se resuelve la ruta
	 */
	@Override
	public Object run() throws ZuulException {
		/*
		 * obtener el request
		 */
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		log.info(String.format("%s request enrutado a %s", request.getMethod(), request.getRequestURL().toString()));
		
		Long tiempoInicio = System.currentTimeMillis();
		request.setAttribute("tiempoInicio", tiempoInicio);
		return null;
	}
	
	/*
	 * Filtro del tipo PRE antes de que se resuelva la ruta
	 */
	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

}
