package com.hly.springbootmybatismultidatasources.config;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import javax.sql.DataSource;

/**
 * @author :hly
 * @github :https://github.com/huangliangyun
 * @blog :http://www.javahly.com/
 * @CSDN :blog.csdn.net/Sirius_hly
 * @date :2019/4/9
 */
@Configuration
//配置mbatis接口
@MapperScan(basePackages = "com.hly.springbootmybatismultidatasources.dao.articleService", sqlSessionFactoryRef = "articleServiceSqlSessionFactory")
public class ArticleServiceDataSourceConfig {

    //将对象放入容器中
    @Bean(name = "articleServiceDataSource")
    //表示默认数据源
    @Primary
    //yml配置的对象
    @ConfigurationProperties(prefix = "spring.datasource.article-service")
    public DataSource getArticleServiceDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "articleServiceSqlSessionFactory")
    @Primary
    //@Qualifier 查找Spring 容器中名为articleServiceDataSource的对象
    public SqlSessionFactory articleServiceSqlSessionFactory(@Qualifier("articleServiceDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        //配置Mybatis XML文件的位置
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/articleService/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "articleServiceSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate articleServiceSqlSessionTemplate(@Qualifier("articleServiceSqlSessionFactory") SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
