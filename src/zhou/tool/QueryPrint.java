package zhou.tool;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import zhou.database.DatabaseConnect;
import zhou.tool.ScannerChoice;
import zhou.entity.Goods;

/**
 * @author zhouh
 */
public final class QueryPrint {
    private static SqlSession session = DatabaseConnect.getSession();

    /**
     * 根据商品ID或商品名称来查询商品
     * @param goodID
     * @param goodName
     * @return
     */
    public static List<Goods> queryGoodsKey(int goodID, String goodName) {
        List<Goods> goodsList = new ArrayList<>();
        goodsList.add(session.selectOne("findGoodsByName", goodName));
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
        List<Goods> goodsList = QueryPrint.queryGoodsKey(gid, shopping);

        if (goodsList.size() <= 0) {
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
