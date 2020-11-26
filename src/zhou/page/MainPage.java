package zhou.page;

import zhou.tool.ScannerChoice;
import zhou.page.GoodsPage;
/**
 * @author zhouh
 */
public class MainPage extends ScannerChoice{
    public static void main(String[] args) {
        mainPage();
    }

    public static void mainPage() {
        System.out.println("***************************\n");
        System.out.println("\t 1.商品维护\n");
        System.out.println("\t 2.前台收银\n");
        System.out.println("\t 3.商品管理\n");
        System.out.println("***************************");

        System.out.println("\n请输入选项,或者按0退出.");

        while (true) {
            String choice = scannerInfoString();
            String regex = "[0-3]";
            //利用正则表达式对输入进行匹配
            if(choice.matches(regex)) {
                int info = Integer.parseInt(choice);
                switch (info) {
                    case 0:
                        System.out.println("------------------");
                        System.out.println("您已经退出系统!");
                        System.exit(1);
                        break;
                    case 1:
                        //商品维护界面
                        maintenancePage();
                        break;
                    case 2:
                        //前台收银界面
                        checkStandLogPage();
                        break;
                    case 3:
                        //商品管理界面
                        commodityManagementPage();
                        break;
                    default:
                        break;
                }
            }
            System.err.println("!输入有误!");
            System.out.println("重新选择或者按0退出.");
        }
    }

    /**
     * 商品维护界面
     */
    public static void maintenancePage() {
        System.out.println("***************************\n");
        System.out.println("\t 1.添加商品\n");
        System.out.println("\t 2.更改商品\n");
        System.out.println("\t 3.删除商品\n");
        System.out.println("\t 4.查询商品\n");
        System.out.println("\t 5.显示所有商品\n");
        System.out.println("***************************");

        System.out.println("\n请输入选项,或者按 0 返回上一级菜单.");
        while(true) {
            String choice = scannerInfoString();
            String regex = "[0-5]";
            if (choice.matches(regex))
            {
                int info = Integer.parseInt(choice);
                switch (info)
                {
                    case 0:
                        mainPage();
                        break;
                    case 1:
                        GoodsPage.addGoodsPage();
                        break;
                    case 2:
                        //更改商品
                        break;
                    case 3:
                        //删除商品
                        break;
                    case 4:
                        //查询商品
                        break;
                    case 5:
                        //显示所有商品
                        break;
                    default:
                        break;
                }
            }
            System.err.println("!输入有误!");
            System.out.println("重新输入或按 0 返回上一级菜单.");
        }
    }

    /**
     * 收银登录界面
     */
    public static void checkStandLogPage() {
        System.out.println("\n*******欢迎使用商超购物管理系统*******\n");
        System.out.println("\t 1.登录系统\n");
        System.out.println("\t 2.退出\n");
        System.out.println("-----------------------------");
        System.out.println("请输入选项,或者按 0 返回上一级菜单.");

        while(true) {
            String choice = scannerInfoString();
            String regex = "[0-2]";
            if (choice.matches(regex))
            {
                int info = Integer.parseInt(choice);
                switch (info)
                {
                    case 0:
                        mainPage();
                        break;
                    case 1:
                        //登录系统
                        break;
                    case 2:
                        //退出系统
                        System.out.println("------------------");
                        System.out.println("您已经退出系统!");
                        System.exit(-1);
                        break;
                    default:
                        break;
                }
            }
            System.err.println("!输入有误!");
            System.out.println("重新输入或按 0 返回上一级菜单.");
        }
    }

    /**
     *商品管理界面
     */
    public static void commodityManagementPage() {
        System.out.println("***************************\n");
        System.out.println("\t 1.售货员管理\n");
        System.out.println("\t 2.列出当日卖出列表\n");
        System.out.println("***************************");

        System.out.println("\n请输入选项,或者按 0 返回上一级菜单.");
        while(true) {
            String choice = scannerInfoString();
            String regex = "[0-2]";
            if (choice.matches(regex))
            {
                int info = Integer.parseInt(choice);
                switch (info)
                {
                    case 0:
                        mainPage();
                        break;
                    case 1:
                        //售货员管理
                        break;
                    case 2:
                        //列出当日卖出列表
                        break;
                    default:
                        break;
                }
            }
            System.err.println("!输入有误!");
            System.out.println("重新输入或按 0 返回上一级菜单.");
        }
    }
}
