package org.jboss.seam.security.external.openid;

import java.util.LinkedList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.Logger;
import org.jboss.seam.security.Authenticator;
import org.jboss.seam.security.external.openid.api.OpenIdRelyingPartyApi;
import org.jboss.seam.security.external.openid.api.OpenIdRequestedAttribute;
import org.jboss.seam.security.external.openid.providers.OpenIdProvider;

/**
 * 
 * @author Shane Bryzak
 *
 */
public @Named("openIdAuthenticator") @RequestScoped class OpenIdAuthenticator implements Authenticator
{
   private String openIdProviderUrl;
   
   @Inject private OpenIdRelyingPartyApi openIdApi;
   
   @Inject List<OpenIdProvider> providers;
   
   @Inject Logger log;
   
   private String providerCode;
   
   public String getProviderCode()
   {
      return providerCode;
   }
   
   public void setProviderCode(String providerCode)
   {
      this.providerCode = providerCode;
   }
   
   public String getOpenIdProviderUrl()
   {
      return openIdProviderUrl;
   }
   
   public void setOpenIdProviderUrl(String openIdProviderUrl)
   {
      this.openIdProviderUrl = openIdProviderUrl;
   }
   
   protected OpenIdProvider getSelectedProvider()
   {
      if (providerCode != null)
      {
         for (OpenIdProvider provider : providers)
         {
            if (providerCode.equals(provider.getCode())) return provider;
         }
      }
      return null;
   }
   
   public AuthStatus authenticate()
   {
      List<OpenIdRequestedAttribute> attributes = new LinkedList<OpenIdRequestedAttribute>();
      attributes.add(openIdApi.createOpenIdRequestedAttribute("email", "http://schema.openid.net/contact/email", false, null));
      
      OpenIdProvider selectedProvider = getSelectedProvider();
      String url = selectedProvider != null ? selectedProvider.getUrl() : getOpenIdProviderUrl();
      
      if (log.isDebugEnabled()) log.debug("Logging in using OpenID url: " + url);
      
      openIdApi.login(url, attributes, 
            (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse());      
      
      return AuthStatus.DEFERRED;
   }
   
   public List<OpenIdProvider> getProviders()
   {
      return providers;
   }

}
