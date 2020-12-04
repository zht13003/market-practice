package zhou.page;
import zhou.tool.ScannerChoice;
import zhou.Dao.GoodsDao;
import zhou.entity.Goods;

import java.util.ArrayList;

/**
 * 操作商品界面
 * @author zhouh
 */
public class GoodsPage extends ScannerChoice{

    /**
     * 添加商品
     */
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

        changedInfoNext("addGoodsPage");
    }

    /**
     * 显示所有商品
     */
    public static void displayGoodsPage() {
        System.out.println("\t\t\t\t\t所有商品列表\n\n");
        ArrayList<Goods> goodsList = new GoodsDao().displayGoods();

        if(goodsList.size() <= 0) {
            System.err.println("！库存为空！");
            MainPage.maintenancePage();
        }
        else {
            System.out.println("\t商品编号\t\t商品名称\t\t商品价格\t\t商品数量\t\t备注\n");

            for (Goods goods : goodsList) {
                System.out.print("\t" + goods.getGid() +
                        "\t\t" + goods.getGoodName() +
                        "\t\t" + goods.getGoodPrice() +
                        " $\t\t" + goods.getGoodNum());

                int goodNum = goods.getGoodNum();
                if (goodNum == 0) {
                    System.out.println("\t\t该商品已售空！");
                } else if (goodNum < 10) {
                    System.out.println("\t\t该商品已不足10件");
                } else {
                    System.out.println("\t\t-");
                }
                System.out.println("\t");
            }
            //下一步
            System.out.println("---------------------");
            while(true) {
                System.out.println("输入 0 返回上一级菜单");
                String choice = scannerInfoString();
                if ("0".equals(choice))
                {
                    MainPage.maintenancePage();
                }
                System.out.println("输入有误！");
            }
        }
    }
}
