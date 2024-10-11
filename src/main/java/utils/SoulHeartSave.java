package utils;

public class SoulHeartSave {

    public int soulHeart;
    public int blackHeart;
    public int soulHeartNum;
    public int blackHeartNum;
    public int bookCount;
    public int guppyCount;


    public SoulHeartSave(int soulHeart, int blackHeart, int soulHeartNum, int blackHeartNum) {
        this.soulHeart = soulHeart;
        this.blackHeart = blackHeart;
        this.soulHeartNum = soulHeartNum;
        this.blackHeartNum = blackHeartNum;
    }

    public SoulHeartSave(int soulHeart, int blackHeart, int soulHeartNum, int blackHeartNum, int bookCount, int guppyCount) {
        this.soulHeart = soulHeart;
        this.blackHeart = blackHeart;
        this.soulHeartNum = soulHeartNum;
        this.blackHeartNum = blackHeartNum;
        this.bookCount = bookCount;
        this.guppyCount = guppyCount;
    }
}
