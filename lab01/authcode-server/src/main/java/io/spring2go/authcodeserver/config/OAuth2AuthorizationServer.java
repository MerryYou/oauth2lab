package io.spring2go.authcodeserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

//授权服务器配置
@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationServer extends
        AuthorizationServerConfigurerAdapter {

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
            .withClient("testclientapp")
             /** 支持的加密方式详情见 {@link PasswordEncoderFactories} */
            .secret("{bcrypt}" + new BCryptPasswordEncoder().encode("123456"))
            .redirectUris("http://localhost:9001/callback")
            // 授权码模式
            .authorizedGrantTypes("authorization_code", "password", "refresh_token")
            .scopes("read_userinfo", "read_contacts");
    }

}
