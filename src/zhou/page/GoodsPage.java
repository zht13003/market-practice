package zhou.page;
import zhou.tool.QueryPrint;
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
     * 数据库操作对象
     */
    private static GoodsDao goodsDao = new GoodsDao();

    /**
     * 添加商品页面
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
        boolean success = goodsDao.addGoods(good);

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
     * 更新商品页面
     */
    public static void updateGoodsPage() {
        System.out.println("\t正在执行 更改商品 操作\n");
        System.out.println("请输入想要更改的商品名字");

        int gid = QueryPrint.query("updateGoodsPage");

        System.out.println("\n--------请选择您要更改的内容\n");
        System.out.println("\t1.更改商品-名称");
        System.out.println("\t2.更改商品-价格");
        System.out.println("\t3.更改商品-数量");
        System.out.println("\n请输入选项,或者按0返回上一级菜单.");

        out:while(true) {
            String choice = scannerInfoString();
            String regex = "[0-3]";
            Goods goods = null;
            boolean success;

            if(choice.matches(regex)) {
                int info = Integer.parseInt(choice);
                switch (info) {
                    case 0:
                        MainPage.maintenancePage();
                        break;
                    case 1:
                        System.out.println("请输入商品-新名称");
                        String goodName = scannerInfoString();
                        goods = new Goods(gid, goodName);
                        break;
                    case 2:
                        System.out.println("请输入商品-新价格");
                        double goodPrice = scannerInfo();
                        goods = new Goods(gid, goodPrice);
                        break;
                    case 3:
                        System.out.println("请输入商品-新数量");
                        int goodNum = scannerNum();
                        goods = new Goods(gid, goodNum);
                        break;
                    default:
                        System.out.println("请输入正确的选择！");
                        break out;
                }

                success = goodsDao.updateGoods(info, goods);
                if (success)
                {
                    System.out.println("\n\t！！成功更新商品至数据库！！\n");
                }else
                {
                    System.err.println("\n\t！！更新商品失敗！！");
                }
                changedInfoNext("updateGoodsPage");
            }

            System.err.println("！输入有误！");
            System.out.println("请重新选择,或者按0返回上一级菜单.");
        }
    }

    /**
     * 删除商品页面
     */
    public static void deleteGoodsPage() {
        System.out.println("\t正在执行 删除商品 操作\n");
        System.out.println("请输入想要删除的商品名字");

        int gid = QueryPrint.query("deleteGoodsPage");

        while(true) {
            System.out.println("\n确认删除该商品：Y/N");
            String choice = scannerInfoString();

            if ("y".equals(choice) || "Y".equals(choice)) {
                boolean success = goodsDao.deleteGoods(gid);
                if(success) {
                    System.err.println("\t！！已成功刪除该商品！！\n");
                }
                else {
                    System.err.println("\n\t！！刪除该商品失敗！！");
                }
                changedInfoNext("deleteGoodsPage");
            }
            else {
                MainPage.maintenancePage();
            }
            System.out.println("\t!!输入有误,请重新输入!!\n");
        }
    }

    /**
     * 查询商品页面
     */
    public static void queryGoodsPage() {

    }

    /**
     * 显示所有商品
     */
    public static void displayGoodsPage() {
        System.out.println("\t\t\t\t\t所有商品列表\n\n");
        ArrayList<Goods> goodsList = goodsDao.displayGoods();

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

    public static void close() {
        goodsDao.closeConnection();
    }
}
