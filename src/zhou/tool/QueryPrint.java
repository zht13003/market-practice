package zhou.tool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import zhou.database.DatabaseConnect;
import zhou.database.DatebaseClose;
import zhou.tool.ScannerChoice;
import zhou.entity.Goods;

/**
 * @author zhouh
 */
public final class QueryPrint {
    Connection conn  = DatabaseConnect.getConnection();
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    /**
     * 根据商品ID或商品名称来查询商品
     * @param goodID
     * @param goodName
     * @return
     */
    public ArrayList<Goods> queryGoodsKey(int goodID, String goodName) {
        ArrayList<Goods> goodsList = new ArrayList<>();

        String sql = "SELECT * FROM GOODS WHERE GID=? OR GNAME=?";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, goodID);
            pstmt.setString(2, goodName);
            rs = pstmt.executeQuery();
            while (rs.next())
            {
                int gid = rs.getInt("gid");
                String gname = rs.getString(2);
                double gprice = rs.getDouble(3);
                int gnum = rs.getInt(4);

                Goods goods = new Goods(gid,gname,gprice,gnum);
                goodsList.add(goods);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return goodsList;
    }

    /**
     * 查询商品
     * @param operator
     * @return
     */
    public static int query(String operator) {
        int gid = -1;
        String shopping = ScannerChoice.scannerInfoString();
        ArrayList<Goods> goodsList = new QueryPrint().queryGoodsKey(gid, shopping);

        if (goodsList == null || goodsList.size() <= 0) {
            System.err.println("\t！！查无此商品 ！！");
            ScannerChoice.changedInfoNext(operator);
        }
        else {
            Goods goods = goodsList.get(0);

            System.out.println("\t\t\t\t\t商品列表\n\n");
            System.out.println("\t商品编号\t\t商品名称\t\t商品价格\t\t商品数量\t\t备注\n");
            System.out.print("\t" + goods.getGid() +
                    "\t\t" + goods.getGoodName() +
                    "\t\t" + goods.getGoodPrice() +
                    "\t\t" + goods.getGoodNum());
            if (goods.getGoodNum()==0)
            {
                System.out.println("\t\t该商品已售空");
            }else if (goods.getGoodNum()<10)
            {
                System.out.println("\t\t该商品已不足10件");
            }else
            {
                System.out.println("\t\t-");
            }
            gid = goods.getGid();
        }
        return gid;
    }
}
