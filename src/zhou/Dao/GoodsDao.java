package zhou.Dao;

import org.apache.ibatis.session.SqlSession;
import zhou.entity.Goods;
import zhou.database.DatabaseConnect;
import zhou.tool.ScannerChoice;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhouh
 */
public class GoodsDao {

    SqlSession session = DatabaseConnect.getSession();

    /**
     * 添加商品数据到goods表
     */
    public boolean addGoods(Goods goods) {
        session.insert("addGoods", goods);
        return true;
    }

    /**
     * 显示所有商品信息
     * @return
     */
    public List<Goods> displayGoods() {
        return session.selectList("listGoods");
    }

    /**
     * 更新商品信息
     * @param key=1：更改商品名称，2：更改商品价格，3：更改商品数量
     * @param goods
     * @return
     */
    public boolean updateGoods(int key, Goods goods) {
        switch (key) {
            case 1:
                session.update("updateGoodsName", goods);
                break;
            case 2:
                session.update("updateGoodsPrice", goods);
                break;
            case 3:
                session.update("updateGoodsNum", goods);
                break;
            default:
                break;
        }
        return true;
    }

    /**
     * 删除商品
     * @param gid
     * @return
     */
    public boolean deleteGoods(int gid) {
        session.delete("deleteGoods", gid);
        return true;
    }

    /**
     * 查询商品
     * @param key
     * @return
     */
    public List<Goods> queryGoods(int key) {
        List<Goods> goodsList = new ArrayList<>();
        switch (key) {
            case 1:
                goodsList = session.selectList("queryGoodsByPriceOrNum", "good_num");
                break;
            case 2:
                goodsList = session.selectList("queryGoodsByPriceOrNum", "good_price");
                break;
            case 3:
                String nameGet = ScannerChoice.scannerInfoString();
                goodsList = session.selectList("fuzzyFindGoodsByName", nameGet);
                break;
            default:
                break;
        }
        return goodsList;
    }
    public void close() {
        session.commit();
        session.close();
    }

}
