package zhou.page;
import zhou.tool.ScannerChoice;
import zhou.Dao.GoodsDao;
import zhou.entity.Goods;

/**
 * 操作商品界面
 * @author zhouh
 */
public class GoodsPage extends ScannerChoice{

    public static void addGoodsPage() {
        System.out.println("\t正在执行添加商品操作\n");

        System.out.println("\n请输入添加商品-名称");
        String goodsName = scannerInfoString();

        System.out.println("\n请输入添加商品-价格");
        double goodsPrice = scannerInfo();

        System.out.println("\n请输入添加商品-数量");
        int goodsNumber = scannerNum();

        Goods good = new Goods(goodsName, goodsPrice, goodsNumber);
        boolean success = new GoodsDao().addGoods(good);

        if (success)
        {
            System.out.println("\n\t!您已成功添加商品到数据库!");
        }else
        {
            System.out.println("添加商品失败");
        }
        System.exit(1);
    }
}
