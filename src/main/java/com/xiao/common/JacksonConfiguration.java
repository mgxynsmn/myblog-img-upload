package com.xiao.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * Jackson全局配置
 */
@Configuration
public class JacksonConfiguration {

    /**
     * 配置Spring MVC的Json转换器
     */
    @Bean
    public HttpMessageConverters jacksonConverters() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder()
                //.serializationInclusion(JsonInclude.Include.NON_NULL)
                .indentOutput(true)                                                 //输出时自动缩进
                .dateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))    //日期时间格式化
                //.dateFormat(new SimpleDateFormat("yyyy-MM-dd"))                   //日期时间格式化
                .serializerByType(Long.TYPE, new ToStringSerializer())              //long转String
                .serializerByType(Long.class, new ToStringSerializer())             //Long转String
                ;
        ObjectMapper objectMapper = builder.build();
        //SerializerProvider serializerProvider = objectMapper.getSerializerProvider();
        //serializerProvider.setNullValueSerializer(new NullSerializer());
        return new HttpMessageConverters(new MappingJackson2HttpMessageConverter(objectMapper));
    }

    /**
     * 配置@Autowired的ObjectMapper对象
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jacksonCustomizer() {
        return new Jackson2ObjectMapperBuilderCustomizer() {
            @Override
            public void customize(Jackson2ObjectMapperBuilder builder) {
                builder.indentOutput(true);
                builder.dateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
                builder.serializerByType(Long.TYPE, new ToStringSerializer());
                builder.serializerByType(Long.class, new ToStringSerializer());
            }
        };
    }


    /**
     * null值序列化类
     */
    public class NullDeserializer extends JsonDeserializer<Object> {
        @Override
        public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            return "";
        }
    }

}


