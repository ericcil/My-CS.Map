package com.vcredit.persistence;

import com.alibaba.druid.pool.DruidDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;

/**
 * 数据库密码动态修改
 * @Author chenyubin
 * @Date 2018/7/12
 */
@Component
public class DBManager {

    @Autowired
    private DataSource dataSource;

    private Logger logger = LoggerFactory.getLogger(DBManager.class);

    /**
     * 修改数据源密码
     * @param newPwd
     * @return
     */
    public boolean changeDBPWD(String newPwd){

        if(dataSource instanceof DruidDataSource){
            DruidDataSource db = (DruidDataSource)dataSource;
            if( !updatePwd(db,newPwd)) return false;
            db.setPassword(newPwd);
            try {
                db.restart();
            } catch (SQLException e) {
                logger.error("数据源重启异常：",e);
            }
            return true;
        }
        return false;
    }

    private boolean updatePwd(DruidDataSource druidDataSource, String pwd) {
        try(Connection conn = DriverManager.getConnection(druidDataSource.getUrl(), druidDataSource.getUsername(),druidDataSource.getPassword());
            PreparedStatement statement = conn.prepareStatement("SET PASSWORD = PASSWORD('?')");
        ){
            // 修改密码
            statement.setString(1,pwd);
            if( !statement.execute() ) return false;
        } catch (Exception e) {
            logger.error("修改数据库密码异常了",e);
            return false;
        }

        // 重新用新修改的密码再次获取测试连接
        try(Connection conn = DriverManager.getConnection(druidDataSource.getUrl(), druidDataSource.getUsername(), pwd);
            PreparedStatement ps = conn.prepareStatement("select now()");
        ){
            ps.execute();
        } catch (SQLException e) {
            logger.error("测试修改数据库密码异常了",e );
            return false;
        }

        return true;
    }
}
