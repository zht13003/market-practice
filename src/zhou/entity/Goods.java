package zhou.entity;

/**
 * Goods 商品实体类
 * @author zhouh
 */
public class Goods {
    /**
     * 数据库Goods表主鍵
     */
    private int gid;
    private String goodName;
    private double goodPrice;
    private int goodNum;

    /**
     * 添加商品信息
     * @param goodName,gprice,gum
     */
    public Goods(String goodName,double goodPrice,int goodNum)
    {
        this.goodName  = goodName;
        this.goodPrice = goodPrice;
        this.goodNum 	= goodNum;
    }

    public Goods(int gid,String goodName,double goodPrice,int goodNum) {
        this.gid = gid;
        this.goodName  = goodName;
        this.goodPrice = goodPrice;
        this.goodNum 	= goodNum;
    }

    public Goods(int gid,String goodName) {
        this.gid = gid;
        this.goodName  = goodName;
    }

    public Goods(int gid,double goodPrice) {
        this.gid = gid;
        this.goodPrice = goodPrice;
    }

    public Goods(int gid,int goodNum) {
        this.gid = gid;
        this.goodNum 	= goodNum;
    }

    public int getGid()
    {
        return gid;
    }
    public void setGid(int gid)
    {
        this.gid = gid;
    }
    public String getGoodName()
    {
        return goodName;
    }
    public void setGoodName(String gname)
    {
        this.goodName = gname;
    }
    public double getGoodPrice()
    {
        return goodPrice;
    }
    public void setGoodPrice(double gprice)
    {
        this.goodPrice = gprice;
    }
    public int getGoodNum()
    {
        return goodNum;
    }
    public void setGoodNum(int gnum)
    {
        this.goodNum = gnum;
    }
}