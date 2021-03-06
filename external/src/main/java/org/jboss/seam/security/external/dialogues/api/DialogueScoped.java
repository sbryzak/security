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
package org.jboss.seam.security.external.dialogues.api;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.enterprise.context.NormalScope;

/**
 * <p>
 * Scope for a dialogue (flow) between the application and an external identity
 * provider or consumer.
 * </p>
 * 
 * <p>
 * The protocols for sharing identity information (e.g. SAMLv2, OpenID) have
 * quite complex dialogues, that often rely on the user agent (browser) relaying
 * messages between the identity consumer and the identity producer. When the
 * application calls an API method of Seam's SAML or OpenID submodule, the
 * application will often temporary loose control over the browser. After a
 * number of redirects, the external authentication module uses the SPI to
 * inform the application about the outcome. At that moment, the application
 * re-gains control over the browser. This round trip is modeled as a
 * "dialogue", and the dialogue CDI scope is used to manage state that is bound
 * to the dialogue. Not only the identity sharing module uses it to maintain
 * state, also the application: it can save stuff in dialogue scope before the
 * API is called, and read the stuff back in when it is called back through the
 * SPI. For example, when the user opens a page that requires authentication,
 * the view can be stored in the dialogue scope before calling login() on the
 * API. When the SPI reports back that the login succeeded, the same dialogue
 * will be active, so that the application can easily inject the saved view and
 * redirect the user to it.
 * </p>
 * 
 * <p>
 * The dialogue scope is not a passivating scope, so the contextual objects that
 * are saved in contexts of this scope do not have to be serializable. The
 * context is stored in a servlet context attribute.
 * </p>
 * 
 * @author Marcel Kolsteren
 */
@Documented
@Retention(RUNTIME)
@Target( { TYPE, METHOD, FIELD })
@NormalScope(passivating = false)
public @interface DialogueScoped
{

}
