package zhou.Dao;

import zhou.entity.Goods;
import zhou.database.DatabaseConnect;
import zhou.database.DatebaseClose;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * @author zhouh
 */
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
            pstmt.setString(1, goods.getGoodName());
            pstmt.setDouble(2, goods.getGoodPrice());
            pstmt.setInt(3, goods.getGoodNum());

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

    /**
     * 显示所有商品信息
     * @return
     */
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

    /**
     * 更新商品信息
     * @param key=1：更改商品名称，2：更改商品价格，3：更改商品数量
     * @param goods
     * @return
     */
    public boolean updateGoods(int key, Goods goods) {
        boolean bool = false;
        if(this.conn == null) {
            conn = DatabaseConnect.getConnection();
        }

        String sql;
        try {
            switch (key) {
                case 1:
                    sql = "UPDATE GOODS SET GNAME=? WHERE GID=?";
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, goods.getGoodName());
                    break;
                case 2:
                    sql = "UPDATE GOODS SET GPRICE=? WHERE GID=?";
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setDouble(1, goods.getGoodPrice());
                    break;
                case 3:
                    sql = "UPDATE GOODS SET GNUM=? WHERE GID=?";
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setDouble(1, goods.getGoodNum());
                    break;
                default:
                    break;
            }
            pstmt.setInt(2,goods.getGid());

            int rs = pstmt.executeUpdate();
            if(rs > 0) {
                bool = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatebaseClose.addClose(pstmt, conn);
        }
        return bool;
    }

    /**
     * 删除商品
     * @param gid
     * @return
     */
    public boolean deleteGoods(int gid) {
        boolean bool = false;
        if(this.conn == null) {
            conn = DatabaseConnect.getConnection();
        }
        String sql = "DELETE FROM GOODS WHERE GID=?";

        try
        {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,gid);
            int rs = pstmt.executeUpdate();
            if (rs > 0)
            {
                bool = true;
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }finally{
            DatebaseClose.addClose(pstmt,conn);
        }
        return bool;
    }
}
