package cards;

public interface RightClickableCard {
    void onRightClick();

    default void onDoubleRightClick() {
    }

    default Position[] clickablePositions() {
        return new Position[]{RightClickableCard.Position.HAND};
    }

    void updateBgImg();

    public static enum Position {
        HAND,
        DRAW_PILE,
        DISCARD_PILE,
        EXHAUST_PILE;

        private Position() {

        }
    }
}
