/*
 * JBoss, Home of Professional Open Source
 * Copyright 2010, Red Hat, Inc., and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.jboss.seam.security.external.saml.sp;

import org.jboss.seam.security.external.saml.api.SamlPrincipal;
import org.jboss.seam.security.external.saml.api.SamlSpSession;

/**
 * @author Marcel Kolsteren
 * 
 */
public class SamlSpSessionImpl implements SamlSpSession
{
   private SamlPrincipal principal;

   private String sessionIndex;

   private SamlExternalIdentityProvider identityProvider;

   public SamlPrincipal getPrincipal()
   {
      return principal;
   }

   public void setPrincipal(SamlPrincipal samlPrincipal)
   {
      this.principal = samlPrincipal;
   }

   public String getSessionIndex()
   {
      return sessionIndex;
   }

   public void setSessionIndex(String sessionIndex)
   {
      this.sessionIndex = sessionIndex;
   }

   public SamlExternalIdentityProvider getIdentityProvider()
   {
      return identityProvider;
   }

   public void setIdentityProvider(SamlExternalIdentityProvider identityProvider)
   {
      this.identityProvider = identityProvider;
   }

}
