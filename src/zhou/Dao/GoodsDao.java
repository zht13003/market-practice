package zhou.Dao;

import zhou.entity.Goods;
import zhou.database.DatabaseConnect;
import zhou.database.DatebaseClose;

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
            pstmt.setString(1,goods.getGoodName());
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

    public ArrayList<Goods> displayGoods() {
        ArrayList<Goods> goodsList = new ArrayList<>();
        if(this.conn == null) {
            conn = DatabaseConnect.getConnection();
        }
        String sql = "SELECT * FROM GOODS";

        try {
            pstmt = conn.prepareStatement(sql);
            rs 	  = pstmt.executeQuery();

            while(rs.next()) {
                //双引号+主键名,也可用数字表示.
                int gid = rs.getInt(1);
                String gname = rs.getString(2);
                double gprice = rs.getDouble(3);
                int gnum = rs.getInt(4);

                Goods goods = new Goods(gname, gprice, gnum);
                goodsList.add(goods);
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            DatebaseClose.queryClose(pstmt, rs, conn);
        }
        return goodsList;
    }
}
