package zhou.Dao;

import zhou.entity.Goods;
import zhou.database.DatabaseConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GoodsDao {

    Connection conn  = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    /**
     * 添加商品数据到goods表
     */
    public boolean addGoods(Goods goods) {
        boolean success = false;

        //存在多个表时，此处需要更改
        if(this.conn == null) {
            conn = DatabaseConnect.getConnection();
        }

        String sql = "INSERT INTO GOODS(GNAME,GPRICE,GNUM) VALUES(?,?,?)";
        try
        {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,goods.getGoogName());
            pstmt.setDouble(2,goods.getGoodPrice());
            pstmt.setInt(3,goods.getGoodNum());

            int rs = pstmt.executeUpdate();
            if (rs > 0)
            {
                success = true;
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return success;
    }
}
