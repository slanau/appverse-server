package org.appverse.web.framework.backend.frontfacade.mvc.swagger.autoconfigure;

import java.util.ArrayList;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;
import com.mangofactory.swagger.models.dto.ApiInfo;

import org.ajar.swaggermvcui.SwaggerSpringMvcUi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@EnableSwagger
@Configuration
@ConditionalOnClass(SwaggerSpringMvcUi.class)
public class SwaggerAutoConfiguration implements EnvironmentAware {

  private SpringSwaggerConfig springSwaggerConfig;
  
  private RelaxedPropertyResolver propertyResolver;
  
  @Override
  public void setEnvironment(Environment environment) {
      this.propertyResolver = new RelaxedPropertyResolver(environment, "swagger.");
  }

  @Autowired
  public void setSpringSwaggerConfig(SpringSwaggerConfig springSwaggerConfig) {
    this.springSwaggerConfig = springSwaggerConfig;
  }

  @Bean
  public SwaggerSpringMvcPlugin swaggerSpringMvcPlugin() {
    return new SwaggerSpringMvcPlugin(springSwaggerConfig)
    		/* Example: including groups and patterns
    		.swaggerGroup("group")
            .includePatterns(
                    "/business.*",
                    "/some.*",
                    "/contacts.*",
                    "/pet.*",
                    "/springsRestController.*",
                    "/test.*"
            )
            */
    		.includePatterns(getIncludePatterns())
            .apiInfo(apiInfo())
            /* TODO: Review this when we enable OAUth2 in Swagger
            .authorizationTypes(authorizationTypes())
            .authorizationContext(authorizationContext())
            */
            .build();
  }

  /**
   * Patterns to be included to appear in the swagger-ui page
   */
  private String[] getIncludePatterns(){
	  String includePatterns = propertyResolver.getProperty("includePatterns");
	  if (includePatterns == null){
		  String[] pattern = {".*?"};
		  return pattern;
	  }
	  return includePatterns.split(",");		 
  }

  /**
   * API Info as it appears on the swagger-ui page
   */
  private ApiInfo apiInfo() {
	/*
    ApiInfo apiInfo = new ApiInfo(
            "Demo Spring MVC swagger 1.2 api",
            "Sample spring mvc api based on the swagger 1.2 spec",
            "http://en.wikipedia.org/wiki/Terms_of_service",
            "somecontact@somewhere.com",
            "Apache 2.0",
            "http://www.apache.org/licenses/LICENSE-2.0.html"
    );
    */
      return new ApiInfo(
              propertyResolver.getProperty("title"),
              propertyResolver.getProperty("description"),
              propertyResolver.getProperty("termsOfServiceUrl"),
              propertyResolver.getProperty("contact"),
              propertyResolver.getProperty("license"),
              propertyResolver.getProperty("licenseUrl"));
  }

/* TODO: Review this when we enable OAUth2 in Swagger
  private List<AuthorizationType> authorizationTypes() {
    ArrayList<AuthorizationType> authorizationTypes = new ArrayList<AuthorizationType>();

    List<AuthorizationScope> authorizationScopeList = newArrayList();
    authorizationScopeList.add(new AuthorizationScope("global", "access all"));

    List<GrantType> grantTypes = newArrayList();

    LoginEndpoint loginEndpoint = new LoginEndpoint("http://petstore.swagger.wordnik.com/api/oauth/dialog");
    grantTypes.add(new ImplicitGrant(loginEndpoint, "access_token"));

    TokenRequestEndpoint tokenRequestEndpoint = new TokenRequestEndpoint("http://petstore.swagger.wordnik.com/oauth/requestToken", "client_id", "client_secret");
    TokenEndpoint tokenEndpoint = new TokenEndpoint("http://petstore.swagger.wordnik.com/oauth/token", "auth_code");

    AuthorizationCodeGrant authorizationCodeGrant = new AuthorizationCodeGrant(tokenRequestEndpoint, tokenEndpoint);
    grantTypes.add(authorizationCodeGrant);

    OAuth oAuth = new OAuthBuilder()
            .scopes(authorizationScopeList)
            .grantTypes(grantTypes)
            .build();

    authorizationTypes.add(oAuth);
    return authorizationTypes;
  }

  @Bean
  public AuthorizationContext authorizationContext() {
    List<Authorization> authorizations = newArrayList();

    AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
    AuthorizationScope[] authorizationScopes = new AuthorizationScope[]{authorizationScope};
    authorizations.add(new Authorization("oauth2", authorizationScopes));
    AuthorizationContext authorizationContext =
            new AuthorizationContext.AuthorizationContextBuilder(authorizations).build();
    return authorizationContext;
  }
*/
  @Bean
  public MultipartResolver multipartResolver() {
    CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
    multipartResolver.setMaxUploadSize(500000);
    return multipartResolver;
  }

}