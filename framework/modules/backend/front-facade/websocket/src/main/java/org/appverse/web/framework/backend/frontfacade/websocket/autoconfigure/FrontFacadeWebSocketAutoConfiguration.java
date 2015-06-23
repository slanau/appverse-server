package org.appverse.web.framework.backend.frontfacade.websocket.autoconfigure;/*
 Copyright (c) 2012 GFT Appverse, S.L., Sociedad Unipersonal.

 This Source Code Form is subject to the terms of the Appverse Public License 
 Version 2.0 (“APL v2.0”). If a copy of the APL was not distributed with this 
 file, You can obtain one at http://www.appverse.mobi/licenses/apl_v2.0.pdf. [^]

 Redistribution and use in source and binary forms, with or without modification, 
 are permitted provided that the conditions of the AppVerse Public License v2.0 
 are met.

 THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 DISCLAIMED. EXCEPT IN CASE OF WILLFUL MISCONDUCT OR GROSS NEGLIGENCE, IN NO EVENT
 SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
 INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT(INCLUDING NEGLIGENCE OR OTHERWISE) 
 ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
 POSSIBILITY OF SUCH DAMAGE.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@ConditionalOnProperty(value="appverse.frontfacade.websocket.enabled", matchIfMissing=true)
@ComponentScan("org.appverse.web.framework.backend.frontfacade.websocket")
@EnableWebSocketMessageBroker
public class FrontFacadeWebSocketAutoConfiguration extends AbstractWebSocketMessageBrokerConfigurer {
    private static final Logger logger = LoggerFactory.getLogger(FrontFacadeWebSocketAutoConfiguration.class);

    @Value("${appverse.frontfacade.websocket.defaultWebSocketEndpoint.path:/services/websocket}")
    private String defaultwebsocketEndpointPath;
    @Value("${appverse.frontfacade.websocket.simpleBrokerEndpoint.path:/topic/}")
    private String simpleBrokerEndpointPath;
    @Value("${appverse.frontfacade.websocket.queueBrokerEndpoint.path:/queue/}")
    private String queueBrokerEndpointPath;
    @Value("${appverse.frontfacade.websocket.applicationDestinationEndpoint.path:/app}")
    private String applicationDestinationEndpointPath;

    @Override
    public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
        logger.info("initializing websocket on path:'{}' ...",defaultwebsocketEndpointPath);
        stompEndpointRegistry.addEndpoint(defaultwebsocketEndpointPath).withSockJS();
    }
    @Override
    public void configureMessageBroker(final MessageBrokerRegistry registry) {
        registry.enableSimpleBroker(simpleBrokerEndpointPath, queueBrokerEndpointPath); // destination prefix
        registry.setApplicationDestinationPrefixes(applicationDestinationEndpointPath);
    }
}