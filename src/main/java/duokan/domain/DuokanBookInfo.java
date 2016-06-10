package duokan.domain;

/**
 * Created by kimown on 2015/11/22.
 * jQuery Selector: $('.j-bookitm')
 */

public class DuokanBookInfo {
    /**
     * 书籍编号
     * $('.j-bookitm .book')[0].getAttribute('data-id')
     */
    private String bookId;


    /**
     * 封面图片地址
     * $('.j-bookitm .book .cover img[src^="http://cover.read.duokan.com"]')[0].src
     */
    private String coverURL;

    /**
     * 点击封面图片,跳转的地址
     *
     * $('.j-bookitm .book a')[0].getAttribute('href')
     */
    private String href;

    /**
     * 图书名字
     * $('.j-bookitm .info .title')[0].innerHTML
     */
    private String title;

    /**
     * 真实购买价格
     *$('.j-bookitm .book .u-price em').html()
     */
    private double buyPrice;

    /**
     * 原价
     * $('.j-bookitm .book .u-price del').html()
     */
    private double originalPrice;

    /**
     * 图书星级（得分0-10分，对应0-5星）
     * var a = $('.j-bookitm .info .u-stargrade div')[0].className;
     * a.split("icon grade")[1]
     */
    private int stargrade;

    /**
     * 根据图书星级，进行排序，例如搜索引擎的优先展示
     * var a = $('.j-bookitm .info .u-stargrade .num')[0].innerHTML
     * a.split(' ')[1]
     */
    private int ratingValue;

    /**
     * 作者
     * $('.j-bookitm .info .u-author span')[0].innerHTML
     */
    private String author;

    /**
     * 图书介绍
     * $('.j-bookitm .info .desc')[0].innerHTML
     */
    private String desc;

    public DuokanBookInfo() {
    }

    public String getCoverURL() {
        return coverURL;
    }

    public void setCoverURL(String coverURL) {
        this.coverURL = coverURL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public int getStargrade() {
        return stargrade;
    }

    public void setStargrade(int stargrade) {
        this.stargrade = stargrade;
    }

    public int getRatingValue() {
        return ratingValue;
    }

    public void setRatingValue(int ratingValue) {
        this.ratingValue = ratingValue;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
