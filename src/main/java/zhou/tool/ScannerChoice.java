package zhou.tool;

import zhou.page.GoodsPage;
import zhou.page.MainPage;

import java.util.Scanner;

/**
 * @author zhouh
 */
public class ScannerChoice {
    public static String scannerInfoString() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入：");
        return scanner.next();
    }

    public static double scannerInfo() {
        double num = 0.00;
        do
        {
            Scanner sc = new Scanner(System.in);
            System.out.print("保留小数点后两位,请输入：");
            String info = sc.next();

            String regex = "(([1-9][0-9]*)\\.([0-9]{2}))|[0]\\.([0-9]{2})";
            //保留小数点后2位小数
            if (info.matches(regex))
            {
                num = Double.parseDouble(info);
            }else
            {
                System.err.println("！输入有误！");
                continue;
            }
            break;
        } while (true);

        return num;
    }
    public static int scannerNum() {
        int num = 0;
        String regex = "([1-9])|([1-9][0-9]+)";
        //商品数量
        do
        {
            Scanner sc = new Scanner(System.in);
            System.out.print("请输入：");
            String nums = sc.next();

            if (nums.matches(regex))
            {
                num = Integer.parseInt(nums);
            }else
            {
                System.err.println("！输入有误！");
                continue;
            }
            break;
        } while (true);
        return num;
    }

    public static void changedInfoNext(String next) {
        while(true) {
            System.out.println("是否继续进行-当前操作:(Y/N)");
            String choice = scannerInfoString();

            if("Y".equals(choice.toUpperCase())) {
                if("addGoodsPage".equals(next)) {
                    GoodsPage.addGoodsPage();
                }
                if("updateGoodsPage".equals(next)) {
                    GoodsPage.updateGoodsPage();
                }
                if("deleteGoodsPage".equals(next)) {
                    GoodsPage.deleteGoodsPage();
                }
            }
            else if("N".equals(choice.toUpperCase())) {
                MainPage.maintenancePage();
            }
            System.out.println("\n输入有误！请重新输入.");
        }
    }

}
