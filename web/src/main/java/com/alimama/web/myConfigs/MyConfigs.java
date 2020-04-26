package com.alimama.web.myConfigs;

import com.alimama.web.international.MyInternationalLocalResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;

/**
 * Created by PengWX on 2020/4/26.
 */
@Configuration
public class MyConfigs {

    /**
     * 国际化
     * @return
     */
    @Bean
    public LocaleResolver localeResolver(){
        return new MyInternationalLocalResolver();
    }
}
